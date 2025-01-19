import { api } from "../utils/api";

export async function getVeterinarians() {
  try {
    const result = await api.get("/veterinarians/get-all-veterinarians");
    console.log("The result ", result);
    console.log("The result ", result.data);
    return result.data;
  } catch (error) {
    throw error;
  }
}

export async function findAvailableVeterinarians(searchParams) {
  try {
    const queryParams = new URLSearchParams(searchParams);

    const result = await api.get(
      `/veterinarians/search-veterinarian?${queryParams}`
    );
    return result.data;
  } catch (error) {
    throw error;
  }
}

export const getAllSpecializations = async () => {
  try {
    const response = await api.get("/veterinarians/vet/get-all-specialization");
    return response.data;
  } catch (error) {
    throw error;
  }
};
