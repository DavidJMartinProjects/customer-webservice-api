DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  address VARCHAR(250) DEFAULT NULL,
  city VARCHAR(250) DEFAULT NULL,
  country VARCHAR(250) DEFAULT NULL,
  email VARCHAR(250) DEFAULT NULL,
  image VARCHAR(250) DEFAULT NULL
);

INSERT INTO customers (first_name, last_name, address, city, country, email, image) VALUES
  ('test-firstName-1', 'test-lastName-1', 'test-address-1', 'test-city-1', 'test-country-1', 'test-email-1', 'test-imageUrl-1'),
  ('test-firstName-2', 'test-lastName-2', 'test-address-2', 'test-city-2', 'test-country-2', 'test-email-2', 'test-imageUrl-2'),
  ('test-firstName-3', 'test-lastName-3', 'test-address-3', 'test-city-3', 'test-country-3', 'test-email-3', 'test-imageUrl-3');
