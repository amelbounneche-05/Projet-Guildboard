const API_URL = "http://localhost:8080";
export async function apiRequest<T>(
  endpoint: string,
  options?: RequestInit
): Promise<T> {

  // "fetch" is used to send an HTTP request to the backend.
  const response = await fetch(`${API_URL}${endpoint}`, {

    // Add all the options that were provided (spread operator).
    ...options,

    // Tell the backend that the data being sent is in JSON format.
    headers: {
      "Content-Type": "application/json",
      ...options?.headers,
    },
  });

  // HTTP response - display an error if the request failed.
  if (!response.ok) {
    throw new Error(`Erreur HTTP : ${response.status}`);
  }

  // If BK returns 204 - successful - no data to return.
  if (response.status === 204) {

    // There is therefore no JSON to read.
    return undefined as T;
  }

  // For other responses BK sends JSON, which is converted into usable JS data.
  return response.json();
}