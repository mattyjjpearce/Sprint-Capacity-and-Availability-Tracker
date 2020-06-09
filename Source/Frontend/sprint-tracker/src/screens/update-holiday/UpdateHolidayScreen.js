import React from "react";
import ReactDOM from "react-dom";
import TableFilter from "react-table-filter";
import {} from "./UpdateHolidayScreen.scss";
import {} from "./UpdateHolidayScreen.css";

class UpdateHolidayScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      user: undefined,
      hid: "Holiday ID",
      eid: this.props.user.eid,
      date: "Start Date",
      length: "Length",
      holidayId: "Holiday IDs",
      startDate: "Start Date",
      length: "Length",
    };
    this._filterUpdated = this._filterUpdated.bind(this);
  }

  componentDidMount() {
    let eid = this.props.user.eid;
    fetch(`http://localhost:8080/holidays/${eid}`).then(async (response) => {
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
    //Alerts the user with the informaiton they have inputted
    alert(
      "A new Holiday was updated with the following details:\nHoliday ID: " +
        this.state.holidayId +
        "\nStart Date: " +
        this.state.startDate +
        "\nLength: " +
        this.state.length
    );

    let URL = "http://localhost:8080";

    // Quick Fix - allows us to parse the startDate with the correct formatting
    let parseDate = (drepr) => drepr.replace("-", "/").replace("-", "/");

    const { holidayId, startDate, length } = this.state;
    const jsonParam = {
      employeeID: this.props.user.eid,
      holidayId,
      startDate: parseDate(startDate),
      length,
    };

    // put request method
    let PUT_REQUEST_BODY = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(jsonParam),
    };
    console.log(PUT_REQUEST_BODY);

    //fetch response from server, to check if the holiday has been added.
    fetch(`${URL}/holidays`, PUT_REQUEST_BODY).then(async (response) => {
      var resp = await response;
      alert(resp.status);
      if (resp.status === 200) {
        alert("Holiday Updated!");
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
    const { startDate, length, holidayId } = this.state;

    return (
      <div>
        <h1>Update Holiday Requests</h1>
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
        <form onSubmit={this.submitHandler.bind(this)}>
          <br />
          <br />
          HolidayID: &nbsp;&nbsp;&nbsp;&nbsp;
          <input
            type="text"
            name={"holidayId"}
            value={holidayId}
            onChange={this.changeHandler.bind(this)}
          />
          <br />
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
          <input type="submit" value="Update Holiday" />
        </form>
      </div>
    );
  }
}

export default UpdateHolidayScreen;
