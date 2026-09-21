import { useState } from "react";
import "./QuestForm.css";
import { createQuete } from "../services/questService";
import type { Quete } from "../types/quest";

// Defines the functions that App.tsx must provide to the form.
interface QuestFormProps {
  // Function called when the user cancels.
  onCancel: () => void;

  // Function called after the quest is successfully created.
  onCreated: (quest: Quete) => void;
}

function QuestForm({
  onCancel,
  onCreated,
}: QuestFormProps) {

  const [titre, setTitre] = useState("");
  const [description, setDescription] = useState("");
  const [statut, setStatut] = useState("");
  const [difficulte, setDifficulte] = useState("");
  const [niveauRequis, setNiveauRequis] = useState("");
  const [recompenseOr, setRecompenseOr] = useState("");
  const [recompenseXp, setRecompenseXp] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [error, setError] = useState<string | null>(null);

  // Function executed when the user submits the form.
  const handleSubmit = async (
    event: React.FormEvent<HTMLFormElement>
  ) => {

    // Prevents the page from reloading.
    event.preventDefault();

    // Activates the loading state.
    setIsSubmitting(true);

    // Clears any previous error.
    setError(null);

    try {

      // Prepares the data expected by the backend.
      const newQuest: Omit<Quete, "id"> = {

        titre: titre,
        description: description,
        statut: statut,
        difficulte: difficulte,
        niveauRequis: Number(niveauRequis),
        recompenseOr: Number(recompenseOr),
        recompenseXp: Number(recompenseXp),
      };

      // Sends the quest to the Spring Boot backend.
      const createdQuest = await createQuete(newQuest);

      // Informs App.tsx that the creation was successful.
      onCreated(createdQuest);

    } catch {

      // Displays a message if the creation fails.
      setError("Impossible de créer la quête.");

    } finally {

      // Stops the loading state.
      setIsSubmitting(false);
    }
  };

  return (
    <main className="quest-form-background">
      <section className="quest-form-intro">
        <h2>
          AJOUTER UNE NOUVELLE QUÊTE
        </h2>

        <p>
          Créez une nouvelle mission pour la guilde
        </p>

      </section>

      {/* Quest creation form. */}
      <form
        className="quest-form"
        onSubmit={handleSubmit}
      >

        <div className="form-field full-width">
          <label htmlFor="titre">
            Titre de la quête *
          </label>

          <input
            id="titre"
            type="text"
            placeholder="Ex : Transporter la marchandise dans le village"
            value={titre}
            onChange={(event) =>
              setTitre(event.target.value)
            }
            required
          />

        </div>

        {/* Description field. */}
        <div className="form-field full-width">

          {/* Description label. */}
          <label htmlFor="description">
            Description de la mission *
          </label>

          {/* Field used to enter the quest description. */}
          <input
            id="description"
            type="text"
            placeholder="Détailler la mission pour guider l'aventurier"
            value={description}
            onChange={(event) =>
              setDescription(event.target.value)
            }
            required
          />

        </div>

        {/* Row containing the status and difficulty. */}
        <div className="form-row">

          <div className="form-field">
            <label htmlFor="statut">
              Statut *
            </label>

            {/* Status selector. */}
            <select
              id="statut"
              value={statut}
              onChange={(event) =>
                setStatut(event.target.value)
              }
              required
            >

              <option value="">
                Sélectionnez
              </option>

              <option value="Disponible">
                Disponible
              </option>

              <option value="En cours">
                En cours
              </option>

              <option value="Terminée">
                Terminée
              </option>

            </select>

          </div>

          {/* Difficulty field. */}
          <div className="form-field">

            <label htmlFor="difficulte">
              Difficulté *
            </label>

            {/* Difficulty selector. */}
            <select
              id="difficulte"
              value={difficulte}
              onChange={(event) =>
                setDifficulte(event.target.value)
              }
              required
            >

              <option value="">
                Sélectionnez
              </option>

              <option value="Facile">
                Facile
              </option>

              <option value="Moyen">
                Moyen
              </option>

              <option value="Difficile">
                Difficile
              </option>

            </select>

          </div>

        </div>

        {/* Row containing the level and rewards. */}
        <div className="form-row">

          {/* Required level field. */}
          <div className="form-field">

            <label htmlFor="niveauRequis">
              Niveau requis *
            </label>

            {/* Required level field. */}
            <input
              id="niveauRequis"
              type="number"
              min="1"
              placeholder="Ex : 20"
              value={niveauRequis}
              onChange={(event) =>
                setNiveauRequis(event.target.value)
              }
              required
            />

          </div>

          {/* Gold reward field. */}
          <div className="form-field">

            <label htmlFor="recompenseOr">
              Or (PO)
            </label>

            {/* Gold reward field. */}
            <input
              id="recompenseOr"
              type="number"
              min="0"
              placeholder="Ex : 20"
              value={recompenseOr}
              onChange={(event) =>
                setRecompenseOr(event.target.value)
              }
            />

          </div>

          {/* XP reward field. */}
          <div className="form-field">

            <label htmlFor="recompenseXp">
              Expérience (XP)
            </label>

            {/* XP reward field. */}
            <input
              id="recompenseXp"
              type="number"
              min="1"
              placeholder="Ex : 20"
              value={recompenseXp}
              onChange={(event) =>
                setRecompenseXp(event.target.value)
              }
              required
            />

          </div>

        </div>

        {/* Displays an error if the creation fails. */}
        {error && (
          <p className="form-error">
            {error}
          </p>
        )}

        {/* Contains the action buttons. */}
        <div className="form-actions">

          {/* Cancels the creation and returns to the Dashboard. */}
          <button
            type="button"
            className="cancel-button"
            onClick={onCancel}
          >
            Annuler
          </button>

          <button
            type="submit"
            className="create-button"
            disabled={isSubmitting}
          >
            {isSubmitting
              ? "Création..."
              : "Créer la quête"}
          </button>

        </div>

      </form>

    </main>
  );
}

export default QuestForm;