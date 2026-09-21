// Shape of a quest — front/back contract
export interface Quete {
  id: number;
  titre: string;
  difficulte: string;
  description: string;
  niveauRequis: number;
  statut: string;
  recompenseOr: number;
  recompenseXp: number;
}