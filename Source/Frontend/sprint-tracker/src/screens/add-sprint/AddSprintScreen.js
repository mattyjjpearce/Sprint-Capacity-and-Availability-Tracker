import React from "react";
import ReactDOM from "react-dom";
import "./AddSprintScreen.css";

class AddSprintScreen extends React.Component {
  constructor(props) {
    super(props);
    //state is the object where the data will be stored
    this.state = {
      sprintId: "Sprint Id",
      sprintDescription: "Sprint Description",
      startDate: "Start Date",
      sprintLength: "Sprint Length",
      pointsPlanned: "Points Planned",
      pointsCompleted: "Points Completed",
      teamId: "Team Id",
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
      "A new Sprint was added with the following details:\nSprint ID: " +
        this.state.sprintId +
        "\nSprint Description: " +
        this.state.sprintDescription +
        "\nStart Date: " +
        this.state.startDate +
        "\nSprint Length: " +
        this.state.sprintLength +
        "\nPoints Planned: " +
        this.state.pointsPlanned +
        "\nPoints Completed: " +
        this.state.pointsCompleted +
        "\nTeam ID: " +
        this.state.teamID
    );
    let URL = "http://localhost:8080";
    //Parse integers such as eid and teamId into accepted types
    this.state.sprintLength = parseInt(this.state.sprintLength)
    this.state.sprintId = parseInt(this.state.sprintId)
    this.state.startDate = parseInt(this.state.startDate)
    this.state.pointsPlanned = parseInt(this.state.pointsPlanned)
    this.state.pointsCompleted = parseInt(this.state.pointsCompleted)
    this.state.teamId = parseInt(this.state.teamId)


   
    // post request method 
    let POST_REQUEST_BODY = {
      method: "POST",
      headers: {
      "Content-Type": "application/json",
      },
      body: JSON.stringify(this.state)
    };

    //fetch response from server, to check if the holiday has been added. 
    fetch(`${URL}/sprints`, POST_REQUEST_BODY).then(async response => {
      var resp = await response;
      alert (resp.status)
      if (resp.status === 201) {
        alert("New Sprint Added!");

       // var data = resp.json();
      } else if (resp.status === 400) {
      } else {
       
      }
    });
  }

  
  //Display 
  render() {
    const { sprintId, sprintDescription, startDate, sprintLength, pointsPlanned, pointsCompleted, teamId } = this.state;
    return (
      <form onSubmit={this.submitHandler.bind(this)}>
        <h1>Add Sprint</h1>
        
        <h6>Fill in all the details below to add a new sprint.</h6>
        Sprint ID:
        <input
          type="text"
          name="sprintId"
          value={sprintId}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Sprint Description: 
        <input
          type="text"
          name="sprintDescription"
          value={sprintDescription}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Start Date:
        <input
          type="date"
          name="startDate"
          value={startDate}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Sprint Length:
        <input
          type="text"
          name="sprintLength"
          value={sprintLength}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Points Planned: 
        <input
          type="text"
          name="pointsPlanned"
          value={pointsPlanned}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Points Completed:
        <input
          type="text"
          name="pointsCompleted"
          value={pointsCompleted}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Team ID:
        <input
          type="text"
          name="teamId"
          value={teamId}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        <input type="submit" value="Add Sprint" />
      </form>
    );
  }
}

export default AddSprintScreen;
