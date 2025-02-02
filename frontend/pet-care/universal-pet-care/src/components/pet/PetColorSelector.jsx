import React, { useEffect, useState } from "react";
import { Form, Col } from "react-bootstrap";
import AddItemModal from "../modals/AddItemModal";
import { getAllPetColors } from "./PetService";

const PetColorSelector = ({ value, onChange }) => {
  const [petColors, setPetColors] = useState([]);
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    const fetchAllPetColors = async () => {
      try {
        const response = await getAllPetColors();
        setPetColors(response.data);
      } catch (error) {
        console.error(error.message);
      }
    };
    fetchAllPetColors();
  }, []);

  //1. handle color change
  const handleColorChange = (e) => {
    if (e.target.value === "add-new-item") {
      setShowModal(true);
    } else {
     // onChange({ target: { name: "color", value: e.target.value } });
      onChange(e);
    }
  };
  //2. handle save new item
  const handleSaveNewItem = (newItem) => {
    if (newItem && !petColors.includes(newItem)) {
      setPetColors([...petColors, newItem]);
      onChange({ target: { name: "color", value: newItem } });
    }
  };

  const handleClose = () => {
    setShowModal(false);
  };

  return (
    <React.Fragment>
      <Form.Group as={Col} controlId='petColor' className='mb-2'>
        <Form.Control
          as='select'
          name='color'
          value={value}
          required
          onChange={handleColorChange}>
          <option value=''>...select pet color...</option>
          {petColors.map((color) => (
            <option key={color} value={color}>
              {color}
            </option>
          ))}
          <option value='add-new-item'>Add New</option>
        </Form.Control>
      </Form.Group>
      <AddItemModal
        show={showModal}
        handleClose={handleClose}
        handleSave={handleSaveNewItem}
        itemLabel='Color'
      />
    </React.Fragment>
  );
};

export default PetColorSelector;
