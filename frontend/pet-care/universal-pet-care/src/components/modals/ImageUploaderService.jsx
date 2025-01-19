import { api } from "../utils/api";

export async function updateUserPhoto(photoId, photoData) {
  try {
    const response = await api.put(
      `/photos/photo/${photoId}/update`,
      photoData,
      {
        headers: {
          "Content-Type": "application/octet-stream",
        },
      }
    );
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function uploadUserPhoto(userId, photoData) {
  try {
    const formData = new FormData();
    formData.append("file", photoData);
    formData.append("userId", userId);
    const response = await api.post("/photos/photo/upload", formData);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function deleteUserPhoto(photoId, userId) {
    try {
      const response = await api.delete(
        `/photos/photo/${photoId}/${userId}/delete`
      );
      return response.data;
    } catch (error) {
      throw error;
    }
  }


