import React from "react";
import "./AddAssociateScreen.css";

class AddAssociateScreen extends React.Component {
  constructor(props) {
    super(props);
    //state is the object where the data will be stored
    this.state = {
      eid: "EU ID",
      firstName: "First Name",
      lastName: "Last Name",
      teamId: "Team 1",
      position: "Position",
      email: "Email"
    };
  }
  changeHandler(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  //Once the Add New Associate button is pressed: 
  submitHandler(e) {
    e.preventDefault();
    console.log(this.state);
    //Alerts the user with the informaiton they have inputted
    alert(
      "A new associate was added with the following details:\nEU ID: " +
        this.state.eid +
        "\nFirst Name: " +
        this.state.firstName +
        "\nLast Name: " +
        this.state.lastName +
        "\nEmail: " +
        this.state.email +
        "\nPosition: " +
        this.state.position +
        "\nTeam: " +
        this.state.teamId
    );
    let URL = "http://localhost:8080";
    //Parse integers such as eid and teamId into accepted tyoes
    this.state.eid = parseInt(this.state.eid)
    this.state.teamId = parseInt(this.state.teamId)
   
    // post request method 
    let POST_REQUEST_BODY = {
      method: "POST",
      headers: {
      "Content-Type": "application/json",
      },
      body: JSON.stringify(this.state)
    };

    //fetch response from server, to check if the  associate has been added. 
    fetch(`${URL}/employees`, POST_REQUEST_BODY).then(async response => {
      var resp = await response;
      alert (resp.status)
      if (resp.status === 201) {
        alert("New Associate Added!");

        var data = resp.json();
      } else if (resp.status === 400) {
      } else {
       
      }
    });
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    this.setState({ [name]: value });
  }
  //Display 
  render() {
    const { eid, firstName, lastName, teamId, email, position } = this.state;
    return (
      <form onSubmit={this.submitHandler.bind(this)}>
        <h1>Add New Associate</h1>
        <h6>Fill in all the details below to add a new associate.</h6>
        EU ID:
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input
          type="text"
          name="eid"
          value={eid}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        First Name: &nbsp;&nbsp;&nbsp;&nbsp;
        <input
          type="text"
          name="firstName"
          value={firstName}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Last Name: &nbsp;&nbsp;&nbsp;&nbsp;
        <input
          type="text"
          name="lastName"
          value={lastName}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Email:
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input
          type="text"
          name="email"
          value={email}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Position:
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <select
          name="position"
          value={position}
          onChange={this.changeHandler.bind(this)}
        >
          <br />
          <option value="Associate">Associate</option>
          <option value="Scrum Master">Scrum Master</option>
          <option value="Admin">Admin</option>
          <option value="Manager">Manager</option>
        </select>
        <br />
        Team:
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <select
          name="teamId"
          value={teamId}
          onChange={this.changeHandler.bind(this)}
        >
          <br />
          <option value="1">Team 1</option>
          <option value="2">Team 2</option>
          <option value="3">Team 3</option>
          <option value="4">Team 4</option>
        </select>
        <br />
        <input type="submit" value="Add New Associate" />
      </form>
    );
  }
}

export default AddAssociateScreen;
