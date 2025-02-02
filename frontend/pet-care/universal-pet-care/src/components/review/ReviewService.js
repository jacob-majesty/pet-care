import { api } from "../utils/api";

export async function addReview(vetId, reviewerId, reviewData) {
  try {
    const token = localStorage.getItem("authToken")
    const response = await api.post(
      `reviews/submit-review?vetId=${vetId}&reviewerId=${reviewerId}`,
      reviewData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    return response.data;
  } catch (error) {
    throw error;
  }
}
