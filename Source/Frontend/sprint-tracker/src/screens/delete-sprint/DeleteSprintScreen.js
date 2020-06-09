import React from "react";
import ReactDOM from "react-dom";
import TableFilter from "react-table-filter";
import {} from "./DeleteSprintScreen.scss";
import {} from "./DeleteSprintScreen.css";

class DeleteSprintScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      input: "",
      sprintId: "Sprint ID",
      sprintDescription: "Sprint Description",
      startDate: "Start Date",
      sprintLength: "Length",
      pointsPlanned: "Points Planned",
      pointsCompleted: "Points Completed",
      teamName: "Team Name",
      firstName: "First Name",
      lastName: "Last Name",
    };
    this._filterUpdated = this._filterUpdated.bind(this);
    this.changeHandler = this.changeHandler.bind(this);
  }

  _filterUpdated(newData, filtersObject) {
    this.setState({
      data: newData,
    });
  }

  changeHandler(e) {
    console.log(e);
    this.setState({ [e.target.name]: e.target.value });
  }

  componentDidMount() {
    fetch("http://localhost:8080/sprints").then(async (response) => {
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

  submitHandler(e) {
    e.preventDefault();
    console.log(this.state);
    //Alerts the user with the informaiton they have inputted
    alert("The Sprint with ID  " + this.state.input + " will be deleted.");

    let URL = "http://localhost:8080";
    //Parse integers such as eid and teamId into accepted types
    this.state.input = parseInt(this.state.input);

    //fetch response from server, to check if the holiday has been deleted.
    fetch(`${URL}/sprints/${this.state.input}`, { method: "delete" }).then(
      async (response) => {
        var resp = await response;
        alert(resp.status);
        if (resp.status === 200) {
          // if the status is "OK" (deleted)
          alert("Sprint deleted!");

          var data = resp.json();
        } else if (resp.status === 400 || resp.status === 404) {
        } else {
        }
      }
    );
  }

  //print event and set state of input to value inputted
  handleInputChange(event) {
    console.log(event);
    this.setState({ input: event.target.value });
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    this.setState({ [name]: value });
  }

  render() {
    let elementsHtml;
    if (this.state.data === undefined) {
      elementsHtml = <tr></tr>;
    } else {
      console.log(this.state);
      elementsHtml = this.state.data.map((item, index) => {
        //   const {holidayId} = this.state;
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
    }
    const { sprintId } = this.state;
    return (
      <div>
        <h1>Delete Sprints</h1>
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
        <br />
        <h3>Delete Sprint Form</h3>
        <h6>Fill in the ID and press delete to permanently remove a sprint.</h6>
        <form onSubmit={this.submitHandler.bind(this)}>
          Sprint ID:
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input
            type="text"
            value={this.state.input}
            onChange={this.handleInputChange.bind(this)}
          />
          <br />
          <input type="submit" value="Delete Sprint" />
        </form>
      </div>
    );
  }
}

export default DeleteSprintScreen;
