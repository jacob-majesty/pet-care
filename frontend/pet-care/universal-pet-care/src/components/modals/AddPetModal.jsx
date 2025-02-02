import React, { useState } from "react";
import { Modal, Button, Form, Col, Row } from "react-bootstrap";
import PetBreedSelector from "../pet/PetBreedSelector";
import PetTypeSelector from "../pet/PetTypeSelector";
import PetColorSelector from "../pet/PetColorSelector";

const AddPetModal = ({ show, onHide, onAddPet, appointmentId }) => {
  const [newPet, setNewPet] = useState({
    name: "",
    age: "",
    color: "",
    type: "",
    breed: "",
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
    console.log("New Pet Information: ", newPet);
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
              value={newPet.name}
              onChange={handleInputChange}
            />
          </Form.Group>
          <Form.Group controlId='petColor'>
            <Form.Label>Color</Form.Label>
            <PetColorSelector
              name='color'
              value={newPet.petColor}
              onChange={handleInputChange}
            />
          </Form.Group>
          <h5 className='text-center'>Pet Type and Breed</h5>
          <Form.Group controlId='petType'>
            <Form.Label>Type</Form.Label>
            <PetTypeSelector
              name='type'
              value={newPet.type}
              onChange={handleInputChange}
            />
          </Form.Group>
          <Form.Group controlId='petBreed'>
            <Form.Label>Breed</Form.Label>
            <PetBreedSelector
              name='breed' 
              petType={newPet.type}
              value={newPet.petBreed}
              onChange={handleInputChange}
            />
          </Form.Group>
          <Form.Group controlId='petAge'>
            <Form.Label>Age</Form.Label>
            <Form.Control
              type='number'
              name='age'
              value={newPet.age}
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
