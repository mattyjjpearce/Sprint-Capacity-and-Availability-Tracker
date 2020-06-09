import React from "react";
import ReactDOM from "react-dom";
import "./AddTeamsScreen.css";

class AddTeamsScreen extends React.Component {
  constructor(props) {
    super(props);
    //state is the object where the data will be stored
    this.state = {
      teamId: "Team ID",
      teamName: "Team Name",
      teamManagerId: "Team Manager ID",
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
      "A new team was added with the following details:\nTeam ID: " +
        this.state.teamId +
        "\nTeam Name: " +
        this.state.teamName +
        "\nTeam Manager ID: " +
        this.state.teamManagerId
    );

    let URL = "http://localhost:8080";
    //Parse integers such as eid and teamId into accepted types
    this.state.teamId = parseInt(this.state.id)
    this.state.teamManagerId = parseInt(this.state.teamManagerId)
    


   
    // post request method 
    let POST_REQUEST_BODY = {
      method: "POST",
      headers: {
      "Content-Type": "application/json",
      },
      body: JSON.stringify(this.state)
    };

    //fetch response from server, to check if the holiday has been added. 
    fetch(`${URL}/teams`, POST_REQUEST_BODY).then(async response => {
      var resp = await response;
      alert (resp.status)
      if (resp.status === 201) {
        alert("New team Added!");

        var data = resp.json();
      } else if (resp.status === 400) {
      } else {
       
      }
    });
  }

  
  //Display 
  render() {
    const { teamId, teamName, teamManagerId} = this.state;
    return (
      <form onSubmit={this.submitHandler.bind(this)}>
        <h1>Add Team</h1>
        
        <h6>Fill in all the details below to add a new team.</h6>
        Team ID:
        <input
          type="text"
          name="teamId"
          value={teamId}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Team Name: 
        <input
          type="text"
          name="teamName"
          value={teamName}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Team Manager ID:
        <input
          type="text"
          name="teamManagerId"
          value={teamManagerId}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        <input type="submit" value="Add Team" />
      </form>
    );
  }
}

export default AddTeamsScreen;
