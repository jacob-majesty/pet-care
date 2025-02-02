import { api } from "../utils/api";

export async function getAllPetTypes() {
  try {
    const result = await api.get("/pets/get-types");
    return result.data;
  } catch (error) {
    throw error;
  }
}


export async function getAllPetColors() {
  try {
    const result = await api.get("/pets/get-pet-colors");
    return result.data;
  } catch (error) {
    throw error;
  }
}

export async function getAllPetBreeds(petType) {
  try {
    const result = await api.get(`/pets/get-pet-breeds?petType=${petType}`);
    return result.data;
  } catch (error) {
    throw error;
  }
}

export const updatePet = async (petId, updatedPet) => {
  try {
    const response = await api.put(`/pets/pet/${petId}/update`, updatedPet);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const deletePet = async (id) => {
  try {
    const response = await api.delete(`/pets/pet/${id}/delete`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export async function addPet(appointmentId, petData) {
  try {   
    const petsArray = Array.isArray(petData) ? petData : [petData];
    const response = await api.put(
      `/pets/save-pets?appointmentId=${appointmentId}`, petsArray,
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    return response.data;
  } catch (error) {
    throw error;
  }
}


