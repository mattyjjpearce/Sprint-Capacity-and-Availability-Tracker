import React from "react";
import ReactDOM from "react-dom";
import TableFilter from "react-table-filter";
import {} from "./UpdateSprintScreen.scss";
import {} from "./UpdateSprintScreen.css";

class UpdateSprintScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      date: "Enter Start Date of Sprint",
      length: "Enter Length of Sprint",
      sprintId: "Sprint Id",
      sprintDescription: "Sprint Description",
      startDate: "Start Date",
      sprintLength: "Sprint Length",
      pointsPlanned: "Points Planned",
      pointsCompleted: "Points Completed",
      teamId: "Team Id",
    };
    this._filterUpdated = this._filterUpdated.bind(this);
  }

  t_filterUpdated(newData, filtersObject) {
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
      "A new Sprint was updated with the following details:\nSprint ID: " +
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

    // Quick Fix - allows us to parse the startDate with the correct formatting
    let parseDate = (drepr) => drepr.replace("-", "/").replace("-", "/");

    const {
      sprintId,
      startDate,
      sprintLength,
      pointsPlanned,
      pointsCompleted,
      teamId,
    } = this.state;
    const jsonParam = {
      sprintId,
      startDate: parseDate(startDate),
      sprintLength,
      pointsPlanned,
      pointsCompleted,
      teamId,
    };

    /*
    //Parse integers such as eid and teamId into accepted types
    this.state.sprintLength = parseInt(this.state.sprintLength)
    this.state.sprintId = parseInt(this.state.sprintId)
    this.state.startDate = parseInt(this.state.startDate)
    this.state.pointsPlanned = parseInt(this.state.pointsPlanned)
    this.state.pointsCompleted = parseInt(this.state.pointsCompleted)
    this.state.teamId = parseInt(this.state.teamId)
*/


console.log(jsonParam); 

    // post request method
    let PUT_REQUEST_BODY = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(jsonParam),
    };
      console.log(jsonParam); 


    //fetch response from server, to check if the holiday has been added.
    fetch(`${URL}/sprints/${sprintId}`, PUT_REQUEST_BODY).then(async (response) => {
      var resp = await response;
      alert(resp.status);
      if (resp.status === 200) {
        alert("New Sprint Added!");

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

  componentDidMount() {
    fetch("http://localhost:8080/sprints").then(async (response) => {
      var data = await response.json();
      this.setState({ data });
      this.tableFilterNode.reset(this.state.data, true);
    });
    fetch("http://localhost:8080/teams").then(async (response) => {
      var data1 = await response.json();
      this.setState({ data1 });
      this.tableFilterNode.reset(this.state.data1, true);
    });
  }

  _filterUpdated(newData, filtersObject) {
    this.setState({
      data: newData,
    });
  }

  render() {
    let elementsHtml, elementsHtml1;
    if (this.state.data === undefined) {
      elementsHtml = <tr></tr>;
      elementsHtml1 = <tr></tr>;
    } else {
      elementsHtml = this.state.data.map((item, index) => {
        return (
          <tr key={"row_" + index}>
            <td className="cell">{item.sprintId}</td>
            <td className="cell">{item.sprintDescription}</td>
            <td className="cell">{item.startDate}</td>
            <td className="cell">{item.sprintLength}</td>
            <td className="cell">{item.pointsPlanned}</td>
            <td className="cell">{item.pointsCompleted}</td>
            <td className="cell">{item.team.teamName}</td>
            <td className="cell">{item.team.teamManager.firstName}</td>
            <td className="cell">{item.team.teamManager.lastName}</td>
          </tr>
        );
      });
      elementsHtml1 = this.state.data1.map((item1, index1) => {
        return (
          <tr key={"row_" + index1}>
            <td className="cell">{item1.teamId}</td>
            <td className="cell">{item1.teamName}</td>
            <td className="cell">{item1.teamManager.firstName}</td>
            <td className="cell">{item1.teamManager.lastName}</td>
          </tr>
        );
      });
    }

    const {
      sprintId,
      sprintDescription,
      startDate,
      sprintLength,
      pointsPlanned,
      pointsCompleted,
      teamId,
    } = this.state;
    return (
      <div>
        <h1>Update Sprint</h1>
        <h3>Current Sprints</h3>
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
                key="sprintId"
                filterkey="sprintId"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                Sprint ID
              </th>
              <th
                key="sprintDescription"
                filterkey="sprintDescription"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                Sprint Description
              </th>
              <th
                key="startDate"
                filterkey="startDate"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                Start Date
              </th>
              <th
                key="sprintLength"
                filterkey="sprintLength"
                className="cell"
                alignleft={"true"}
                casesensitive={"false"}
                showsearch={"true"}
              >
                Sprint Length
              </th>
              <th
                key="pointsPlanned"
                filterkey="pointsPlanned"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                Points Planned
              </th>
              <th
                key="pointsCompleted"
                filterkey="pointsCompleted"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                Points Completed
              </th>

              <th
                key="team.teamName"
                filterkey="team.teamName"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                Team Name
              </th>
              <th
                key="team.teamManager.firstName"
                filterkey="team.teamManager.firstName"
                className="cell"
                alignleft={"true"}
                casesensitive={"false"}
                showsearch={"true"}
              >
                Manager's First Name
              </th>
              <th
                key="team.teamManager.lastName"
                filterkey="team.teamManager.lastName"
                className="cell"
                alignleft={"true"}
                casesensitive={"false"}
                showsearch={"true"}
              >
                Manager's Last Name
              </th>
            </TableFilter>
          </thead>
          <tbody>{elementsHtml}</tbody>
        </table>
        <h3>Current Teams</h3>
        <table className="custom-table">
          <thead>
            <TableFilter
              ref={(node) => {
                this.tableFilterNode = node;
              }}
              rows={this.state.data1}
              onFilterUpdate={this._filterUpdated}
            >
              <th key="teamId">Team ID</th>
              <th key="teamName">Team Name</th>
              <th key="teamManager.firstName">Manager's First Name</th>
              <th key="teamManager.lastName">Manager's Last Name</th>
            </TableFilter>
          </thead>
          <tbody>{elementsHtml1}</tbody>
        </table>
        <br />
        <h3>Update Sprint Form</h3>
        <h6>Fill in all the details below to update a sprint.</h6>
        <form onSubmit={this.submitHandler.bind(this)}>
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
          <input type="submit" value="Update Sprint" />
        </form>
      </div>
    );
  }
}

export default UpdateSprintScreen;
