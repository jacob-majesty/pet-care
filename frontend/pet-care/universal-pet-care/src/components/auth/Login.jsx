import React, { useState } from "react";
import { BsPersonFill, BsLockFill } from "react-icons/bs";
import { Container, Row, Col, Card, Form, Button, InputGroup } from "react-bootstrap"
import { Link } from "react-router-dom";

const Login = () => {
  const [credentials, setCredentials] = useState({
    email: "",
    password: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCredentials((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };
  return (
    <Container className='mt-5'>
      <Row className='justify-content-center'>
        <Col sm={6}>
          <Card>
            <Card.Body>
              <Card.Title className='text-center mb-4'>Login</Card.Title>
              <Form>
                <Form.Group className='mb-3' controlId='username'>
                  <Form.Label>Username</Form.Label>
                  <InputGroup>
                    <InputGroup.Text>
                      <BsPersonFill /> {/* User icon */}
                    </InputGroup.Text>
                    <Form.Control
                      type='text'
                      name='email'
                      value={credentials.email}
                      onChange={handleInputChange}
                    />
                  </InputGroup>
                </Form.Group>

                <Form.Group className='mb-3' controlId='password'>
                  <Form.Label>Password</Form.Label>
                  <InputGroup>
                    <InputGroup.Text>
                      <BsLockFill /> {/* Lock icon */}
                    </InputGroup.Text>
                    <Form.Control
                      type='password'
                      name='password'
                      value={credentials.password}
                      onChange={handleInputChange}
                    />
                  </InputGroup>
                </Form.Group>
                <Button
                  variant='outline-primary'
                  type='submit'
                  className='w-100'>
                  Login
                </Button>
              </Form>
              <div className='text-center mt-2'>
                Don't have an accoount yet?{" "}
                <Link
                  to={"/register-user"}
                  style={{ textDecoration: "none" }}>
                  Register here
                </Link>               
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default Login;
