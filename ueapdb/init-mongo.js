db.createUser({
  user: "admin",
  pwd: "pwd123",
  roles: [
    {
      role: "readWrite",
      db: "ueap-auth",
    },
  ],
});
