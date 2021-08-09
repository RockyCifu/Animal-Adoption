import React from "react"

const SelectInput = props => {
  const { label, text, value, onChange, error } = props

  const options = props.options.map(option => {
    let text = option ? option : "Please Select"

    if (option === "true" || option === "false") {
      text = option === "true" ? "yes" : "no"
    }

    return (
      <option key={option} value={option}>
        {text}
      </option>
    )
  })

  return (
    <label htmlFor={label}>
      {text} {error && <p style={{ color: "red" }}>{error}</p>}
      <select id={label} name={label} value={value} onChange={onChange}>
        {options}
      </select>
    </label>
  )
}

export default SelectInput
