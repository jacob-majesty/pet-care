import React, { Fragment } from "react";
import { Card } from "react-bootstrap";



const UserImage = ({ userId, placeholder,userPhoto, altText = "User photo" }) => {
  return (
    <Fragment>
      {userPhoto ? (
        <Card.Img
          src={`data:image/png;base64, ${userPhoto}`}
          className='user-image'
          alt={altText}
        />
      ) : (
        <Card.Img src={placeholder} className='user-image' alt={altText} />
      )}
    </Fragment>
  );
};

export default UserImage;
