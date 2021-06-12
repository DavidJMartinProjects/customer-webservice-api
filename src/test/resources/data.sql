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
  ('David', 'Martin', 'Main St.', 'Texas', 'USA', 'dm@email.com', 'https://bootdey.com/img/Content/avatar/avatar1.png'),
  ('Vicky', 'Hulston', 'City Center', 'Paris', 'France', 'jd@email.com', 'https://bootdey.com/img/Content/avatar/avatar2.png'),
  ('Mary', 'Bloggs', 'Town Square', 'London', 'UK', 'mb@email.com', 'https://bootdey.com/img/Content/avatar/avatar5.png'),
  ('Jim', 'Martin', 'Main St.', 'Texas', 'USA', 'dm@email.com', 'https://bootdey.com/img/Content/avatar/avatar4.png'),
  ('Joe', 'Doe', 'City Center', 'Paris', 'France', 'jd@email.com', 'https://bootdey.com/img/Content/avatar/avatar5.png'),
  ('Lucy', 'Bloggs', 'Town Square', 'London', 'UK', 'mb@email.com', 'https://bootdey.com/img/Content/avatar/avatar6.png'),
  ('Ann', 'Martin', 'Main St.', 'Texas', 'USA', 'dm@email.com', 'https://bootdey.com/img/Content/avatar/avatar7.png'),
  ('John', 'Doe', 'City Center', 'Paris', 'France', 'jd@email.com', 'https://bootdey.com/img/Content/avatar/avatar8.png'),
  ('Mary', 'Bloggs', 'Town Square', 'London', 'UK', 'mb@email.com', 'https://bootdey.com/img/Content/avatar/avatar2.png'),
  ('Jane', 'Martin', 'Main St.', 'Texas', 'USA', 'dm@email.com', 'https://bootdey.com/img/Content/avatar/avatar1.png'),
  ('John', 'Doe', 'City Center', 'Paris', 'France', 'jd@email.com', 'https://bootdey.com/img/Content/avatar/avatar2.png');
