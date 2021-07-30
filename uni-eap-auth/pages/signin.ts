import {
  providers,
  signIn,
  getSession,
  csrfToken,
  CtxOrReq,
} from "next-auth/client";

export default function SignIn({ providers, csrfToken }) {
  return;
}

SignIn.getInitialProps = async (context) => {
  const { req, res } = context;
  const session = await getSession({ req });
  // check if user is already signed in
  if (session && res && session.accessToken) {
    // if signed in already, redirect user back to home
    res.writeHead(302, {
      Location: "/",
    });
    res.end();
    return;
  }
  return {
    session: undefined,
    providers: await providers(),
    csrfToken: await csrfToken(context),
  };
};
