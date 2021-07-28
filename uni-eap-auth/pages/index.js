import { signIn, signOut, useSession } from "next-auth/client";
import Head from "next/head";
import Link from "next/link";
import React from "react";
import styles from "../styles/Home.module.css";

export default function Home() {
  const [session, loading] = useSession();
  return (
    <div className={styles.container}>
      <Head>
        <title>UEAP</title>
        <link rel="icon" href="/favicon.iso" />
      </Head>
      <main className={styles.main}>
        <div>
          {loading && <p>Loading...</p>}
          {!session && (
            <>
              Not signed in <br />
              <br />
              <button
                onClick={() =>
                  signIn("github", { callbackUrl: "http://localhost:3000/" })
                }
              >
                Sign In with GitHub
              </button>
              <br />
              <button
                onClick={() =>
                  signIn("google", { callbackUrl: "http://localhost:3000/" })
                }
              >
                Sign In with Google
              </button>
              <br />
              <button
                onClick={() =>
                  signIn("twitter", { callbackUrl: "http://localhost:3000/" })
                }
              >
                Sign In with Twitter
              </button>
            </>
          )}
          {session && (
            <>
              Signed in as {session.user.name}
              <br />
              <button>
                <Link href="/secret">Secret</Link>
              </button>
              <br />
              <button onClick={() => signOut()}>Sign Out</button>
            </>
          )}
        </div>
      </main>
    </div>
  );
}
