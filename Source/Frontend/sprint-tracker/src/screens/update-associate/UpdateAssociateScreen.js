import React from "react";
import ReactDOM from "react-dom";
import DataTable from "react-data-table-component";
import TableFilter from "react-table-filter";
import {} from "./UpdateAssociateScreen.scss";
import {} from "./UpdateAssociateScreen.css";

class UpdateAssociateScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      eid:  this.props.user.eid,
      firstName:  this.props.user.firstName,
      lastName: this.props.user.lastName,
      teamId: this.props.user.teamId,
      position: this.props.user.position,
      email: this.props.user.email
    };
    this._filterUpdated = this._filterUpdated.bind(this);

  }

  componentDidMount() {
    fetch("http://localhost:8080/employees").then(async (response) => {
      var data = await response.json();
      this.setState({ data });
      this.tableFilterNode.reset(this.state.data, true);
    });
  }

  _filterUpdated(newData, filtersObject) {
    this.setState({
      data: newData,
    });
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
   
    // put request method 
    let PUT_REQUEST_BODY = {
      method: "PUT",
      headers: {
      "Content-Type": "application/json",
      },
      body: JSON.stringify(this.state)
    };

    //fetch response from server, to check if the  associate has been added. 
    fetch(`${URL}/employees`, PUT_REQUEST_BODY).then(async response => {
      var resp = await response;
      alert (resp.status)
      if (resp.status === 200) { //potentially might have to be 201 rather than 200 
        alert("Associate Updated!");

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

  render() {
    
    const { eid, firstName, lastName, teamId, email, position } = this.state;
    let elementsHtml;
    if (this.state.data === undefined) {
      elementsHtml = <tr></tr>;
    } else {
      console.log(this.state);
      elementsHtml = this.state.data.map((item, index) => {
        return (
          <tr key={"row_" + index}>
            <td className="cell">{item.eid}</td>
            <td className="cell">{item.firstName}</td>
            <td className="cell">{item.lastName}</td>
            <td className="cell">{item.position}</td>
            <td className="cell">{item.teamId}</td>
            <td className="cell">{item.email}</td>
          </tr>
        );
      });
    }

    return (
      <div>
        <h1>Update Associates</h1>
        <h3>Current Associates</h3>
        <table className="basic-table">
          <thead>
            <TableFilter
              ref={(node) => {
                this.tableFilterNode = node;
              }}
              rows={this.state.data}
              onFilterUpdate={this._filterUpdated}
            >
              <th
                key="eid"
                filterkey="eid"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                EU ID
              </th>
              <th key="firstName" filterkey="firstName" className="cell">
                First Name
              </th>
              <th
                key="lastName"
                filterkey="lastName"
                className="cell"
                alignleft={"true"}
              >
                Last Name
              </th>
              <th
                key="position"
                filterkey="position"
                className="cell"
                casesensitive={"true"}
                showsearch={"true"}
              >
                Position
              </th>
              <th key="teamId" filterkey="teamId" className="cell">
                Team ID
              </th>
              <th
                key="email"
                filterkey="email"
                className="cell"
                alignleft={"true"}
              >
                Email
              </th>
            </TableFilter>
          </thead>
          <tbody>{elementsHtml}</tbody>
        </table>
        <br />
        <h3>Update Associate Form</h3>
        <h6>Fill in the details below and submit in order to update an associate.</h6>
        <form onSubmit={this.submitHandler.bind(this)}>
        EU ID: 
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
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
        <input type="submit" value="Update Associate" />
      </form>
      </div>
    );
  }
}

export default UpdateAssociateScreen;
