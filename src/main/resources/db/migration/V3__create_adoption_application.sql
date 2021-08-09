DROP TABLE IF EXISTS adoption_applications CASCADE;

CREATE TABLE adoption_applications (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR (255) NOT NULL,
  phone_number VARCHAR (255) NOT NULL,
  email VARCHAR (255) NOT NULL,
  home_status VARCHAR (255) NOT NULL,
  application_status VARCHAR (255) NOT NULL,
  adoptable_pet_id BIGINT REFERENCES adoptable_pets(id)
);