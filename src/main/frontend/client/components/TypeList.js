import React, { useState, useEffect } from "react"
import TypeTile from "./TypeTile.js"
import DBUtils from "../utils/DBUtils.js"

const TypeList = () => {
  const [pets, setPets] = useState([])

  const getTypes = async () => {
    if (pets.length === 0) {
      setPets(await DBUtils.fetchData(`/api/v1/pets`))
    }
  }

  useEffect(() => {
    getTypes()
  }, [])

  const petTypes = pets.map(petType => {
    return <TypeTile key={petType.id} petType={petType} />
  })

  return <div className="home">{petTypes}</div>
}

export default TypeList
