import React from "react";
import ReactDOM from "react-dom";
import "./AddWorkPatternScreen.css";

class AddWorkPatternScreen extends React.Component {
  constructor(props) {
    super(props);
    //state is the object where the data will be stored
    this.state = {
      workPatternId: "Work Pattern ID",
      eid:  this.props.user.eid,
      mondayHours: "Input hours",
      tuesdayHours: "Input hours",
      wednesdayHours: "Input hours",
      thursdayHours: "Input hours",
      fridayHours: "Input hours",
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
      "A new team was added with the following details:\nWorking Pattern ID: " +
        this.state.workPatternId +
        "\nEU ID: " +
        this.props.user.eid +
        "\nMonday's Hours: " +
        this.state.mondayHours +
        "\nTuesday's Hours: " +
        this.state.tuesdayHours +
        "\nWednesday's Hours: " +
        this.state.wednesdayHours +
        "\nThursday's Hours: " +
        this.state.thursdayHours +
        "\nFriday's Hours: " +
        this.state.fridayHours
    );

    let URL = "http://localhost:8080";

    //Parse integers such as eid and teamId into accepted types
    this.state.workPatternId= parseInt(this.state.workPatternId)
    this.state.mondayHours = parseInt(this.state.mondayHours)
    this.state.tuesdayHours = parseInt(this.state.tuesdayHours)
    this.state.wednesdayHours = parseInt(this.state.wednesdayHours)
    this.state.thursdayHours = parseInt(this.state.thursdayHours)
    this.state.fridayHours = parseInt(this.state.fridayHours)
    

    // post request method 
    let POST_REQUEST_BODY = {
      method: "POST",
      headers: {
      "Content-Type": "application/json",
      },
      body: JSON.stringify(this.state)
    };

    //fetch response from server, to check if the holiday has been added. 
    fetch(`${URL}/workPattern`, POST_REQUEST_BODY).then(async response => {
      var resp = await response;
      alert (resp.status)
      if (resp.status === 201) {
        alert("New Working Pattern Added!");

       // var data = resp.json();
      } else if (resp.status === 400) {
      } else {
       
      }
    });
  }

  //Display 
  render() {
    const { workPatternId, eid, mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours} = this.state;
    return (
      <form onSubmit={this.submitHandler.bind(this)}>
        <h1>Add Work Pattern</h1>
        <h6>Fill in all the details below to add a new work pattern.</h6>
        Work Pattern ID:
        <input
          type="text"
          name="workPatternId"
          value={workPatternId}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Monday's Hours: 
        <input
          type="text"
          name="mondayHours"
          value={mondayHours}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Tuesday's Hours:
        <input
          type="text"
          name="tuesdayHours"
          value={tuesdayHours}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Wednesday's Hours:
        <input
          type="text"
          name="wednesdayHours"
          value={wednesdayHours}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Thursday's Hours:
        <input
          type="text"
          name="thursdayHours"
          value={thursdayHours}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Friday's Hours:
        <input
          type="text"
          name="fridayHours"
          value={fridayHours}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        <input type="submit" value="Add Work Pattern" />
      </form>
    );
  }
}

export default AddWorkPatternScreen;
