import React, { useState, useEffect } from "react"
import ApplicationTile from "./ApplicationTile.js"
import DBUtils from "../utils/DBUtils.js"

const ApplicationList = () => {
  const [applications, setApplications] = useState([])

  const getApplications = async () => {
    const data = await DBUtils.fetchData(`api/v1/pending_applications`)
    setApplications(data)
  }

  useEffect(() => {
    getApplications()
  }, [])

  const handleRefresh = () => {
    getApplications()
  }

  const applicationTiles = applications.map(application => {
    return (
      <ApplicationTile
        key={application.id}
        application={application}
        handleRefresh={handleRefresh}
      />
    )
  })

  return (
    <>
      <h1>Adoption Applications</h1>
      {applicationTiles.length !== 0 ? (
        <ul>{applicationTiles}</ul>
      ) : (
        <h2>There are currently no pending applications.</h2>
      )}
    </>
  )
}

export default ApplicationList
