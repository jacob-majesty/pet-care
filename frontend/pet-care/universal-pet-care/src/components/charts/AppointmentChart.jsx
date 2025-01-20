import React, { useState, useEffect } from "react";
import CustomPieChart from "./CustomPieChart";
import { getAppointmentsSummary } from "../appointment/AppointmentService";

const AppointmentChart = () => {
  const [appointmentData, setAppointmentData] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    const getAppointmentsInfo = async () => {
      try {
        const response = await getAppointmentsSummary();
          setAppointmentData(response.data); 
        console.log("yeeeeeeeeeh" ,response.data)  
      } catch (error) {     
        setErrorMessage(error.message);
      }
    };
    getAppointmentsInfo();
  }, []);
  return (
    <div>
      <h5 className='mb-4 chart-title'>Appointments Overview</h5>
      <CustomPieChart data={appointmentData} />
    </div>
  );
};

export default AppointmentChart;
