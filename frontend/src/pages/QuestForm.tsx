// On importe useState pour gérer les champs du formulaire.
import { useState } from "react";

// On importe le CSS du formulaire.
import "./QuestForm.css";

// On importe la fonction qui envoie la quête au backend.
import { createQuete } from "../services/questService";

// On importe le type Quete qui correspond au DTO du backend.
import type { Quete } from "../types/quest";

// On définit les fonctions que App.tsx doit fournir au formulaire.
interface QuestFormProps {
  // Fonction appelée lorsque l'utilisateur annule.
  onCancel: () => void;

  // Fonction appelée après la création réussie de la quête.
  onCreated: (quest: Quete) => void;
}

// Composant du formulaire de création d'une quête.
function QuestForm({
  onCancel,
  onCreated,
}: QuestFormProps) {

  // Stocke le titre saisi.
  const [titre, setTitre] = useState("");

  // Stocke la description saisie.
  const [description, setDescription] = useState("");

  // Stocke le statut sélectionné.
  const [statut, setStatut] = useState("");

  // Stocke la difficulté sélectionnée.
  const [difficulte, setDifficulte] = useState("");

  // Stocke le niveau requis saisi.
  const [niveauRequis, setNiveauRequis] = useState("");

  // Stocke la récompense en or.
  const [recompenseOr, setRecompenseOr] = useState("");

  // Stocke la récompense en XP.
  const [recompenseXp, setRecompenseXp] = useState("");

  // Permet d'afficher un message pendant l'envoi.
  const [isSubmitting, setIsSubmitting] = useState(false);

  // Stocke une éventuelle erreur du backend.
  const [error, setError] = useState<string | null>(null);

  // Fonction exécutée lorsque l'utilisateur clique sur "Créer la quête".
  const handleSubmit = async (
    event: React.FormEvent<HTMLFormElement>
  ) => {

    // Empêche le rechargement de la page.
    event.preventDefault();

    // Active l'état de chargement.
    setIsSubmitting(true);

    // Supprime une ancienne erreur.
    setError(null);

    try {

      // On prépare exactement les données attendues par le backend.
      const newQuest: Omit<Quete, "id"> = {

        // Titre de la quête.
        titre: titre,

        // Description de la quête.
        description: description,

        // Statut de la quête.
        statut: statut,

        // Difficulté de la quête.
        difficulte: difficulte,

        // Conversion du niveau en nombre.
        niveauRequis: Number(niveauRequis),

        // Conversion de l'or en nombre.
        recompenseOr: Number(recompenseOr),

        // Conversion de l'XP en nombre.
        recompenseXp: Number(recompenseXp),
      };

      // On envoie réellement la quête à Spring Boot.
      const createdQuest = await createQuete(newQuest);

      // On prévient App.tsx que la création a fonctionné.
      onCreated(createdQuest);

    } catch {

      // Message affiché si le backend refuse ou si la connexion échoue.
      setError("Impossible de créer la quête.");

    } finally {

      // On arrête l'état de chargement.
      setIsSubmitting(false);
    }
  };

  // Affichage du formulaire.
  return (
    <main className="quest-form-background">

      {/* Bouton permettant de revenir au Dashboard. */}
      <button
        type="button"
        className="back-button"
        onClick={onCancel}
      >
        Retour
      </button>

      {/* Introduction du formulaire. */}
      <section className="quest-form-intro">

        {/* Titre de la page. */}
        <h2>
          AJOUTER UNE NOUVELLE QUÊTE
        </h2>

        {/* Petite description. */}
        <p>
          Créez une nouvelle mission pour la guilde
        </p>

      </section>

      {/* Formulaire de création. */}
      <form
        className="quest-form"
        onSubmit={handleSubmit}
      >

        {/* Champ du titre. */}
        <div className="form-field full-width">

          {/* Label du titre. */}
          <label htmlFor="titre">
            Titre de la quête *
          </label>

          {/* Input du titre. */}
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

        {/* Champ de description. */}
        <div className="form-field full-width">

          {/* Label de la description. */}
          <label htmlFor="description">
            Description de la mission *
          </label>

          {/* Input de la description. */}
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

        {/* Ligne contenant le statut et la difficulté. */}
        <div className="form-row">

          {/* Champ du statut. */}
          <div className="form-field">

            {/* Label du statut. */}
            <label htmlFor="statut">
              Statut *
            </label>

            {/* Sélecteur du statut. */}
            <select
              id="statut"
              value={statut}
              onChange={(event) =>
                setStatut(event.target.value)
              }
              required
            >

              {/* Valeur par défaut. */}
              <option value="">
                Sélectionnez
              </option>

              {/* Statut disponible. */}
              <option value="Disponible">
                Disponible
              </option>

              {/* Statut en cours. */}
              <option value="En cours">
                En cours
              </option>

              {/* Statut terminée. */}
              <option value="Terminée">
                Terminée
              </option>

            </select>

          </div>

          {/* Champ de difficulté. */}
          <div className="form-field">

            {/* Label de la difficulté. */}
            <label htmlFor="difficulte">
              Difficulté *
            </label>

            {/* Sélecteur de difficulté. */}
            <select
              id="difficulte"
              value={difficulte}
              onChange={(event) =>
                setDifficulte(event.target.value)
              }
              required
            >

              {/* Valeur par défaut. */}
              <option value="">
                Sélectionnez
              </option>

              {/* Difficulté facile. */}
              <option value="Facile">
                Facile
              </option>

              {/* Difficulté moyenne. */}
              <option value="Moyen">
                Moyen
              </option>

              {/* Difficulté difficile. */}
              <option value="Difficile">
                Difficile
              </option>

            </select>

          </div>

        </div>

        {/* Ligne contenant les récompenses et le niveau. */}
        <div className="form-row">

          {/* Champ du niveau requis. */}
          <div className="form-field">

            {/* Label du niveau. */}
            <label htmlFor="niveauRequis">
              Niveau requis *
            </label>

            {/* Input du niveau. */}
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

          {/* Champ de récompense en or. */}
          <div className="form-field">

            {/* Label de l'or. */}
            <label htmlFor="recompenseOr">
              Or (PO)
            </label>

            {/* Input de l'or. */}
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

          {/* Champ de récompense XP. */}
          <div className="form-field">

            {/* Label de l'XP. */}
            <label htmlFor="recompenseXp">
              Expérience (XP)
            </label>

            {/* Input de l'XP. */}
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

        {/* Affichage d'une erreur éventuelle. */}
        {error && (
          <p className="form-error">
            {error}
          </p>
        )}

        {/* Boutons du formulaire. */}
        <div className="form-actions">

          {/* Bouton Annuler. */}
          <button
            type="button"
            className="cancel-button"
            onClick={onCancel}
          >
            Annuler
          </button>

          {/* Bouton de création. */}
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

// On exporte le composant.
export default QuestForm;