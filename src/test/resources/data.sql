-- brands
INSERT INTO brands (id, name, created_at)
VALUES (1, 'Toyota', CURRENT_TIMESTAMP);

-- models
INSERT INTO models (id, brand_id, name, created_at)
VALUES (1, 1, 'Corolla', CURRENT_TIMESTAMP);

-- vehicles
INSERT INTO vehicles (id, model_id, "year", color, price, status, created_at, updated_at, sold_at) VALUES
(1, 1, 2023, 'Red', 25000.00, 'AVAILABLE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL);

-- customers
INSERT INTO customers (id, auth_id, first_name, last_name, email, phone, address, cpf, validated, type, created_at, updated_at)
VALUES (1, 'auth0|123456789', 'User', 'Last Name', 'user@example.com', '1234567890', '123 Main St', '12345678901', TRUE, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- sales
INSERT INTO sales (id, customer_id, sale_date, total_amount, payment_method, transaction_id, status, created_at)
VALUES (1, 1, CURRENT_TIMESTAMP, 25000.00, 'Credit Card', 'txn_001', 'COMPLETED', CURRENT_TIMESTAMP);

-- sale_vehicles
INSERT INTO sale_vehicles (id, sale_id, vehicle_id, sale_price, created_at)
VALUES (1, 1, 1, 25000.00, CURRENT_TIMESTAMP);
