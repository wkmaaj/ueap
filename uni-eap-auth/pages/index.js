import { signIn, signOut, useSession } from "next-auth/client";
import styles from "../styles/Home.module.css";

export default function Home() {
  const [session, loading] = useSession();
  return (
    <main className={styles.main}>
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
    </main>
  );
}
