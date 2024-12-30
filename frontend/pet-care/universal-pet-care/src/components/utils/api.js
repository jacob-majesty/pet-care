import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:9193/api/v1",
});
