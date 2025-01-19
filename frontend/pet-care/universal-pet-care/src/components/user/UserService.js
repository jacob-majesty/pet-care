import { api } from "../utils/api";

export async function getUserById(userId) {
  try {
    const result = await api.get(`/users/user/${userId}`);
    return result.data;
  } catch (error) {
    throw error;
  }
}

export async function registerUser(user) {
  try {
    const result = await api.post("/users/register", user);
    return result.data;
  } catch (error) {
    throw error;
  }
}

export async function changeUserPassword(
  userId,
  currentPassword,
  newPassword,
  confirmNewPassword
) {
  try {
    const requestData = { currentPassword, newPassword, confirmNewPassword };
    const result = await api.put(
      `/users/user/${userId}/change-password`,
      requestData
    );
    return result.data;
  } catch (error) {
    throw error;
  }
}
export async function updateUser(userData, userId) {
  try {
    const response = await api.put(`/users/user/${userId}/update`, userData);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function deleteUser(userId) {
  try {
    const response = await api.delete(`/users/user/${userId}/delete`);
    return response.data;
  } catch (error) {
    throw error;
  }
 }