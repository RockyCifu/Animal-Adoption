import React, { useState } from "react"
import AdoptionForm from "./AdoptionForm"
import Confirmation from "./Confirmation"
import SuccessTile from "./SuccessTile"
import DBUtils from "../utils/DBUtils.js"

const ApplicationTile = props => {
  const [showConfirm, setShowConfirm] = useState(false)
  const [showForm, setShowForm] = useState(false)
  const [successfull, setSuccessfull] = useState(false)
  const [action, setAction] = useState("")

  const { name, phoneNumber, email, homeStatus, adoptablePet } = props.application

  const handleConfirm = e => {
    setAction(e.target.name)
    setShowConfirm(true)
  }

  const handleAction = () => {
    switch (action) {
      case "edit":
        editApplication()
        break
      case "delete":
        deleteApplication()
        break
    }
  }

  const handleSuccess = () => {
    setSuccessfull(true)
  }

  const deleteApplication = async () => {
    setShowConfirm(false)
    await DBUtils.deleteData("/api/v1/application", { id: props.application.id })
    props.handleRefresh()
  }

  const editApplication = () => {
    setShowConfirm(false)
    setShowForm(true)
  }

  let variableDisplay = (
    <>
      <button id="edit" name="edit" onClick={handleConfirm}>
        Edit
      </button>
      <button id="delete" name="delete" onClick={handleConfirm}>
        Delete
      </button>
    </>
  )

  if (showConfirm) {
    variableDisplay = (
      <Confirmation action={action} handleAction={handleAction} setShowConfirm={setShowConfirm} />
    )
  }

  if (showForm) {
    variableDisplay = <AdoptionForm values={props.application} handleSuccess={handleSuccess} />
  }

  if (successfull) {
    variableDisplay = <SuccessTile />
  }

  return (
    <li>
      <strong>Name: </strong>
      {name}
      <br />
      <strong>Phone Number: </strong>
      {phoneNumber}
      <br />
      <strong>Email: </strong>
      {email}
      <br />
      <strong>Home Status: </strong>
      {homeStatus}
      <br />
      <strong>Pet Wanted: </strong>
      {adoptablePet.name}
      <br />
      {variableDisplay}
      <br />
      <br />
    </li>
  )
}

export default ApplicationTile
