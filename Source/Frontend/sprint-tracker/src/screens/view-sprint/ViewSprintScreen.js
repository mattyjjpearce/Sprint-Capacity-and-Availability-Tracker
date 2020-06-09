import React from "react";
import ReactDOM from "react-dom";
import TableFilter from "react-table-filter";
import {} from "./ViewSprintScreen.scss";
import {} from "./ViewSprintScreen.css";

class ViewSprintScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hid: "Holiday ID",
      eid: "EU ID",
      date: "Start Date",
      length: "Length",
    };

    this._filterUpdated = this._filterUpdated.bind(this);

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    this.setState({ [name]: value });
  }

  handleSubmit(event) {
    const target = event.target;
    target.name = "id";
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

  render() {
    let elementsHtml;
    if (this.state.data === undefined) {
      elementsHtml = <tr></tr>;
    } else {
      console.log(this.state);
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
    }

    return (
      <div>
        <h1>View Sprints</h1>
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
              </th><th
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
      </div>
    );
  }
}

export default ViewSprintScreen;
