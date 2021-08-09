import React from "react"
import { Link } from "react-router-dom"

const TypeTile = props => {
  const { type, description, imgUrl } = props.petType
  return (
    <>
      <h1>
        <Link to={`/pets/${type}`}>{type}</Link>
      </h1>
      <Link to={`/pets/${type}`}>
        {" "}
        <img className="images thumbnail" src={imgUrl}></img>{" "}
      </Link>
      {description}
    </>
  )
}

export default TypeTile
