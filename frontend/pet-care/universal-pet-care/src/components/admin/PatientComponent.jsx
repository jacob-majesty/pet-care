import React, { useEffect, useState } from "react";
import { Table, OverlayTrigger, Tooltip, Row, Col } from "react-bootstrap";
import { Link } from "react-router-dom";
import AlertMessage from "../common/AlertMessage";
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import { getPatients } from "../patient/PatientService";

const PatientComponent = () => {
  const [patients, setPatients] = useState([]);

  const {
    successMessage,
    setSuccessMessage,
    errorMessage,
    setErrorMessage,
    showSuccessAlert,
    setShowSuccessAlert,
    showErrorAlert,
    setShowErrorAlert,
  } = UseMessageAlerts();

  const fetchPatients = async () => {
    try {
      const response = await getPatients();
      setPatients(response.data);
    } catch (error) {
      setErrorMessage(error.message);
      setShowErrorAlert(true);
    }
  };

  useEffect(() => {
    fetchPatients();
  }, []);

  return (
    <main>
      <h4 className=''>List of Patiemts</h4>

      <Row>
        <Col>The patient filter is coming here</Col>
        <Col>
          {" "}
          {showErrorAlert && (
            <AlertMessage type='danger' message={errorMessage} />
          )}
          {showSuccessAlert && (
            <AlertMessage type='success' message={successMessage} />
          )}
        </Col>
      </Row>

      <Table>
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Gender</th>
            <th>Registered on</th>
            <th colSpan={2}>Action</th>
          </tr>
        </thead>
        <tbody>
          {patients.map((patient, index) => (
            <tr>
              <td>{patient.id}</td>
              <td>{patient.firstName}</td>
              <td>{patient.lastName}</td>
              <td>{patient.email}</td>
              <td>{patient.phoneNumber}</td>
              <td>{patient.gender}</td>
              <td>{patient.createdAt}</td>
              <td>
                <OverlayTrigger
                  overlay={
                    <Tooltip id={`tooltip-view-${index}`}>
                      View patient details
                    </Tooltip>
                  }>
                  <Link
                    to={`/user-dashboard/${patient.id}/my-dashboard`}
                    className='text-info'>
                    <BsEyeFill />
                  </Link>
                </OverlayTrigger>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </main>
  );
};

export default PatientComponent;
