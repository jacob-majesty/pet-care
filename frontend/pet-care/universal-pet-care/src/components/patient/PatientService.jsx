import { api } from "../utils/api";
export async function getPatients() {
  try {
    const result = await api.get("/patients/get-all-patients");
    return result.data;
  } catch (error) {
    throw error;
  }
}

