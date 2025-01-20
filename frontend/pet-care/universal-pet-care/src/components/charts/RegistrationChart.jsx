import React, { useState, useEffect } from "react";
import {
  ResponsiveContainer,
  LineChart,
  Line,
  CartesianGrid,
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
} from "recharts";
import { getAggregateUsersByMonthAndType } from "../user/UserService";

const RegistrationChart = () => {
  const [userData, setUserData] = useState([]);
  const [errorMessage, setErrorMessage] = useState(null);

  useEffect(() => {
    const getUsers = async () => {
      try {
        const response = await getAggregateUsersByMonthAndType();
        const userData = response.data;
        console.log("The userdata here :", userData);
        //Transform the backend data into the desired format
        const transformedData = Object.entries(userData).map(
          ([month, counts]) => {
            return {
              name: month,
              Veterinarians: counts.VET || 0,
              Patients: counts.PATIENT || 0,
            };
          }
        );
        setUserData(transformedData);
      } catch (error) {
        console.log("The error : ", error);
        setErrorMessage(error.message);
      }
    };
    getUsers();
  }, []);

  return (
    <ResponsiveContainer width='60%' height={400}>
      <h5 className='chart-title mb-5'>Users Registration Overview</h5>
      <BarChart data={userData}>
        <XAxis dataKey='name' angle={-50} textAnchor='end' height={60} />
        <YAxis />
        <Tooltip />
        <Legend />
        <Bar dataKey={"Veterinarians"} fill='#2f6a32' />
        <Bar dataKey={"Patients"} fill='#d26161' />
      </BarChart>
    </ResponsiveContainer>
  );
};

export default RegistrationChart;
