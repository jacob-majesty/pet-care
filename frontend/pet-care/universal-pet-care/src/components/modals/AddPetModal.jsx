import React, { useState } from "react";
import { Modal, Button, Form, Col, Row } from "react-bootstrap";
import PetBreedSelector from "../pet/PetBreedSelector";
import PetTypeSelector from "../pet/PetTypeSelector";
import PetColorSelector from "../pet/PetColorSelector";

const AddPetModal = ({ show, onHide, onAddPet, appointmentId }) => {
  const [newPet, setNewPet] = useState({
    name: "",
    type: "",
    breed: "",
    color: "",
    age: "",
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setNewPet((prevPet) => ({
      ...prevPet,
      [name]: value,
    }));
  };

  const handleAddPet = () => {
    onAddPet(appointmentId, newPet);
    setNewPet({
      name: "",
      type: "",
      breed: "",
      color: "",
      age: "",
    });
  };

  return (
    <Modal show={show} onHide={onHide}>
      <Modal.Header closeButton>
        <Modal.Title>Add New Pet</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group controlId='petName'>
            <Form.Label>Name</Form.Label>
            <Form.Control
              type='text'
              name='name'
              value={newPet.petName}
              onChange={handleInputChange}
            />
            <Form.Group as={Col} className='mb-2 mt-2'>
              <Form.Label>Color</Form.Label>
              <PetColorSelector
                value={newPet.petColor}
                onChange={handleInputChange}
              />
            </Form.Group>
            <h5 className='text-center'>Pet Type and Breed</h5>
            <fieldset className='field-set mb-2'>            
              <Form.Group as={Row} className='mb-2 d-flex'>
                <Col>
                  <PetTypeSelector
                    value={newPet.petType}
                    onChange={handleInputChange}
                  />
                </Col>
                <Col>
                  <PetBreedSelector
                    petType={newPet.petType}
                    value={newPet.petBreed}
                    onChange={handleInputChange}
                  />
                </Col>
              </Form.Group>
            </fieldset>
          </Form.Group>
          <Form.Group controlId='petAge'>
            <Form.Label>Age</Form.Label>
            <Form.Control
              type='number'
              name='age'
              value={newPet.petAge}
              onChange={handleInputChange}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant='secondary' onClick={onHide}>
          Close
        </Button>
        <Button variant='info' onClick={handleAddPet}>
          Add Pet
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AddPetModal;
