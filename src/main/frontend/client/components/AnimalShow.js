import React, { useState, useEffect } from "react"
import AdoptionForm from "./AdoptionForm.js"
import SuccessTile from "./SuccessTile.js"
import DBUtils from "../utils/DBUtils.js"
import { Redirect } from "react-router"
import _ from "lodash"

const AnimalShow = props => {
  const [animal, setAnimal] = useState({})
  const [showForm, setShowForm] = useState(false)
  const [successful, setSuccessful] = useState(false)
  const [redirect, setRedirect] = useState(false)

  const { id, type } = props.match.params
  const { name, age, imgUrl, vaccinationStatus, adoptionStory } = animal

  const getAnimal = async () => {
    if (_.isEmpty(animal)) {
      const requestedAnimal = await DBUtils.fetchData(`/api/v1/pets/${type}/${id}`)

      if (!requestedAnimal.id) {
        setRedirect(true)
      } else {
        setAnimal(requestedAnimal)
      }
    }
  }

  useEffect(() => {
    getAnimal()
  }, [])

  const handleClick = () => {
    setShowForm(true)
  }

  const handleSuccess = () => {
    setSuccessful(true)
  }

  const vaccinated = vaccinationStatus ? "Yes" : "No"
  const adoptionForm =
    showForm && successful ? (
      <SuccessTile />
    ) : showForm ? (
      <AdoptionForm id={id} handleSuccess={handleSuccess} />
    ) : (
      ""
    )

  return redirect ? (
    <Redirect push to="/404" />
  ) : (
    <>
      <h1>{name}</h1>
      <img src={imgUrl}></img>
      <p>
        <strong>Age:</strong> {age} months old
      </p>
      <p>
        <strong>{name}'s story:</strong> {adoptionStory}
      </p>
      <p>
        <strong>Vaccination Status:</strong> {vaccinated}
      </p>
      {adoptionForm}
      {!showForm && <button onClick={handleClick}>Adopt Me</button>}
    </>
  )
}

export default AnimalShow
