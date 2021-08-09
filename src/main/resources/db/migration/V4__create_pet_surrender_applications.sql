DROP TABLE IF EXISTS pet_surrender_applications CASCADE;

CREATE TABLE pet_surrender_applications (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR (255) NOT NULL,
  phone_number VARCHAR (255) NOT NULL,
  email VARCHAR (255) NOT NULL,
  status VARCHAR (255) NOT NULL,
  adoptable_pet_id BIGINT REFERENCES adoptable_pets(id)
);