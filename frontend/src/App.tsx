// Imports first — JS needs to know these before running the rest
import { useEffect, useState } from "react";
import Header from "./components/Header";
import Dashboard from "./pages/Dashboard";
import QuestForm from "./pages/QuestForm";
import type { Quete } from "./types/quest";
import { getQuetes, deleteQuete } from "./services/questService";

function App() {

  // Tracks whether the creation form should be shown
  const [showQuestForm, setShowQuestForm] = useState(false);

  // Stores the list of quests fetched from the BK (...)
  const [quetes, setQuetes] = useState<Quete[]>([]);

  // Waiting for server response — shows loading
  const [isLoading, setIsLoading] = useState(true);

  // If error — shows the message
  const [error, setError] = useState<string | null>(null);

  // Runs code once, the first time the component appears
  useEffect(() => {

    // Function that waits for the server response and fetches the quests
    async function chargerQuetes() {

      try {

        // Shows the loading state
        setIsLoading(true);

        // Clears any previous error
        setError(null);

        // Service that talks to the BK
        // GET http://localhost:8080/api/quetes
        const data = await getQuetes();

        // Stores the received quests
        setQuetes(data);

      } catch {

        // If communication with the BK fails
        setError("Impossible de charger les quêtes.");

      } finally {

        // Either way, loading is done
        setIsLoading(false);
      }
    }

    // Calls the function defined above
    chargerQuetes();

  }, []);

  // Function to add a quest — opens the form
  const handleAddQuest = () => {
    setShowQuestForm(true);
  };

  // Function called when a quest is successfully created
  const handleQuestCreated = (createdQuest: Quete) => {

    // Adds the new quest to the existing list
    setQuetes((currentQuetes) => [
      ...currentQuetes,
      createdQuest,
    ]);

    // Closes the form — goes back to the dashboard
    setShowQuestForm(false);
  };

  // Function to delete a quest
  const handleQuestDeleted = async (id: number) => {

    try {

      // Asks the backend to delete the quest
      await deleteQuete(id);

      // Removes the deleted quest from the displayed list
      setQuetes((currentQuetes) =>
        currentQuetes.filter((quete) => quete.id !== id)
      );

    } catch (error) {

      // Displays the real error in the browser console
      console.error("Erreur lors de la suppression :", error);

      // Displays the error message on the page
      setError(
        error instanceof Error
          ? error.message
          : "Impossible de supprimer la quête."
      );
    }
  };

  return (
    <>
      {/* Function that runs when clicking Quests — goes back to the Dashboard */}
      <Header onQuetesClick={() => setShowQuestForm(false)} />

      {/* If true, show the form */}
      {showQuestForm ? (

        <QuestForm

          // Function called when clicking cancel
          onCancel={() => setShowQuestForm(false)}

          // Function called after successful creation in the BK
          onCreated={handleQuestCreated}

        />

      ) : (

        // Otherwise, show the Dashboard
        <Dashboard

          // Passes the fetched quests to the Dashboard
          quests={quetes}

          // Passes the function that opens the form
          onAddQuest={handleAddQuest}

          // Passes the loading state
          isLoading={isLoading}

          // Passes the error state
          error={error}

          // Passes the function that deletes a quest
          onDeleteQuest={handleQuestDeleted}

        />
      )}
    </>
  );
}

export default App;