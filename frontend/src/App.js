import "./App.css";

import { Link } from "react-router-dom";

export default function App() {
  return (
    <div style={{ paddingLeft: "1rem" }}>
      <h1>SLAST</h1>
      <nav
        style={{
          borderBottom: "solid 1px",
          paddingBottom: "1rem",
        }}
      >
        <Link to="/slastice">Slastice</Link> |{" "}
        <Link to="/zaposlenici">Zaposlenici</Link>
      </nav>
    </div>
  );
}
