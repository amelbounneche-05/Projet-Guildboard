// On importe useEffect pour lancer une action lorsque le composant est chargé.
// On importe useState pour stocker les données et les états de l'application.
import { useEffect, useState } from "react";

// On importe notre Header.
import Header from "./components/Header";

// On importe les pages que l'on affiche.
import Dashboard from "./pages/Dashboard";
import QuestForm from "./pages/QuestForm";

// On importe le type Quete créé dans types/quest.ts.
import type { Quete } from "./types/quest";

// On importe la fonction qui récupère les quêtes depuis le backend.
import { getQuetes } from "./services/questService";

// Composant principal de notre application.
function App() {

  // Permet de savoir si on affiche le formulaire de création
  // ou le Dashboard.
  const [showQuestForm, setShowQuestForm] = useState(false);

  // Stocke les quêtes récupérées depuis le backend.
  //
  // Au début, le tableau est vide car nous n'avons pas encore
  // récupéré les données.
  const [quetes, setQuetes] = useState<Quete[]>([]);

  // Permet de savoir si le chargement des quêtes est en cours.
  const [isLoading, setIsLoading] = useState(true);

  // Stocke un éventuel message d'erreur.
  //
  // "string | null" signifie :
  // - string → il y a un message d'erreur
  // - null → il n'y a pas d'erreur
  const [error, setError] = useState<string | null>(null);

  // useEffect permet d'exécuter du code lorsque le composant App
  // est chargé pour la première fois.
  useEffect(() => {

    // Fonction asynchrone qui va récupérer les quêtes.
    async function chargerQuetes() {

      try {

        // On indique que le chargement commence.
        setIsLoading(true);

        // On supprime une ancienne erreur éventuelle.
        setError(null);

        // On appelle le service qui communique avec le backend.
        //
        // getQuetes() va envoyer :
        // GET http://localhost:8081/api/quetes
        const data = await getQuetes();

        // On place les quêtes reçues dans notre state.
        setQuetes(data);

      } catch {

        // Si la communication avec le backend échoue,
        // on affiche un message d'erreur.
        setError("Impossible de charger les quêtes.");

      } finally {

        // Dans tous les cas, le chargement est terminé.
        setIsLoading(false);
      }
    }

    // On lance la fonction.
    chargerQuetes();

  }, []);

  // Fonction appelée lorsqu'on veut ouvrir le formulaire
  // de création d'une nouvelle quête.
  const handleAddQuest = () => {

    // On affiche QuestForm.
    setShowQuestForm(true);
  };

  // Fonction appelée lorsque QuestForm a réussi à créer
  // une nouvelle quête dans le backend.
  const handleQuestCreated = (createdQuest: Quete) => {

    // On ajoute la nouvelle quête au tableau existant.
    setQuetes((currentQuetes) => [
      ...currentQuetes,
      createdQuest,
    ]);

    // On ferme le formulaire.
    setShowQuestForm(false);
  };

  // On retourne l'interface de notre application.
  return (
    <>
      {/* Header présent sur toutes les pages. */}
      <Header />

      {/* Si showQuestForm vaut true, on affiche le formulaire. */}
      {showQuestForm ? (

        // Formulaire de création d'une quête.
        <QuestForm

          // Fonction permettant de revenir au Dashboard.
          onCancel={() => setShowQuestForm(false)}

          // Fonction appelée après la création réussie
          // de la quête dans le backend.
          onCreated={handleQuestCreated}

        />

      ) : (

        // Sinon, on affiche le Dashboard.
        <Dashboard

          // On transmet les quêtes récupérées au Dashboard.
          quests={quetes}

          // On transmet la fonction permettant d'ouvrir le formulaire.
          onAddQuest={handleAddQuest}

          // On transmet l'état de chargement.
          isLoading={isLoading}

          // On transmet l'éventuelle erreur.
          error={error}

        />
      )}
    </>
  );
}

// On exporte App pour pouvoir l'utiliser dans main.tsx.
export default App;