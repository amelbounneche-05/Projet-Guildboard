// On crée une interface appelée "Quete".
// Elle décrit la forme d'une quête venant de notre API.
export interface Quete {

  // Identifiant unique de la quête.
  // "number" signifie que cette valeur est un nombre.
  id: number;

  // Titre de la quête.
  // "string" signifie que cette valeur est du texte.
  titre: string;

  // Niveau de difficulté de la quête.
  // Exemple : "Facile", "Moyen" ou "Difficile".
  difficulte: string;

  // Description de la quête.
  description: string;

  // Niveau minimum requis pour réaliser la quête.
  // Exemple : 2 signifie qu'il faut au minimum le niveau 2.
  niveauRequis: number;

  // Statut actuel de la quête.
  // Exemple : "Disponible", "En cours" ou "Terminée".
  statut: string;

  // Quantité d'or donnée comme récompense.
  recompenseOr: number;

  // Quantité d'XP donnée comme récompense.
  recompenseXp: number;
}