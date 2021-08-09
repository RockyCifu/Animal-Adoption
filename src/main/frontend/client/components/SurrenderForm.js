import _ from "lodash"
import React, { useState } from "react"
import DBUtils from "../utils/DBUtils.js"
import TextInput from "./TextInput"
import SelectInput from "./SelectInput"
import SuccessTile from "./SuccessTile"

const SurrenderForm = () => {
  const INITIAL_STATE = {
    name: "",
    phoneNumber: "",
    email: "",
    petName: "",
    petAge: "",
    petType: "",
    imgUrl: "",
    vaccinationStatus: "",
    adoptionStory: ""
  }

  const [values, setValues] = useState(INITIAL_STATE)
  const [errors, setErrors] = useState({})
  const [success, setSuccess] = useState(false)

  const handleChange = e => {
    e.preventDefault()
    setValues({ ...values, [e.currentTarget.name]: e.currentTarget.value })
  }

  const handleSubmit = async e => {
    e.preventDefault()
    if (DBUtils.isValid(Object.keys(values), values, setErrors)) {
      const response = await DBUtils.postData("/api/v1/surrender", values)
      if (_.isEmpty(response)) {
        setValues(INITIAL_STATE)
        setSuccess(true)
      } else {
        setErrors(response)
      }
    }
  }

  const form = success ? (
    <SuccessTile />
  ) : (
    <form onSubmit={handleSubmit}>
      <TextInput
        label="name"
        text="Name: "
        onChange={handleChange}
        value={values.name}
        error={errors.name}
      />
      <TextInput
        label="phoneNumber"
        text="Phone: "
        onChange={handleChange}
        value={values.phoneNumber}
        error={errors.phoneNumber}
      />
      <TextInput
        label="email"
        text="Email: "
        onChange={handleChange}
        value={values.email}
        error={errors.email}
      />
      <TextInput
        label="petName"
        text="Pet's Name: "
        onChange={handleChange}
        value={values.petName}
        error={errors.petName}
      />
      <TextInput
        label="imgUrl"
        text="Picture of your pet: "
        onChange={handleChange}
        value={values.imgUrl}
        error={errors.imgUrl}
      />
      <TextInput
        label="petAge"
        text="Pets Age (in months): "
        onChange={handleChange}
        value={values.petAge}
        error={errors.petAge}
      />
      <TextInput
        label="adoptionStory"
        text="Animal's Story: "
        onChange={handleChange}
        value={values.adoptionStory}
        error={errors.adoptionStory}
      />
      <SelectInput
        label="petType"
        text="Pet Type: "
        onChange={handleChange}
        value={values.petType}
        error={errors.petType}
        options={["", "cat", "dog"]}
      />
      <SelectInput
        label="vaccinationStatus"
        text="Vaccinated?: "
        onChange={handleChange}
        value={values.vaccinationStatus}
        error={errors.vaccinationStatus}
        options={["", "true", "false"]}
      />
      <input className="button round" type="submit" value="Submit" />
    </form>
  )

  return (
    <>
      <h2>Surrender Your Pet:</h2>
      {form}
    </>
  )
}
export default SurrenderForm
