import React from 'react'

const CardComponent = ({label, count, IconComponent}) => {
    return (
      <div className='admin-card'>
        <div className='card-inner'>
          {label}
          <IconComponent className='card-icon' />
        </div>
        <h3>{count}</h3>
      </div>
    );
}

export default CardComponent
