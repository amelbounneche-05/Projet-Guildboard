//mettre les import en haut (relation)
import logo from "../assets/logo_QA.png";
import "./Header.css";

function Header() {
  return ( // affichage
    <header>
      <div className="logo-container"> 
        <img src={logo} alt="Logo QA" />    
        <h1>
          <span>GUILD</span>BOARD
        </h1>
      </div>

      <nav>
        <button className="active">Quêtes</button> 
        <button>Aventuriers</button>
      </nav>
    </header>
  );
}

export default Header;