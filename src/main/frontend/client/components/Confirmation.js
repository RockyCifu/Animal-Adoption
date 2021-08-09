import React from "react"

const Confirmation = props => {
  return (
    <>
      Are you sure you want to {props.action} the application?{" "}
      <button onClick={() => props.handleAction()}>Yes</button>
      <button onClick={() => props.setShowConfirm(false)}>No</button>
    </>
  )
}

export default Confirmation
