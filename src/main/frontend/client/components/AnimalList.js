import React, { useState, useEffect } from "react"
import AnimalTile from "./AnimalTile.js"
import DBUtils from "../utils/DBUtils.js"
import { Redirect } from "react-router"

const AnimalList = props => {
  const [animals, setAnimals] = useState([])
  const [redirect, setRedirect] = useState(false)

  const fetchAnimals = async () => {
    if (animals.length === 0) {
      const animalList = await DBUtils.fetchData(`/api/v1/pets/${props.match.params.type}`)
      if (animalList.length === 0) {
        setRedirect(true)
      } else {
        setAnimals(animalList)
      }
    }
  }

  useEffect(() => {
    fetchAnimals()
  }, [props])

  const petList = animals.map(animal => {
    return <AnimalTile key={animal.id} animal={animal} />
  })

  return redirect ? <Redirect push to="/404" /> : <>{petList}</>
}

export default AnimalList
