import _ from "lodash"
import React, { useState } from "react"
import DBUtils from "../utils/DBUtils.js"
import TextInput from "./TextInput"
import SelectInput from "./SelectInput"

const AdoptionForm = props => {
  const INITIAL_STATE = {
    name: props.values ? props.values.name : "",
    phoneNumber: props.values ? props.values.phoneNumber : "",
    email: props.values ? props.values.email : "",
    homeStatus: props.values ? props.values.homeStatus : "",
    petId: props.values ? props.values.adoptablePet.id : props.id
  }

  const [values, setValues] = useState(INITIAL_STATE)
  const [errors, setErrors] = useState({})

  const handleChange = e => {
    e.preventDefault()
    setValues({ ...values, [e.currentTarget.name]: e.currentTarget.value })
  }

  const handleSubmit = async e => {
    e.preventDefault()
    if (DBUtils.isValid(["name", "phoneNumber", "email", "homeStatus"], values, setErrors)) {
      let response
      if (props.values) {
        const payLoad = { ...values, id: props.values.id }
        response = await DBUtils.patchData("/api/v1/application", payLoad)
      } else {
        response = await DBUtils.postData("/api/v1/application", values)
      }

      if (_.isEmpty(response)) {
        setValues(INITIAL_STATE)
        props.handleSuccess()
      } else {
        setErrors(response)
      }
    }
  }

  return (
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
      <SelectInput
        label="homeStatus"
        text="Own or rent your home?:"
        onChange={handleChange}
        value={values.homeStatus}
        error={errors.homeStatus}
        options={["", "Own", "Rent"]}
      />
      <input className="submit-btn" type="submit" value="Apply" />
    </form>
  )
}

export default AdoptionForm
