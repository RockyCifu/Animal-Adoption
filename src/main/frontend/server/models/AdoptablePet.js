import pg from "pg"

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/adopt_a_pet"
})

class AdoptablePet {
  constructor({
    id = null,
    name,
    img_url,
    imgUrl,
    age,
    vaccination_status,
    vaccinationStatus,
    adoption_story,
    adoptionStory,
    available_for_adoption,
    availableForAdoption,
    type_id,
    typeId
  }) {
    this.id = id
    this.name = name
    this.imgUrl = img_url || imgUrl
    this.age = age
    this.vaccinationStatus = vaccination_status || vaccinationStatus
    this.adoptionStory = adoption_story || adoptionStory
    this.availableForAdoption = available_for_adoption || availableForAdoption
    this.typeId = type_id || typeId
  }

  static async findById(id) {
    try {
      const client = await pool.connect()
      const result = await client.query("SELECT * FROM adoptable_pets WHERE id = $1", [id])
      const adoptionPet = new this(result.rows[0])
      client.release()
      return adoptionPet
    } catch (error) {
      console.error(error)
    }
  }
}

export default AdoptablePet
