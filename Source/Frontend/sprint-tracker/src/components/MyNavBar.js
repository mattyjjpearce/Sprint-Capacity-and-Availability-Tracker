import React from 'react';
import { Navbar, Form, FormControl, Button } from 'react-bootstrap';


class MyNavBar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      user: {},
    };
    this.employeeIDInput = React.createRef();
  }

  async getUser(id) {
    let response = await fetch(`http://localhost:8080/employees/${id}`);
    return await response.json();
  }

  async validateUser(id) {
    let response = await fetch(`http://localhost:8080/employees/${id}`);
    let status = await response.status;
    return status === 200;
  }

  async switchUser(event) {
    event.preventDefault();
    let id = this.employeeIDInput.current.value;
    let valid = await this.validateUser(id);
    if (valid) {
      let user = await this.getUser(id);
      this.props.onChangeUser(user);
      this.setState({ user });
    } else {
      alert('error');
    }
  }

  unsetUser(event) {
    event.preventDefault();
    this.props.onChangeUser(undefined);
  }
  

  render() {
    return (  
      <Navbar bg="info" variant="dark" expand="dark">
        <Navbar.Brand className="mr-auto">
          Sprint Tracker
        </Navbar.Brand>
        {this.props.user === undefined
          ? <Form 
              inline 
              onSubmit={this.switchUser.bind(this)}
            >
              <FormControl 
                type="text" 
                placeholder="Employee ID" 
                className="mr-sm-2"
                ref={this.employeeIDInput}
              />
              <Button type="submit" variant="outline-light">Switch</Button>
            </Form>
          : <div> 
              <Navbar.Text className="text-light mr-sm-2">
                Signed in as: {this.state.user.firstName + ' ' + this.state.user.lastName}
              </Navbar.Text>
             
              <Button 
                onClick={this.unsetUser.bind(this)}
                variant="danger"
              >
                Exit
              </Button>
            </div>
        }
      </Navbar>
    );
  }
}

export default MyNavBar;
