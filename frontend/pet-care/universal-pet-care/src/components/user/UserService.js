import { api } from "../utils/api";

export async function getUserById(userId) {
  try {
    const result = await api.get(`/users/user/${userId}`);  
    return result.data;
  } catch (error) {   
    throw error;
  }
}
