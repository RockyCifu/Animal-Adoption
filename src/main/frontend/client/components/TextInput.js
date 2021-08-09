import React from "react"

const TextInput = props => {
  const { label, text, value, onChange, error } = props
  return (
    <label htmlFor={label}>
      {text} {error && <p style={{ color: "red" }}>{error}</p>}
      <input id={label} type="text" name={label} onChange={onChange} value={value} />
    </label>
  )
}

export default TextInput
