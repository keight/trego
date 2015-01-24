# --- !Ups

CREATE TABLE countries (
  id BIGSERIAL PRIMARY KEY,
  countryName VARCHAR(255) NOT NULL
);

CREATE TABLE provinces (
  id BIGSERIAL PRIMARY KEY,
  provinceName VARCHAR(255) NOT NULL,
  countryId INTEGER NOT NULL,
  CONSTRAINT fk_countryId_provinces FOREIGN KEY (countryId) REFERENCES countries(id)
);

CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  telephone INTEGER NOT NULL,
  fax VARCHAR,
  company VARCHAR,
  address VARCHAR(255) NOT NULL,
  countryId INTEGER NOT NULL,
  provinceId INTEGER NOT NULL,
  CONSTRAINT fk_countryId_user FOREIGN KEY (countryId) REFERENCES countries(id),
  CONSTRAINT fk_provinceId_user FOREIGN KEY (provinceId) REFERENCES provinces(id)
);

CREATE TABLE categories(
  id BIGSERIAL PRIMARY KEY,
  categotyName VARCHAR(255) NOT NULL,
  parentId INTEGER
);

CREATE TABLE products(
  id BIGSERIAL PRIMARY KEY,
  productName VARCHAR(255) NOT NULL,
  price FLOAT NOT NULL,
  discount INTEGER,
  rating FLOAT,
  status BOOLEAN,
  code INTEGER,
  color VARCHAR(255) NOT NULL,
  size VARCHAR(255) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  detail VARCHAR(1000) NOT NULL,
  quantity INTEGER NOT NULL,
  featured_image VARCHAR(255) NOT NULL,
  images VARCHAR(1000) NOT NULL,
  categoryId INTEGER NOT NULL,
  CONSTRAINT fk_categoryId_products FOREIGN KEY (categoryId) REFERENCES categories(id)
);


# --- !Downs

DROP TABLE users;
DROP TABLE provinces;
DROP TABLE countries;
DROP TABLE products;
DROP TABLE categories;
