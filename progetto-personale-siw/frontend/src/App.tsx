import { useEffect, useState } from "react";

type UserInfo =
  | { loggedIn: false }
  | { loggedIn: true; username: string; roles: string[] };

type CsrfInfo = {
  token: string;
  headerName: string;
};

export default function App() {
  const [user, setUser] = useState<UserInfo | null>(null);
  const [csrfToken, setCsrfToken] = useState<CsrfInfo | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Promise.all([
      fetch("/api/me").then(r => r.json()),
      fetch("/api/csrf").then(r => r.json()),
    ])
      .then(([userData, csrfData]) => {
        setUser(userData);
        setCsrfToken(csrfData);
        setLoading(false);
      })
      .catch(() => setLoading(false));
  }, []);

  const handleLogout = async () => {
    if (!csrfToken) return;
    await fetch("/logout", {
      method: "POST",
      headers: {
        [csrfToken.headerName]: csrfToken.token,
      },
    });
    window.location.href = "/";
  };

  if (loading) return <p>Caricamento...</p>;

  const isUser = user?.loggedIn ? user.roles.includes("USER") : false;
  const isAdmin = user?.loggedIn ? user.roles.includes("ADMIN") : false;

  return (
    <div>
      <header>
        {user?.loggedIn
          ? <span>Sei loggato come: <b>{user.username}</b></span>
          : <span>Non sei loggato</span>
        }
      </header>

      <h1>Home</h1>

      <nav>
        <h2><a href="/corsi">I nostri corsi</a></h2>
        <h2><a href="/staff">Il nostro staff</a></h2>
        <h2><a href="/tipi_abbonamenti">Gli abbonamenti che offriamo</a></h2>

        {!user?.loggedIn && <>
          <h2><a href="/login">Effettua il Login</a></h2>
          <h2><a href="/register">Registrati</a></h2>
        </>}

        {(isUser || isAdmin) &&
          <h2><a href="/utente/profilo">Pagina personale Utente</a></h2>}

        {isAdmin && <>
          <h2><a href="/esercizi">Esercizi</a></h2>
          <h2><a href="/admin/schede">Schede allenamento</a></h2>
          <h2><a href="/admin/elenco">PT</a></h2>
        </>}

        {user?.loggedIn &&
          <button onClick={handleLogout}>Logout</button>
        }
      </nav>
    </div>
  );
}