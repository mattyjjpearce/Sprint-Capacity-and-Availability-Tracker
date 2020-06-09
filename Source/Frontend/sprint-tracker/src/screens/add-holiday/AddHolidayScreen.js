import React from "react";
import ReactDOM from "react-dom";
import "./AddHolidayScreen.css";

class AddHolidayScreen extends React.Component {
  constructor(props) {
    super(props);
    //state is the object where the data will be stored
    this.state = {
      employeeID: this.props.user.eid,
      startDate: "Start Date",
      length: "Length",
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
      "A new Holiday was added with the following details:\nEU ID: " +
        this.props.user.eid +
        "\nStart Date: " +
        this.state.startDate +
        "\nLength: " +
        this.state.length
    );
    let URL = "http://localhost:8080";
    //Parse integers such as eid and teamId into accepted types

    let parseDate = drepr => drepr.replace('-', '/').replace('-', '/');

    let param = {
      employeeID: this.props.user.eid,
      startDate: parseDate(this.state.startDate),
      length: this.state.length
    };
   
    // post request method 
    let POST_REQUEST_BODY = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(param)
    };

    //fetch response from server, to check if the holiday has been added. 
    fetch(`${URL}/holidays`, POST_REQUEST_BODY).then(async response => {
      var resp = await response;
      alert (resp.status)
      if (resp.status === 201) {
        alert("New Holiday Added!");

       //  var data = resp.json();
      } else if (resp.status === 400) {
      } else {
       
      }
    });
  }

  
  //Display 
  render() {
    const {  startDate, length} = this.state;
    return (
      <form onSubmit={this.submitHandler.bind(this)}>
        <h1>Add Holiday</h1>
        <h6>Fill in all the details below to add a new holiday.</h6>
        Start Date: &nbsp;&nbsp;&nbsp;&nbsp;
        <input
          type="date"
          name="startDate"
          value={startDate}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Length (days): &nbsp;&nbsp;&nbsp;&nbsp;
        <input
          type="text"
          name="length"
          value={length}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        <input type="submit" value="Request Holiday" />
      </form>
    );
  }
}

export default AddHolidayScreen;
