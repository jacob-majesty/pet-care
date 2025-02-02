import React from 'react'
import { Spinner } from 'react-bootstrap';

const LoadSpinner = ({variant = "success"}) => {
  return (
    <div
      className='d-flex justify-content-center align-items-center mt-5'
      style={{ height: "100%" }}>
      <Spinner animation='border' variant={variant} />
    </div>
  );
}

export default LoadSpinner
