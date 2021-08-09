import pg from "pg"

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/adopt_a_pet"
})

class Surrender {
  constructor ( { id=null, name, phoneNumber, phone_number, email, petName, pet_name, petAge, pet_age, petImageUrl, pet_image_url, vaccinationStatus, vaccination_status, application_status, petType, pet_type_id } ) {
    this.id = id
    this.name = name
    this.phoneNumber = phone_number || phoneNumber
    this.email = email
    this.petName = petName || pet_name
    this.petAge = petAge || pet_age
    this.petType = petType
    this.petImageUrl = petImageUrl || pet_image_url
    this.vaccinationStatus = vaccinationStatus || vaccination_status
    this.applicationStatus = application_status || "pending"
    this.petTypeId = pet_type_id || null
  }

  async save() {
    try {
      const client = await pool.connect()
      const petTypeSeek = await client.query("SELECT * FROM pet_types WHERE pet_types.type = $1", [this.petType])
      this.petTypeId = petTypeSeek.rows[0].id
      const result = await client.query("INSERT INTO pet_surrender_applications (name, phone_number, email, pet_name, pet_age, pet_image_url, vaccination_status, application_status, pet_type_id) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9) RETURNING id", [this.name, this.phoneNumber, this.email, this.petName, this.petAge, this.petImageUrl, this.vaccinationStatus, this.applicationStatus, this.petTypeId])
      this.id = result.rows[0].id
      client.release()
      return true
    } catch (error) {
      console.error(error)
      throw(error)
    }
  }
}

export default Surrender
