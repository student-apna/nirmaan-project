import express from "express";

import userRouter from "./routes/userRoutes.js";
import { initDb } from "./db/sequelize.js"; // central db
import dotenv from "dotenv";

dotenv.config();
const app = express();
app.use(express.json());
app.use('/api/user',userRouter);

const startServer = async () => {
  try {
    await initDb(); // Initialize DB and models
    app.listen(4000, () => {
      console.log(" server is running on port 4000");
    });
  } catch (err) {
    console.error( err);
  }
};


startServer();