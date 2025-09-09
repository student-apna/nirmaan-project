import { dbConnection } from "./dbconnection.js";
import createUserModel from "../models/userModel.js";

// Async function to get sequelize instance and models
export const initDb = async () => {
  const sequelize = await dbConnection("auth", "postgres", "aftab");
  const User = createUserModel(sequelize);
  await sequelize.sync({ alter: true });
  console.log("Database & models synced");
  return { sequelize, User };
};