import { api } from "../utils/api";

export async function bookAppointment(senderId, recipientId, appointmentRequest) {
    try {
      const result = await api.post(
        `/appointments/book-appointment?senderId=${senderId}&recipientId=${recipientId}`,
        appointmentRequest
      );
        console.log("The result from here :",result);
      return result.data;
    } catch (error) {
      throw error;
    }
  
}

/*   `/appointments/book-appointment/${senderId}/${recipientId}` */