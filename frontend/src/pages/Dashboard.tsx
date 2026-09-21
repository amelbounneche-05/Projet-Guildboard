// We import useState to manage the data that changes in the Dashboard.
import { useState } from "react";
import "./Dashboard.css";
import type { Quete } from "../types/quest";

// We define the data that the Dashboard receives from App.tsx.
interface DashboardProps {
  quests: Quete[];
  onAddQuest: () => void;
  isLoading: boolean;
  error: string | null;
  onDeleteQuest: (id: number) => void;
}

function Dashboard({
  quests,
  onAddQuest,
  isLoading,
  error,
  onDeleteQuest,
}: DashboardProps) {

  // Stores the currently selected quest.
  const [selectedQuest, setSelectedQuest] = useState<Quete | null>(null);

  // Stores the text typed in the search field.
  const [search, setSearch] = useState("");

  // Stores the selected status filter.
  const [filter, setFilter] = useState("Toutes");

  // We directly filter the quests received from App.tsx.
  const filteredQuests = quests.filter((quest) => {

    // We check whether the title matches the search.
    const searchMatch = quest.titre
      .toLowerCase()
      .includes(search.toLowerCase());

    // We check whether the status matches the filter.
    const filterMatch =
      filter === "Toutes" || quest.statut === filter;

    // The quest must meet both conditions.
    return searchMatch && filterMatch;
  });

  if (isLoading) {
    return (
      <main className="dashboard">
        <p>Chargement des quêtes...</p>
      </main>
    );
  }

  if (error) {
    return (
      <main className="dashboard">
        <p>{error}</p>
      </main>
    );
  }

  return (
    <main className="dashboard">
      <section className="dashboard-intro">
        <h2>TABLEAU DES QUÊTES</h2>
        <p>
          Organisez les missions de la guilde, envoyez vos aventuriers
          à l’aventure et récoltez gloire et récompenses.
        </p>

        <button
          className="add-quest-button"
          onClick={onAddQuest}
        >
          + Ajouter une quête
        </button>

      </section>

      <section className="quest-board">
        <div className="quest-list">
          <h3>LISTE DES QUÊTES</h3>

          <input
            className="quest-search"
            type="text"
            placeholder="Recherche de quête..."
            value={search}
            onChange={(event) => setSearch(event.target.value)}
          />

          <div className="quest-filters">

            <button
              className={filter === "Toutes" ? "filter-active" : ""}
              onClick={() => setFilter("Toutes")}
            >
              Toutes
            </button>

            <button
              className={filter === "Disponible" ? "filter-active" : ""}
              onClick={() => setFilter("Disponible")}
            >
              Disponible
            </button>

            <button
              className={filter === "En cours" ? "filter-active" : ""}
              onClick={() => setFilter("En cours")}
            >
              En cours
            </button>

            <button
              className={filter === "Terminée" ? "filter-active" : ""}
              onClick={() => setFilter("Terminée")}
            >
              Terminée
            </button>

          </div>

          <div className="quest-cards">

            {/* If no quest matches, we display this message. */}
            {filteredQuests.length === 0 ? (

              <p>Aucune quête trouvée.</p>

            ) : (

              // Otherwise, we loop through the retrieved quests.
              filteredQuests.map((quest) => (

                // One card represents one quest.
                <article
                  key={quest.id}

                  className={`quest-card ${quest.statut
                    .toLowerCase()
                    .replace(" ", "-")}`}

                  onClick={() => setSelectedQuest(quest)}
                >

                  <div className="quest-card-content">
                    <h4>{quest.titre}</h4>
                    <p>{quest.description}</p>
                    <div className="quest-card-info">

                      {/* Difficulty coming from the backend. */}
                      <span>
                        {quest.difficulte}
                      </span>

                      {/* Required level coming from the backend. */}
                      <span>
                        Niveau {quest.niveauRequis}
                      </span>

                      {/* Status coming from the backend. */}
                      <span
                        className={`status ${quest.statut
                          .toLowerCase()
                          .replace(" ", "-")}`}
                      >
                        {quest.statut}
                      </span>

                      {/* Button used to delete the quest. */}
                      <button
                        className="delete-button"
                        onClick={(event) => {

                          // Prevents the button click from selecting the card.
                          event.stopPropagation();

                          // Asks the parent to delete the quest.
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

        <div className="selected-quest">
          <h3>QUÊTE SÉLECTIONNÉE</h3>

          {/* If no quest is selected. */}
          {!selectedQuest ? (

            <div className="empty-quest">
              <div className="quest-icon">
                📜
              </div>
              <p>
                Sélectionnez une quête
              </p>

              <span>
                pour afficher ses informations et récompenses
              </span>

            </div>

          ) : (

            // Otherwise, we display the selected quest's information.
            <div className="quest-selected-content">
              <h4>
                {selectedQuest.titre}
              </h4>

              <p>
                {selectedQuest.description}
              </p>

              <div>
                <strong>Difficulté :</strong>{" "}
                {selectedQuest.difficulte}
              </div>

              <div>
                <strong>Niveau :</strong>{" "}
                {selectedQuest.niveauRequis}
              </div>

              <div>
                <strong>Statut :</strong>{" "}
                {selectedQuest.statut}
              </div>

              <div className="rewards">

                <span>
                  OR : {selectedQuest.recompenseOr} PO
                </span>

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

export default Dashboard;