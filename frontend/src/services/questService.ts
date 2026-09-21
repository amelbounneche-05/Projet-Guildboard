// Imports functions to communicate with the Spring Boot server
import { apiRequest } from "./api";
import type { Quete } from "../types/quest";

// Fetches all quests - waiting for BK response
export async function getQuetes(): Promise<Quete[]> {

  // ENvoi requête avec apiRequest côté backend auquel on attend un tableau de quête
  return apiRequest<Quete[]>("/api/quetes");
}

// Fetches a single quest via (id) - returns one quest
export async function getQueteById(id: number): Promise<Quete> {
  return apiRequest<Quete>(`/api/quetes/${id}`);
}

// Create quest - no id (backend gives it)
export async function createQuete(
  quete: Omit<Quete, "id">
): Promise<Quete> {

  return apiRequest<Quete>("/api/quetes", {
    method: "POST",

    // Translation between JS and JSON (Spring Boot)
    body: JSON.stringify(quete),
  });
}

//Function to modify an existing quest
export async function updateQuete(
  id: number,
  quete: Omit<Quete, "id">
): Promise<Quete> {

  // Sends the request to the quest matching this id
  return apiRequest<Quete>(`/api/quetes/${id}`, {

    // Replace the data
    method: "PUT",

    // Translates the quest into JSON before sending to BK
    body: JSON.stringify(quete),
  });
}

export async function deleteQuete(id: number): Promise<void> {

  // Sends a DELETE request to the target quest
  await apiRequest<void>(`/api/quetes/${id}`, {

    method: "DELETE",
  });
}