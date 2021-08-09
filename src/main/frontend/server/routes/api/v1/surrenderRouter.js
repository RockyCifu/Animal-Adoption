import express from "express"
import Surrender from '../../../models/Surrender.js'

const surrenderRouter = new express.Router()

surrenderRouter.post("/", async (req, res) => {
  try {
    const newSurrender = new Surrender(req.body)
    await newSurrender.save()
    return res.status(201).json({ newSurrender })
  } catch (error) {
    console.error(error)
    return res.status(422).json({ errors: error })
  }
})

export default surrenderRouter
