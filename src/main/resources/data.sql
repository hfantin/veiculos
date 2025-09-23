-- limpar tabelas
DELETE from brands;
DELETE from models;
DELETE from vehicles;

-- reinicia sequenciais
 ALTER SEQUENCE brands_id_seq RESTART;
 ALTER SEQUENCE models_id_seq RESTART;
 ALTER SEQUENCE vehicles_id_seq RESTART;

-- popular as tabelas marcas de veículos
INSERT INTO brands (name) VALUES
('Toyota'),
('Volkswagen'),
('Fiat'),
('Chevrolet'),
('Ford'),
('Honda'),
('Hyundai'),
('Nissan'),
('Renault'),
('Jeep'),
('BMW'),
('Mercedes-Benz'),
('Audi'),
('Volvo'),
('Mitsubishi');

-- incluir modelos de veiculos
-- Modelos Toyota
INSERT INTO models (brand_id, name) VALUES
(1, 'Corolla'),
(1, 'Hilux'),
(1, 'RAV4'),
(1, 'Yaris'),
(1, 'Etios');

-- Modelos Volkswagen
INSERT INTO models (brand_id, name) VALUES
(2, 'Gol'),
(2, 'Polo'),
(2, 'Virtus'),
(2, 'T-Cross'),
(2, 'Nivus');

-- Modelos Fiat
INSERT INTO models (brand_id, name) VALUES
(3, 'Uno'),
(3, 'Mobi'),
(3, 'Argo'),
(3, 'Cronos'),
(3, 'Toro');

-- Modelos Chevrolet
INSERT INTO models (brand_id, name) VALUES
(4, 'Onix'),
(4, 'Tracker'),
(4, 'S10'),
(4, 'Cruze'),
(4, 'Spin');

-- Modelos Ford
INSERT INTO models (brand_id, name) VALUES
(5, 'Ranger'),
(5, 'Ka'),
(5, 'EcoSport'),
(5, 'Focus'),
(5, 'Fiesta');

-- Modelos Honda
INSERT INTO models (brand_id, name) VALUES
(6, 'Civic'),
(6, 'HR-V'),
(6, 'Fit'),
(6, 'City'),
(6, 'WR-V');

-- Modelos Hyundai
INSERT INTO models (brand_id, name) VALUES
(7, 'HB20'),
(7, 'Creta'),
(7, 'Tucson'),
(7, 'i30'),
(7, 'Santa Fé');

-- Modelos Nissan
INSERT INTO models (brand_id, name) VALUES
(8, 'Kicks'),
(8, 'Versa'),
(8, 'March'),
(8, 'Frontier'),
(8, 'Sentra');

-- Modelos Renault
INSERT INTO models (brand_id, name) VALUES
(9, 'Kwid'),
(9, 'Sandero'),
(9, 'Duster'),
(9, 'Captur'),
(9, 'Logan');

-- Modelos Jeep
INSERT INTO models (brand_id, name) VALUES
(10, 'Renegade'),
(10, 'Compass'),
(10, 'Commander'),
(10, 'Wrangler'),
(10, 'Gladiator');

-- Modelos BMW
INSERT INTO models (brand_id, name) VALUES
(11, 'Série 3'),
(11, 'X1'),
(11, 'X3'),
(11, 'Série 5'),
(11, 'X5');

-- Modelos Mercedes-Benz
INSERT INTO models (brand_id, name) VALUES
(12, 'Classe A'),
(12, 'Classe C'),
(12, 'GLA'),
(12, 'GLC'),
(12, 'Classe E');

-- Modelos Audi
INSERT INTO models (brand_id, name) VALUES
(13, 'A3'),
(13, 'A4'),
(13, 'Q3'),
(13, 'Q5'),
(13, 'A5');

-- Modelos Volvo
INSERT INTO models (brand_id, name) VALUES
(14, 'XC40'),
(14, 'XC60'),
(14, 'XC90'),
(14, 'S60'),
(14, 'V60');

-- Modelos Mitsubishi
INSERT INTO models (brand_id, name) VALUES
(15, 'L200'),
(15, 'Outlander'),
(15, 'Eclipse Cross'),
(15, 'ASX'),
(15, 'Pajero Sport');

-- incluir veiculos
-- Veículos Toyota
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(1, 2023, 'Prata', 85000.00, 'AVAILABLE'),
(1, 2022, 'Preto', 78000.00, 'AVAILABLE'),
(2, 2023, 'Branco', 120000.00, 'AVAILABLE'),
(3, 2022, 'Vermelho', 95000.00, 'SOLD'),
(4, 2023, 'Azul', 65000.00, 'AVAILABLE');

-- Veículos Volkswagen
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(6, 2023, 'Branco', 70000.00, 'AVAILABLE'),
(7, 2022, 'Prata', 85000.00, 'AVAILABLE'),
(8, 2023, 'Preto', 90000.00, 'SOLD'),
(9, 2022, 'Cinza', 95000.00, 'AVAILABLE'),
(10, 2023, 'Vermelho', 88000.00, 'AVAILABLE');

-- Veículos Fiat
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(11, 2023, 'Verde', 45000.00, 'AVAILABLE'),
(12, 2022, 'Branco', 40000.00, 'SOLD'),
(13, 2023, 'Prata', 60000.00, 'AVAILABLE'),
(14, 2022, 'Preto', 55000.00, 'AVAILABLE'),
(15, 2023, 'Azul', 75000.00, 'AVAILABLE');

-- Veículos Chevrolet
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(16, 2023, 'Branco', 65000.00, 'AVAILABLE'),
(17, 2022, 'Prata', 85000.00, 'AVAILABLE'),
(18, 2023, 'Preto', 120000.00, 'SOLD'),
(19, 2022, 'Vermelho', 75000.00, 'AVAILABLE'),
(20, 2023, 'Cinza', 50000.00, 'AVAILABLE');

-- Veículos Ford
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(21, 2023, 'Azul', 110000.00, 'AVAILABLE'),
(22, 2022, 'Branco', 45000.00, 'SOLD'),
(23, 2023, 'Prata', 70000.00, 'AVAILABLE'),
(24, 2022, 'Preto', 60000.00, 'AVAILABLE'),
(25, 2023, 'Vermelho', 55000.00, 'AVAILABLE');

-- Veículos Honda
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(26, 2023, 'Branco', 95000.00, 'AVAILABLE'),
(27, 2022, 'Prata', 85000.00, 'AVAILABLE'),
(28, 2023, 'Preto', 65000.00, 'SOLD'),
(29, 2022, 'Azul', 70000.00, 'AVAILABLE'),
(30, 2023, 'Vermelho', 75000.00, 'AVAILABLE');

-- Veículos Hyundai
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(31, 2023, 'Cinza', 60000.00, 'AVAILABLE'),
(32, 2022, 'Branco', 80000.00, 'AVAILABLE'),
(33, 2023, 'Prata', 95000.00, 'SOLD'),
(34, 2022, 'Preto', 55000.00, 'AVAILABLE'),
(35, 2023, 'Azul', 110000.00, 'AVAILABLE');

-- Veículos Nissan
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(36, 2023, 'Vermelho', 85000.00, 'AVAILABLE'),
(37, 2022, 'Branco', 50000.00, 'AVAILABLE'),
(38, 2023, 'Prata', 45000.00, 'SOLD'),
(39, 2022, 'Preto', 90000.00, 'AVAILABLE'),
(40, 2023, 'Azul', 75000.00, 'AVAILABLE');

-- Veículos Renault
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(41, 2023, 'Amarelo', 40000.00, 'AVAILABLE'),
(42, 2022, 'Branco', 45000.00, 'AVAILABLE'),
(43, 2023, 'Prata', 70000.00, 'SOLD'),
(44, 2022, 'Preto', 65000.00, 'AVAILABLE'),
(45, 2023, 'Azul', 50000.00, 'AVAILABLE');

-- Veículos Jeep
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(46, 2023, 'Verde', 90000.00, 'AVAILABLE'),
(47, 2022, 'Branco', 110000.00, 'AVAILABLE'),
(48, 2023, 'Prata', 130000.00, 'SOLD'),
(49, 2022, 'Preto', 150000.00, 'AVAILABLE'),
(50, 2023, 'Azul', 140000.00, 'AVAILABLE');

-- Veículos BMW
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(51, 2023, 'Branco', 200000.00, 'AVAILABLE'),
(52, 2022, 'Prata', 180000.00, 'AVAILABLE'),
(53, 2023, 'Preto', 220000.00, 'SOLD'),
(54, 2022, 'Azul', 250000.00, 'AVAILABLE'),
(55, 2023, 'Cinza', 230000.00, 'AVAILABLE');

-- Veículos Mercedes-Benz
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(56, 2023, 'Prata', 220000.00, 'AVAILABLE'),
(57, 2022, 'Preto', 200000.00, 'AVAILABLE'),
(58, 2023, 'Branco', 180000.00, 'SOLD'),
(59, 2022, 'Azul', 240000.00, 'AVAILABLE'),
(60, 2023, 'Cinza', 260000.00, 'AVAILABLE');

-- Veículos Audi
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(61, 2023, 'Preto', 190000.00, 'AVAILABLE'),
(62, 2022, 'Branco', 210000.00, 'AVAILABLE'),
(63, 2023, 'Prata', 170000.00, 'SOLD'),
(64, 2022, 'Azul', 230000.00, 'AVAILABLE'),
(65, 2023, 'Cinza', 200000.00, 'AVAILABLE');

-- Veículos Volvo
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(66, 2023, 'Azul', 180000.00, 'AVAILABLE'),
(67, 2022, 'Branco', 200000.00, 'AVAILABLE'),
(68, 2023, 'Prata', 250000.00, 'SOLD'),
(69, 2022, 'Preto', 220000.00, 'AVAILABLE'),
(70, 2023, 'Cinza', 190000.00, 'AVAILABLE');

-- Veículos Mitsubishi
INSERT INTO vehicles (model_id, year, color, price, status) VALUES
(71, 2023, 'Branco', 120000.00, 'AVAILABLE'),
(72, 2022, 'Prata', 110000.00, 'AVAILABLE'),
(73, 2023, 'Preto', 100000.00, 'SOLD'),
(74, 2022, 'Azul', 90000.00, 'AVAILABLE'),
(75, 2023, 'Vermelho', 130000.00, 'AVAILABLE');