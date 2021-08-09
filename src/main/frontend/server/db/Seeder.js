import pg from "pg"

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/adopt_a_pet",
})

class Seeder {
  static async seed() {
    try {
      await pool.query("INSERT INTO pet_types (type, img_url, description) VALUES ($1, $2, $3) ", [
        "Cat",
        "https://www.purina.co.uk/sites/default/files/2020-12/Understanding%20Your%20Cat%27s%20Body%20LanguageHERO.jpg",
        "The cat is very cute an innocent animal. They have sharp claws, furry body and a bushy tail. The cat is omnivore because it eats both meat and vegetables.",
      ])

      await pool.query("INSERT INTO pet_types (type, img_url, description) VALUES ($1, $2, $3) ", [
        "Dog",
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg",
        "A popular pet because they are usually playful, friendly, loyal and listen to humans.",
      ])

      await pool.query(
        "INSERT INTO adoptable_pets (name, img_url, age, vaccination_status, adoption_story, available_for_adoption, type_id ) VALUES ($1, $2, $3, $4, $5, $6, $7) ",
        [
          "Ziggy",
          "https://g.petango.com/photos/2213/31449f9a-b386-41a6-8ff1-5c155e1b66c9.jpg",
          7,
          true,
          "Ziggy is a sweet and quiet 7-year-old tabby looking for her new home. Ziggy is shy and likes her environments to be quiet, predictable and routine. One she settles in she's very loving and affectionate. She likes to chase toys too! We don't know if Ziggy's ever met other cats or dogs, so any introductions to resident pets should be done very slowly. Because she can be sensitive to handling and noises, she is looking for a cat experienced, mellow home with just teens and adults. If you're ready to welcome Ziggy into your home, send in an adoption application today!",
          true,
          1,
        ]
      )

      await pool.query(
        "INSERT INTO adoptable_pets (name, img_url, age, vaccination_status, adoption_story, available_for_adoption, type_id ) VALUES ($1, $2, $3, $4, $5, $6, $7) ",
        [
          "Baloo",
          "https://humanepro.org/sites/default/files/styles/article/public/images/post/cat-portrait.jpg?itok=YzekXgKK",
          6,
          true,
          "Baloo was found wandering and was brought to PAWS so he could find a safe, indoor home with people to love him! He is a low key, easygoing cat who enjoys attention on his terms. He is very smart and knows what he likes.",
          true,
          1,
        ]
      )

      await pool.query(
        "INSERT INTO adoptable_pets (name, img_url, age, vaccination_status, adoption_story, available_for_adoption, type_id ) VALUES ($1, $2, $3, $4, $5, $6, $7) ",
        [
          "Ace",
          "https://static.wixstatic.com/media/1b5818_27a7ad82a1464f82a573ee2cb80d5728~mv2.jpg/v1/fill/w_640,h_520,al_c,q_80,usm_0.66_1.00_0.01/1b5818_27a7ad82a1464f82a573ee2cb80d5728~mv2.webp",
          2,
          true,
          "Ace is a sweet and sensitive boy who can be easily be overwhelmed by loud noises and fast movements. Once he feels comfortable, Ace is affectionate and enjoys gentle pets and attention. He also likes the company of other little dogs.",
          true,
          2,
        ]
      )

      await pool.query(
        "INSERT INTO adoptable_pets (name, img_url, age, vaccination_status, adoption_story, available_for_adoption, type_id ) VALUES ($1, $2, $3, $4, $5, $6, $7) ",
        [
          "Christa",
          "https://specials-images.forbesimg.com/imageserve/5db4c7b464b49a0007e9dfac/960x0.jpg?fit=scale",
          3,
          true,
          "Christa is incredibly active, social, outgoing, bouncy and exuberant. She can sometimes be overly excited with people when she first meets them, but Christa is working on her greeting style. She is super playful with other dogs..and loves to wrestle!",
          true,
          2,
        ]
      )

      pool.end()
    } catch (error) {
      console.error(error)
      pool.end()
    }
  }
}

export default Seeder
