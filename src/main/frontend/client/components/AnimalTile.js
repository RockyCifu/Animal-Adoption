import React from "react"
import { Link } from "react-router-dom"

const AnimalTile = props => {
  const { id, name, petType, imgUrl, age, vaccinationStatus } = props.animal

  const isVaccinated = vaccinationStatus ? "Yes" : "No"

  return (
    <>
      <h2>
        <Link to={`/pets/${petType.type}/${id}`}>{name}</Link>
      </h2>
      <div>
        <Link to={`/pets/${petType.type}/${id}`}>
          <img className="images thumbnail" src={imgUrl}></img>
        </Link>
        <div className="card-section">
          <p>
            <strong>Age:</strong> {age} months{" "}
          </p>
          <p>
            <strong>Vaccinated:</strong> {isVaccinated}
          </p>
        </div>
      </div>
    </>
  )
}

export default AnimalTile
