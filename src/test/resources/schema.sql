-- Table: brands
CREATE TABLE IF NOT EXISTS brands (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: models
CREATE TABLE IF NOT EXISTS models (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    brand_id INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(brand_id, name),
    FOREIGN KEY (brand_id) REFERENCES brands(id) ON DELETE CASCADE
);

-- Table: vehicles
CREATE TABLE IF NOT EXISTS vehicles (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    model_id INTEGER NOT NULL,
    "year" INTEGER NOT NULL,
    color VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) DEFAULT 'AVAILABLE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sold_at TIMESTAMP NULL,
    FOREIGN KEY (model_id) REFERENCES models(id) ON DELETE CASCADE
);

-- Indices for vehicles
CREATE INDEX IF NOT EXISTS idx_vehicles_status ON vehicles(status);
CREATE INDEX IF NOT EXISTS idx_vehicles_price ON vehicles(price);
CREATE INDEX IF NOT EXISTS idx_vehicles_model_id ON vehicles(model_id);

-- Table: customers
CREATE TABLE IF NOT EXISTS customers (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    auth_id VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address TEXT,
    cpf VARCHAR(11),
    validated BOOLEAN DEFAULT FALSE,
    type VARCHAR(20) DEFAULT 'USER' CHECK (type IN ('USER', 'ADMIN')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indices for customers
CREATE INDEX IF NOT EXISTS idx_customers_auth_id ON customers(auth_id);
CREATE INDEX IF NOT EXISTS idx_customers_email ON customers(email);
CREATE INDEX IF NOT EXISTS idx_customers_type ON customers(type);

-- Table: sales
CREATE TABLE IF NOT EXISTS sales (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    payment_method VARCHAR(50),
    transaction_id VARCHAR(100),
    status VARCHAR(20) DEFAULT 'COMPLETED' CHECK (status IN ('PENDING', 'COMPLETED', 'CANCELLED')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
);

-- Indices for sales
CREATE INDEX IF NOT EXISTS idx_sales_customer_id ON sales(customer_id);
CREATE INDEX IF NOT EXISTS idx_sales_date ON sales(sale_date);
CREATE INDEX IF NOT EXISTS idx_sales_status ON sales(status);

-- Table: sale_vehicles
CREATE TABLE IF NOT EXISTS sale_vehicles (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    sale_id INTEGER NOT NULL,
    vehicle_id INTEGER NOT NULL,
    sale_price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(vehicle_id),
    FOREIGN KEY (sale_id) REFERENCES sales(id) ON DELETE CASCADE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- Indices for sale_vehicles
CREATE INDEX IF NOT EXISTS idx_sale_vehicles_sale_id ON sale_vehicles(sale_id);
CREATE INDEX IF NOT EXISTS idx_sale_vehicles_vehicle_id ON sale_vehicles(vehicle_id);
