// Adresse de notre serveur backend Spring Boot.
// Spring Boot fonctionne maintenant sur le port 8081.
const API_URL = "http://localhost:8080";

// Fonction générale utilisée pour communiquer avec le backend.
//
// <T> permet à TypeScript de connaître le type
// de la réponse que le backend va nous envoyer.
//
// Exemple :
// apiRequest<Quete[]>("/api/quetes")
// signifie que la réponse sera un tableau de Quete.
export async function apiRequest<T>(
  endpoint: string,
  options?: RequestInit
): Promise<T> {

  // "fetch" permet d'envoyer une requête HTTP au backend.
  //
  // `${API_URL}${endpoint}` assemble les deux parties :
  // http://localhost:8081 + /api/quetes
  //
  // Ce qui donne :
  // http://localhost:8081/api/quetes
  const response = await fetch(`${API_URL}${endpoint}`, {

    // On récupère les options éventuellement données
    // par questService.ts.
    //
    // Par exemple :
    // method: "POST"
    // ou
    // method: "DELETE"
    ...options,

    // On indique au backend que les données envoyées
    // sont au format JSON.
    headers: {
      "Content-Type": "application/json",

      // On conserve également les éventuels headers
      // envoyés dans les options.
      ...options?.headers,
    },
  });

  // "response.ok" vaut true si la réponse HTTP
  // est correcte (par exemple 200 ou 201).
  //
  // Si le backend renvoie une erreur (400, 404, 500...),
  // on déclenche une erreur.
  if (!response.ok) {

    // response.status contient le code HTTP.
    // Exemple : 404, 400, 500...
    throw new Error(`Erreur HTTP : ${response.status}`);
  }

  // Si le backend renvoie 204, cela signifie :
  // la requête a réussi mais il n'y a aucune donnée à retourner.
  //
  // C'est notamment le cas pour :
  // DELETE /api/quetes/{id}
  if (response.status === 204) {

    // Il n'y a donc pas de JSON à lire.
    return undefined as T;
  }

  // Pour les autres réponses, le backend nous renvoie du JSON.
  //
  // "response.json()" transforme la réponse
  // en objet JavaScript utilisable par React.
  return response.json();
}