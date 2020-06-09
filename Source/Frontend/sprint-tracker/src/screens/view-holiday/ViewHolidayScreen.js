import React from "react";
import ReactDOM from "react-dom";
import TableFilter from "react-table-filter";
import {} from "./ViewHolidayScreen.scss";
import {} from "./ViewHolidayScreen.css";

class ViewHolidayScreen extends React.Component {
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
    fetch("http://localhost:8080/holidays").then(async (response) => {
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
      elementsHtml = this.state.data.map((item, index) => {
        return (
          <tr key={"row_" + index}>
            <td className="cell">{item.holidayId}</td>
            <td className="cell">{item.employee.eid}</td>
            <td className="cell">{item.employee.firstName}</td>
            <td className="cell">{item.employee.lastName}</td>
            <td className="cell">{item.employee.teamId}</td>
            <td className="cell">{item.startDate}</td>
            <td className="cell">{item.length}</td>
          </tr>
        );
      });
    }

    return (
      <div>
        <h1>View Holidays</h1>
        <h3>Current Holidays</h3>
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
                key="holidayId"
                filterkey="holidayId"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                Holiday ID
              </th>
              <th
                key="employee.eid"
                filterkey="employee.eid"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                EU ID
              </th>
              <th
                key="employee.firstName"
                filterkey="employee.firstName"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                First Name
              </th>
              <th
                key="employee.lastName"
                filterkey="employee.lastName"
                className="cell"
                alignleft={"true"}
                casesensitive={"false"}
                showsearch={"true"}
              >
                Last Name
              </th>
              <th
                key="employee.teamId"
                filterkey="employee.teamId"
                className="cell"
                alignleft={"true"}
                casesensitive={"false"}
                showsearch={"true"}
              >
                Team ID
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
                key="length"
                filterkey="length"
                className="cell"
                alignleft={"true"}
              >
                Length
              </th>
            </TableFilter>
          </thead>
          <tbody>{elementsHtml}</tbody>
        </table>
      </div>
    );
  }
}

export default ViewHolidayScreen;
