import React from "react";
import { Accordion, Col, Card } from "react-bootstrap";
import { Link } from "react-router-dom";
import UserImage from "../common/UserImage";

import RatingStars from "../rating/RatingStars";

const VeterinarianCard = ({ vet }) => {
  return (
    <Col key={vet.id} className='mb-4 xs={12}'>
      <Accordion>
        <Accordion.Item eventKey='0'>
          <Accordion.Header>
            <div className='d-flex align-items-center'>
              <Link to={""}>
                <UserImage
                  userId={vet.id}
                  userPhoto={vet.photo}                 
                />
              </Link>
            </div>
            <div className='flex-grow-1 ml-3 px-5'>
              <Card.Title className='title'>
                Dr. {vet.firstName} {vet.lastName}
              </Card.Title>
              <Card.Title>
                <h6>{vet.specialization}</h6>
              </Card.Title>
              <Card.Text className='review rating-stars'>
                Reviews: <RatingStars rating={vet.averageRating}/> ({vet.totalReviewers})
              </Card.Text>
              <Link to={`/book-appointment/${vet.id}/new-appointment`} className="link">
                Book appointment
              </Link>
            </div>
          </Accordion.Header>
          <Accordion.Body>
            <div>
              <Link to={`/veterinarian/${vet.id}/veterinarian`} className='link-2'>
                See what people are saying about
              </Link>{" "}
              <span className='margin-left-space ms-2'>Dr.{vet.firstName}</span>
            </div>
          </Accordion.Body>
        </Accordion.Item>
      </Accordion>
    </Col>
  );
};

export default VeterinarianCard;
