// On importe la fonction apiRequest depuis api.ts.
// Elle nous permet de communiquer avec le backend Spring Boot.
import { apiRequest } from "./api";

// On importe le type Quete depuis notre fichier quest.ts.
// Cela permet à TypeScript de savoir à quoi ressemble une quête.
import type { Quete } from "../types/quest";


// Cette fonction permet de récupérer toutes les quêtes.

// "async" signifie que la fonction va effectuer
// une opération qui peut prendre un peu de temps.
// Ici, elle attend la réponse du backend.
export async function getQuetes(): Promise<Quete[]> {

  // On appelle apiRequest avec la route de notre backend.
  // Le backend possède cette route dans QueteController :
  // GET /api/quetes
  //
  // <Quete[]> indique que nous attendons
  // un tableau de quêtes en réponse.
  return apiRequest<Quete[]>("/api/quetes");
}


// Cette fonction permet de récupérer une seule quête
// grâce à son identifiant.
export async function getQueteById(id: number): Promise<Quete> {

  // On ajoute l'identifiant à la route.
  // Par exemple, si id = 3 :
  // /api/quetes/3
  //
  // <Quete> indique que le backend doit retourner
  // une seule quête.
  return apiRequest<Quete>(`/api/quetes/${id}`);
}


// Cette fonction permet de créer une nouvelle quête.
//
// Omit<Quete, "id"> signifie qu'on utilise tous les champs
// de Quete sauf "id".
// C'est normal car l'identifiant sera créé par le backend.
export async function createQuete(
  quete: Omit<Quete, "id">
): Promise<Quete> {

  // On envoie une requête POST vers /api/quetes.
  return apiRequest<Quete>("/api/quetes", {

    // POST sert à créer une nouvelle donnée.
    method: "POST",

    // JSON.stringify transforme notre objet JavaScript
    // en texte JSON compréhensible par Spring Boot.
    body: JSON.stringify(quete),
  });
}


// Cette fonction permet de modifier une quête existante.
export async function updateQuete(
  id: number,
  quete: Omit<Quete, "id">
): Promise<Quete> {

  // On envoie la requête vers la quête correspondant à son id.
  // Exemple : /api/quetes/3
  return apiRequest<Quete>(`/api/quetes/${id}`, {

    // PUT sert à modifier une donnée existante.
    method: "PUT",

    // On transforme les données de la quête en JSON
    // avant de les envoyer au backend.
    body: JSON.stringify(quete),
  });
}


// Cette fonction permet de supprimer une quête.
export async function deleteQuete(id: number): Promise<void> {

  // On envoie une requête DELETE vers la quête concernée.
  await apiRequest<void>(`/api/quetes/${id}`, {

    // DELETE sert à supprimer une donnée.
    method: "DELETE",
  });
}