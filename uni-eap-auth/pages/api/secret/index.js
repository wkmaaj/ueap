import { getSession } from "next-auth/client";

export default async (req, res) => {
  //   console.log(JSON.stringify(req));
  const session = await getSession({ req });
  //   console.log(`Waleed ${JSON.stringify(session)}`);
  if (session) {
    res.send({ content: "Welcome to the secret page" });
  } else {
    res.send({ error: "You need to be signed in" });
  }
};
