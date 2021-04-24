DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  address VARCHAR(250) DEFAULT NULL,
  city VARCHAR(250) DEFAULT NULL,
  country VARCHAR(250) DEFAULT NULL,
  email VARCHAR(250) DEFAULT NULL
);

INSERT INTO customers (first_name, last_name, address, city, country, email) VALUES
  ('David', 'Martin', 'Main St.', 'Texas', 'USA', 'dm@email.com'),
  ('John', 'Doe', 'City Center', 'Paris', 'France', 'jd@email.com'),
  ('Mary', 'Bloggs', 'Town Square', 'London', 'UK', 'mb@email.com');