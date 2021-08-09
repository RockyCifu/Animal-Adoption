import pg from "pg"

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/adopt_a_pet"
})

class Application {
  constructor ( { id=null, name, phoneNumber, phone_number, email, home_status, homeStatus, application_status, pet_id, petId } ) {
    this.id = id
    this.name = name
    this.phoneNumber = phone_number || phoneNumber
    this.email = email
    this.homeStatus = home_status || homeStatus
    this.applicationStatus = application_status || "pending"
    this.petId = pet_id || petId

  }

  async save() {
    try {
      const client = await pool.connect()
      const result = await client.query("INSERT INTO adoption_application (name, phone_number, email, home_status, application_status, pet_id) VALUES ($1, $2, $3, $4, $5, $6) RETURNING id;", [this.name, this.phoneNumber, this.email, this.homeStatus, this.applicationStatus, this.petId])
      this.id = result.rows[0].id
      client.release()
      return true
    } catch (error) {
      console.error(error)
      throw(error)
    }
  }
}

export default Application
