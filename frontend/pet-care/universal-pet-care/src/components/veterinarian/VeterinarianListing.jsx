import React, { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import VeterinarianCard from "./VeterinarianCard";
import { getVeterinarians } from "./VeterinarianService";
import VeterinarianSearch from "./VeterinarianSearch";
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import NoDataAvailable from "../common/NoDataAvailable";
import LoadSpinner from "../common/LoadSpinner";

const VeterinarianListing = () => {
  const [veterinarians, setVeterinarians] = useState([]);
  const [allVeterinarians, setAllVeterinarians] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const { errorMessage, setErrorMessage, showErrorAlert, setShowErrorAlert } =
    UseMessageAlerts();

  useEffect(() => {
    getVeterinarians()
      .then((data) => {
        setVeterinarians(data.data);
        setAllVeterinarians(data.data);
        setTimeout(() => {
          setIsLoading(false);
        }, 1000);
      })
      .catch((error) => {
        setErrorMessage(error.response.data.message);
        setShowErrorAlert(true);
      });
  }, []);

  const handleSearchResult = (veterinarians) => {
    if (veterinarians === null) {
      setVeterinarians(allVeterinarians);
    } else if (Array.isArray(veterinarians) && veterinarians.length > 0) {
      setVeterinarians(veterinarians);
    } else {
      setVeterinarians([]);
    }
  };

  if (isLoading) {
    return (
      <div>
        <LoadSpinner />
      </div>
    );
  }

  return (
    <Container>
      {veterinarians && veterinarians.length > 0 ? (
        <React.Fragment>
          <Row className='justify-content-center'>
            <h2 className='text-center mb-4 mt-4'>Meet Our Veterinarians</h2>
          </Row>

          <Row className='justify-content-center'>
            <Col md={4}>
              <VeterinarianSearch onSearchResult={handleSearchResult} />
            </Col>
            <Col md={7}>
              {veterinarians.map((vet, index) => (
                <VeterinarianCard key={index} vet={vet} />
              ))}
            </Col>
          </Row>
        </React.Fragment>
      ) : (
        <NoDataAvailable
          dataType={" veterinarians data "}
          message={errorMessage}
        />
      )}
    </Container>
  );
};

export default VeterinarianListing;
