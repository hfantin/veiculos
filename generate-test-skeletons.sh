#!/usr/bin/env bash
set -euo pipefail

SRC_DIR="src/main/java"
TEST_DIR="src/test/java"

find "$SRC_DIR" -name "*.java" | while read -r file; do
  # get package (first 'package' line) and class name
  pkg=$(grep -m1 "^package " "$file" | sed -E 's/^package (.*);/\1/' || true)
  classname=$(basename "$file" .java)
  testpkg="$pkg"
  if [ -z "$testpkg" ]; then
    testdir="$TEST_DIR"
  else
    testdir="$TEST_DIR/$(echo "$testpkg" | tr '.' '/')"
  fi
  mkdir -p "$testdir"
  testfile="$testdir/${classname}Test.java"
  if [ -f "$testfile" ]; then
    echo "Skipping existing $testfile"
    continue
  fi

  cat > "$testfile" <<EOF
${pkg:+package $testpkg;}

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ${classname}Test {

    @Test
    void placeholder_test_for_${classname}() {
        // TODO: replace with real tests for $classname
        assertThat(true).isTrue();
    }
}
EOF

  echo "Created $testfile"
done

echo "Skeleton generation finished. Please edit generated tests to add real assertions and mocks."
