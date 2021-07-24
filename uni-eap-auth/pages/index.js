import { signIn, signOut, useSession } from "next-auth/client";

export default function Home() {
  const { session, loading } = useSession();
  return (
    <div>
      {loading && <p>Loading...</p>}
      {!session && (
        <>
          Not signed in <br />
          <button
            onClick={() =>
              signIn("google", { callbackUrl: "http://localhost:3000/" })
            }
          >
            Sign In
          </button>
        </>
      )}
      {session && (
        <>
          Signed in as {session.user.name}
          <br />
          <button onClick={() => signOut()}>Sign Out</button>
        </>
      )}
    </div>
  );
}
