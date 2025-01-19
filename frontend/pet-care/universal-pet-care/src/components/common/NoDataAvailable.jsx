import React from "react";

const NoDataAvailable = ({ dataType, errorMessage }) => {
  return (
    <div className='text-center mt-5'>
      <h4>No {dataType} available at the moment</h4>
      {errorMessage && <p className='text-danger'>{errorMessage}</p>}
    </div>
  );
};

export default NoDataAvailable;
