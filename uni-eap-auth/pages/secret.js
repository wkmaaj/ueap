import { signOut, useSession } from "next-auth/client";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function Secret() {
  const [session, loading] = useSession();
  const [content, setContent] = useState();

  useEffect(() => {
    const fetchData = async () => {
      const res = await fetch("/api/secret");
      const json = await res.json();
      if (json.content) setContent(json.content);
    };
    fetchData();
  }, [session]);

  // Used to display nothing if the page is not yet rendered
  if (typeof window !== "undefined" && loading) return null;

  if (!session) {
    return (
      <main>
        <div>
          <h1>You aren't signed in, please sign in first</h1>
          <button>
            <Link href="/">Login</Link>
          </button>
        </div>
      </main>
    );
  }

  return (
    <main>
      <div>
        <h1>Welcome to the protected page {session.user.name}</h1>
        <button>
          <Link href="/">Home</Link>
        </button>
        <br />
        <button
          onClick={() => signOut({ callbackUrl: "http://localhost:3000/" })}
        >
          Sign Out
        </button>
      </div>
    </main>
  );
}
