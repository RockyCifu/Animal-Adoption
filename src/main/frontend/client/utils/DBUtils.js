export default class DBUtils {
  static fetchData = async url => {
    try {
      const response = await fetch(url)
      const data = await response.json()
      if (!response.ok && response.status !== 404) {
        const errorMessage = `${data.status} (${data.message})`
        const error = new Error(errorMessage)
        throw error
      }
      return data
    } catch (err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }

  static postData = async (url, formPayload) => {
    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(formPayload)
      })
      if (!response.ok) {
        if (response.status === 422) {
          return await response.json()
        } else {
          throw new Error(response.status)
        }
      }
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`)
    }
    return {}
  }

  static patchData = async (url, formPayload) => {
    try {
      const response = await fetch(url, {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(formPayload)
      })
      if (!response.ok) {
        if (response.status === 422) {
          return await response.json()
        } else {
          throw new Error(response.status)
        }
      }
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`)
    }
    return {}
  }

  static deleteData = async (url, formPayload) => {
    try {
      const response = await fetch(url, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(formPayload)
      })
      if (!response.ok) {
        if (response.status === 422) {
          return await response.json()
        } else {
          throw new Error(response.status)
        }
      }
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`)
    }
    return {}
  }

  static isValid = (fields, values, setErrors) => {
    let formErrors = {}
    fields.forEach(field => {
      if (values[field].trim() === "") {
        formErrors = { ...formErrors, [field]: `${field} must be filled in.` }
      }
    })

    setErrors(formErrors)
    return _.isEmpty(formErrors)
  }
}
