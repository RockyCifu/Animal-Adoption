import express from "express"
import clientRouter from "./clientRouter.js"
import petsRouter from './api/v1/petsRouter.js'
import surrrenderRouter from './api/v1/surrenderRouter.js'
import applicationRouter from './api/v1/applicationRouter.js'

const rootRouter = new express.Router()

rootRouter.use("/api/v1/pets", petsRouter)

rootRouter.use("/api/v1/surrender", surrrenderRouter)

rootRouter.use("/api/v1/application", applicationRouter)

rootRouter.use("/", clientRouter)

export default rootRouter
