// On importe useState pour gérer les données qui changent dans le Dashboard.
import { useState } from "react";

// On importe le fichier CSS du Dashboard.
import "./Dashboard.css";

// On importe le type Quete.
import type { Quete } from "../types/quest";

// On définit les données que le Dashboard reçoit depuis App.tsx.
interface DashboardProps {
  // Tableau des quêtes récupérées depuis le backend.
  quests: Quete[];

  // Fonction permettant d'ouvrir le formulaire de création.
  onAddQuest: () => void;

  // Indique si les quêtes sont encore en chargement.
  isLoading: boolean;

  // Contient le message d'erreur s'il y en a une.
  error: string | null;

  // Fonction permettant de supprimer une quête.
  onDeleteQuest: (id: number) => void;
}

// Composant principal du Dashboard.
function Dashboard({
  quests,
  onAddQuest,
  isLoading,
  error,
  onDeleteQuest,
}: DashboardProps) {

  // Stocke la quête actuellement sélectionnée.
  const [selectedQuest, setSelectedQuest] = useState<Quete | null>(null);

  // Stocke le texte saisi dans la recherche.
  const [search, setSearch] = useState("");

  // Stocke le filtre de statut sélectionné.
  const [filter, setFilter] = useState("Toutes");

  // On filtre directement les quêtes reçues depuis App.tsx.
  const filteredQuests = quests.filter((quest) => {

    // On vérifie si le titre correspond à la recherche.
    const searchMatch = quest.titre
      .toLowerCase()
      .includes(search.toLowerCase());

    // On vérifie si le statut correspond au filtre.
    const filterMatch =
      filter === "Toutes" || quest.statut === filter;

    // La quête doit respecter les deux conditions.
    return searchMatch && filterMatch;
  });

  // Si les quêtes sont encore en chargement, on affiche un message.
  if (isLoading) {
    return (
      <main className="dashboard">
        <p>Chargement des quêtes...</p>
      </main>
    );
  }

  // Si une erreur est survenue pendant la récupération, on affiche le message.
  if (error) {
    return (
      <main className="dashboard">
        <p>{error}</p>
      </main>
    );
  }

  return (
    <main className="dashboard">

      {/* Introduction du Dashboard. */}
      <section className="dashboard-intro">

        {/* Titre principal. */}
        <h2>TABLEAU DES QUÊTES</h2>

        {/* Description du Dashboard. */}
        <p>
          Organisez les missions de la guilde, envoyez vos aventuriers
          à l’aventure et récoltez gloire et récompenses.
        </p>

        {/* Bouton permettant d'ajouter une quête. */}
        <button
          className="add-quest-button"
          onClick={onAddQuest}
        >
          + Ajouter une quête
        </button>

      </section>

      {/* Zone principale contenant la liste et le détail. */}
      <section className="quest-board">

        {/* Partie gauche contenant les quêtes. */}
        <div className="quest-list">

          {/* Titre de la liste. */}
          <h3>LISTE DES QUÊTES</h3>

          {/* Champ de recherche. */}
          <input
            className="quest-search"
            type="text"
            placeholder="Recherche de quête..."
            value={search}
            onChange={(event) => setSearch(event.target.value)}
          />

          {/* Boutons de filtrage. */}
          <div className="quest-filters">

            {/* Affiche toutes les quêtes. */}
            <button
              className={filter === "Toutes" ? "filter-active" : ""}
              onClick={() => setFilter("Toutes")}
            >
              Toutes
            </button>

            {/* Affiche uniquement les quêtes disponibles. */}
            <button
              className={filter === "Disponible" ? "filter-active" : ""}
              onClick={() => setFilter("Disponible")}
            >
              Disponible
            </button>

            {/* Affiche uniquement les quêtes en cours. */}
            <button
              className={filter === "En cours" ? "filter-active" : ""}
              onClick={() => setFilter("En cours")}
            >
              En cours
            </button>

            {/* Affiche uniquement les quêtes terminées. */}
            <button
              className={filter === "Terminée" ? "filter-active" : ""}
              onClick={() => setFilter("Terminée")}
            >
              Terminée
            </button>

          </div>

          {/* Conteneur des cartes de quêtes. */}
          <div className="quest-cards">

            {/* Si aucune quête ne correspond, on affiche ce message. */}
            {filteredQuests.length === 0 ? (

              <p>Aucune quête trouvée.</p>

            ) : (

              // Sinon, on parcourt les quêtes récupérées.
              filteredQuests.map((quest) => (

                // Une carte représente une quête.
                <article
                  key={quest.id}

                  // La couleur de la carte dépend maintenant du statut.
                  className={`quest-card ${quest.statut
                    .toLowerCase()
                    .replace(" ", "-")}`}

                  // Un clic sur la carte sélectionne la quête.
                  onClick={() => setSelectedQuest(quest)}
                >

                  {/* Contenu de la carte. */}
                  <div className="quest-card-content">

                    {/* Titre provenant du backend. */}
                    <h4>{quest.titre}</h4>

                    {/* Description provenant du backend. */}
                    <p>{quest.description}</p>

                    {/* Informations supplémentaires. */}
                    <div className="quest-card-info">

                      {/* Difficulté provenant du backend. */}
                      <span>
                        {quest.difficulte}
                      </span>

                      {/* Niveau requis provenant du backend. */}
                      <span>
                        Niveau {quest.niveauRequis}
                      </span>

                      {/* Statut provenant du backend. */}
                      <span
                        className={`status ${quest.statut
                          .toLowerCase()
                          .replace(" ", "-")}`}
                      >
                        {quest.statut}
                      </span>

                      {/* Bouton permettant de supprimer la quête. */}
                      <button
                        className="delete-button"
                        onClick={(event) => {

                          // Empêche le clic sur le bouton de sélectionner la carte.
                          event.stopPropagation();

                          // Demande au parent de supprimer la quête.
                          onDeleteQuest(quest.id);
                        }}
                      >
                        X
                      </button>

                    </div>

                  </div>

                </article>
              ))
            )}

          </div>

        </div>

        {/* Partie droite contenant les détails. */}
        <div className="selected-quest">

          {/* Titre de la partie détail. */}
          <h3>QUÊTE SÉLECTIONNÉE</h3>

          {/* Si aucune quête n'est sélectionnée. */}
          {!selectedQuest ? (

            <div className="empty-quest">

              {/* Icône de parchemin. */}
              <div className="quest-icon">
                📜
              </div>

              {/* Message principal. */}
              <p>
                Sélectionnez une quête
              </p>

              {/* Explication. */}
              <span>
                pour afficher ses informations et récompenses
              </span>

            </div>

          ) : (

            // Sinon, on affiche les informations de la quête sélectionnée.
            <div className="quest-selected-content">

              {/* Titre de la quête. */}
              <h4>
                {selectedQuest.titre}
              </h4>

              {/* Description de la quête. */}
              <p>
                {selectedQuest.description}
              </p>

              {/* Difficulté. */}
              <div>
                <strong>Difficulté :</strong>{" "}
                {selectedQuest.difficulte}
              </div>

              {/* Niveau requis. */}
              <div>
                <strong>Niveau :</strong>{" "}
                {selectedQuest.niveauRequis}
              </div>

              {/* Statut. */}
              <div>
                <strong>Statut :</strong>{" "}
                {selectedQuest.statut}
              </div>

              {/* Récompenses. */}
              <div className="rewards">

                {/* Récompense en or. */}
                <span>
                  OR : {selectedQuest.recompenseOr} PO
                </span>

                {/* Récompense en XP. */}
                <span>
                  XP : {selectedQuest.recompenseXp} XP
                </span>

              </div>

            </div>
          )}

        </div>

      </section>

    </main>
  );
}

// On exporte le Dashboard pour pouvoir l'utiliser dans App.tsx.
export default Dashboard;