import pg from "pg"
import _ from "lodash"

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/adopt_a_pet"
})

class PetType {
  constructor ( {id, type, description, imgUrl, img_url } ) {
    this.id = id
    this.type = type
    this.description = description
    this.imgUrl = imgUrl || img_url
  }

  static async findAll() {
    try {
      const client = await pool.connect()
      const result = await client.query("SELECT * FROM pet_types;")
      const animalTypes = result.rows.map(animal => new this(animal))
      client.release()
      return animalTypes
    } catch (error) {
      console.error(error)
      client.release()
    }
  }

  static async findByType(type) {
    try {
      const client = await pool.connect()
      const result = await client.query("SELECT * FROM pet_types WHERE type = $1", [_.startCase(type)])
      const animalType = new this(result.rows[0])
      client.release()
      return animalType
    } catch (error) {
      console.error(error)
      client.release()
    }
  }

  async findAdoptablePets() {
    const adoptablePetsFile = await import('./AdoptablePet.js')
    const AdoptablePet = adoptablePetsFile.default

    try {
      const client = await pool.connect()
      const result = await client.query("SELECT * FROM adoptable_pets WHERE type_id = $1;", [this.id])
      const relatedPets = result.rows.map(pet => new AdoptablePet(pet))
      client.release()

      return relatedPets
    } catch (error) {
      console.error(error)
      throw(error)
    }
  }
}

export default PetType
