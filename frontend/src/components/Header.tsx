import logo from "../assets/logo_QA.png";
import "./Header.css";

interface HeaderProps {
  onQuetesClick: () => void;
}

function Header({ onQuetesClick }: HeaderProps) {
  return (
    <header>
      <div className="logo-container">
        <img src={logo} alt="Logo QA" />
        <h1>
          <span>GUILD</span>BOARD
        </h1>
      </div>

      <nav>
        <button className="active" onClick={onQuetesClick}>
          Quêtes
        </button>
        <button>Aventuriers</button>
      </nav>
    </header>
  );
}

export default Header;