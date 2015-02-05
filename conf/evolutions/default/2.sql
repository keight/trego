# --- !Ups

CREATE TABLE slides (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  image VARCHAR(255) NOT NULL
);

# --- !Downs

DELETE TABLE slides;