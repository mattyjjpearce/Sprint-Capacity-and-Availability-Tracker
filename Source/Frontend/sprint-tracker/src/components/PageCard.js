import React from 'react';
import { Card } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';

export default function PageCard({ 
  name, 
  description, 
  link, 
  access
}) {
  let history = useHistory();
  return (
    <Card onClick={e => history.push(link)}>
      <Card.Body>
        <Card.Title>{name}</Card.Title>
        <Card.Text>{description}</Card.Text>
      </Card.Body>
    </Card>
  );
}
