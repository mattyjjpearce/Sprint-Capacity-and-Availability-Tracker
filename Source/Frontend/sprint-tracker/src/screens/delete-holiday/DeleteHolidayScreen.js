import React from "react";
import TableFilter from "react-table-filter";
import {} from "./DeleteHolidayScreen.scss";
import "./DeleteHolidayScreen.css";

class DeleteHolidayScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      input: "",
      holidayId: "holiday Id",
      eid: "EU ID",
      date: "Start Date",
      length: "Length",
      
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
    fetch("http://localhost:8080/holidays").then(async (response) => {
      var data = await response.json();
      this.setState({ data });
      this.tableFilterNode.reset(this.state.data, true);
    });
  }

  submitHandler(e) {
    e.preventDefault();
    console.log(this.state);
    //Alerts the user with the informaiton they have inputted
    alert("The Holiday with ID  " + this.state.input + " will be deleted.");

    let URL = "http://localhost:8080";
    //Parse integers such as eid and teamId into accepted types
    this.state.input = parseInt(this.state.input);

    //fetch response from server, to check if the holiday has been deleted.
    fetch(`${URL}/holidays/${this.state.input}`, { method: "delete" }).then(
      async (response) => {
        var resp = await response;
        alert(resp.status);
        if (resp.status === 200) {
          // if the status is "OK" (deleted)
          alert("Holiday deleted!");

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

    const { holidayId } = this.state;
    return (
      <div>
        <h1>Delete Holiday Requests</h1>
        <h3>Current Holiday Requests</h3>
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
        <br />
        <h3>Delete Holiday Form</h3>
        <h6>Fill in the ID and press delete to permanently remove a holiday.</h6>
        <form onSubmit={this.submitHandler.bind(this)}>
          Holiday ID:
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input
            type="text"
            value={this.state.input}
            onChange={this.handleInputChange.bind(this)}
          />
          <br />
          <input type="submit" value="Delete Holiday" />
        </form>
      </div>
    );
  }
}

export default DeleteHolidayScreen;
