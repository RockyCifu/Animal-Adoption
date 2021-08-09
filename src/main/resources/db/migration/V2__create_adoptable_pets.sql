DROP TABLE IF EXISTS adoptable_pets CASCADE;

CREATE TABLE adoptable_pets (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR (255) NOT NULL,
  img_url VARCHAR (500) NOT NULL,
  age INTEGER,
  vaccination_status BOOLEAN,
  adoption_story TEXT NOT NULL,
  available_for_adoption BOOLEAN NOT NULL,
  pet_type_id BIGINT REFERENCES pet_types(id)
);