DROP TABLE IF EXISTS test;

CREATE TABLE test (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              first_name VARCHAR(250) NOT NULL,
                              last_name VARCHAR(250) NOT NULL,
                              career VARCHAR(250) DEFAULT NULL
);

INSERT INTO Currency (short_Name, name, count_of_exchanged_to, count_of_exchanged_from) VALUES
('EUR', 'Euro', 0, 0),
('USD', 'US Dollar', 0, 0),
('GBP', 'Pound sterling', 0, 0),
('CZK', 'Czech koruna', 0, 0),
('HUF', 'Hungarian forint', 0, 0);

INSERT INTO Exchange_Rate (exchange_from, exchange_to, rate) VALUES
('EUR', 'USD', 1.2188),
('EUR', 'HUF', 349.11),
('EUR', 'CZK', 25.452),
('EUR', 'GBP', 0.85870);