import express from "express"
import Application from "../../../models/Application.js"

const applicationRouter = new express.Router()

applicationRouter.post("/", async (req, res) => {
  try {
    const newApplication = new Application(req.body)
    await newApplication.save()
    return res.status(201).json({ newApplication })
  } catch (error) {
    console.error(error)
    return res.status(422).json({ errors: error })
  }
})

export default applicationRouter