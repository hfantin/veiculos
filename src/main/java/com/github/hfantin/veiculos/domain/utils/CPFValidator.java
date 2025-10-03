package com.github.hfantin.veiculos.domain.utils;

public class CPFValidator {

    public static boolean isValid(final String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        try {
            int dig1 = calculateCpfDigit(cpf.substring(0, 9));
            int dig2 = calculateCpfDigit(cpf.substring(0, 9) + dig1);

            return cpf.equals(cpf.substring(0, 9) + dig1 + dig2);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int calculateCpfDigit(final String base) {
        int sum = 0;
        int weight = base.length() + 1;

        for (int i = 0; i < base.length(); i++) {
            sum += Character.getNumericValue(base.charAt(i)) * weight--;
        }

        int remainder = sum % 11;
        return (remainder < 2) ? 0 : (11 - remainder);
    }
}
