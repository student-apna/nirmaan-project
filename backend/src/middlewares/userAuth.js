export const userAuth = (req, res, next) => {
  const token = req.headers["authorization"]?.split(" ")[1]; // Bearer token

  if (!token) {
    return res.json({ success: false, message: "Not authorized" });
  }

  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    req.userId = decoded.id; // token से user id extract
    next();
  } catch (error) {
    return res.json({ success: false, message: "Invalid token" });
  }
};
