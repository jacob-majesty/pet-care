import React, { useState } from "react";
import DeleteConfirmationModal from "../modals/DeleteConfirmationModal";
import AlertMessage from "../common/AlertMessage";
import { Button, Table } from "react-bootstrap";
import { BsPencilFill, BsTrashFill } from "react-icons/bs";
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import EditablePetRow from "./EditablePetRow";
import { updatePet, deletePet } from "./PetService";

const PetsTable = ({
  pets,
  onPetsUpdate,
  isEditable,
  isPatient,
  appointmentId,
}) => {
  const [editModeId, setEditModeId] = useState(null);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [petToDelete, setPetToDelete] = useState(null);

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

  const handleEditClick = (petId) => {
    setEditModeId(petId);
  };

  const handleCancel = () => {
    setEditModeId(null);
  };

  const handleShowDeleteModal = (petId) => {
    setPetToDelete(petId);
    setShowDeleteModal(true);
  };

  const handleDeletePet = async () => {
    if (petToDelete) {
      try {
        const response = await deletePet(petToDelete);
        onPetsUpdate(appointmentId);
        setSuccessMessage(response.message);
        setShowDeleteModal(false);
        setShowSuccessAlert(true);
      } catch (error) {
        setErrorMessage(error.message);
        setShowErrorAlert(true);
      }
    }
  };

  const handleSavePetUpdate = async (petId, updatedPet) => {
    try {
      const response = await updatePet(petId, updatedPet);
      onPetsUpdate(appointmentId);
      setSuccessMessage(response.message);
      setEditModeId(null);
      setShowSuccessAlert(true);
    } catch (error) {
      setErrorMessage(error.message);
      setShowErrorAlert(true);
    }
  };

  return (
    <section>
      <DeleteConfirmationModal
        show={showDeleteModal}
        onHide={() => setShowDeleteModal(false)}
        onConfirm={handleDeletePet}
        itemToDelete='pet'
      />
      {showErrorAlert && <AlertMessage type='danger' message={errorMessage} />}
      {showSuccessAlert && (
        <AlertMessage type='success' message={successMessage} />
      )}

      <Table striped bordered hover size='sm'>
        <thead>
          <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Breed</th>
            <th>Color</th>
            <th>Age</th>
            {isPatient && <th colSpan={3}>Actions</th>}
          </tr>
        </thead>
        <tbody>
          {Array.isArray(pets) &&
            pets.map((pet, index) =>
              editModeId === pet.id ? (
                <EditablePetRow
                  key={index}
                  pet={pet}
                  index={index}
                  onSave={handleSavePetUpdate}
                  onCancel={handleCancel}
                />
              ) : (
                <tr key={pet.id}>
                  <td>{pet.name}</td>
                  <td>{pet.type}</td>
                  <td>{pet.breed}</td>
                  <td>{pet.color}</td>
                  <td>{pet.age}</td>

                  {isPatient && (
                    <React.Fragment>
                      <td>
                        <Button
                          className='btn btn-sm btn-warning'
                          disabled={!isEditable}
                          onClick={() => handleEditClick(pet.id)}>
                          <BsPencilFill />
                        </Button>
                      </td>
                      <td>
                        <Button
                          className='btn btn-sm btn-danger'
                          disabled={!isEditable}
                          onClick={() => handleShowDeleteModal(pet.id)}>
                          <BsTrashFill />
                        </Button>
                      </td>
                    </React.Fragment>
                  )}
                </tr>
              )
            )}
        </tbody>
      </Table>
    </section>
  );
};

export default PetsTable;


//1. Add functionality to add new pets to the appointment after booking.
// Hints:
  
/* 
a:  You might want to add a modal to popup AND recieve the 
inputs when the add button is cliked.


b: You might want to add a new empty table row bellow to accept the
 inputs when the add button is clicked.
 
  */

 /* ============================================================================== */
 
 //2. Edit the deletePet method / function to check the number
// of pets associated with the appointment befor deletion.
 
// The user should not be allowed to delete pet if there is only
//one pet associated with the appointment

//Hints:

/* 
a: You might want to do the check at the backend and return the appropriate
 message when the contion is not met. or you might want to do it at the frontend
  and hide the delete button if the delete condition is not met

*/
