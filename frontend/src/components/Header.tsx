import logo from "../assets/logo_QA.png";
import "./Header.css";

// Create an interface to define the props
// that the Header component will receive.
interface HeaderProps {

   // Function that takes no parameters
  // and returns no value.
  onQuetesClick: () => void;
}

// Get the "onQuetesClick" prop
// and specify that it must follow the HeaderProps interface.
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