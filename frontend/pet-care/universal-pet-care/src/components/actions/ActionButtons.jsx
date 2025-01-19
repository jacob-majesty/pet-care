import React from "react";
import { Button } from "react-bootstrap";

const ActionButtons = ({
  title,
  variant,
  onClick,
  disabled,  
  className = "",
}) => {
  return (
    <Button
      variant={variant}
      size='sm'
      disabled={disabled}
      onClick={onClick}
      className={className}>
      { title}
    </Button>
  );
};

export default ActionButtons;
