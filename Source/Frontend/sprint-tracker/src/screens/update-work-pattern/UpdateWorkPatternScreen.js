import React from "react";
import ReactDOM from "react-dom";
import TableFilter from "react-table-filter";
import {} from "./UpdateWorkPatternScreen.scss";
import {} from "./UpdateWorkPatternScreen.css";

class UpdateWorkPatternScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [{"workPatternId":3,"employee":{"eid":10,"firstName":"Irv","lastName":"Brazur","position":"Team Manager","email":"ibrazur9@columbia.edu","teamId":7},"eid":10,"thursdayHours":8,"fridayHours":8,"mondayHours":8,"wednesdayHours":8,"tuesdayHours":8},{"workPatternId":5,"employee":{"eid":15,"firstName":"Yulma","lastName":"Scorthorne","position":"Admin","email":"yscorthornee@mashable.com","teamId":6},"eid":15,"thursdayHours":8,"fridayHours":8,"mondayHours":6,"wednesdayHours":8,"tuesdayHours":8},],
      workPatternId: "Work Pattern ID",
      eid:  this.props.user.eid,
      mondayHours: "Input hours",
      tuesdayHours: "Input hours",
      wednesdayHours: "Input hours",
      thursdayHours: "Input hours",
      fridayHours: "Input hours",
    };

    this._filterUpdated = this._filterUpdated.bind(this);

    this.handleChange = this.changeHandler.bind(this);
    this.handleSubmit = this.submitHandler.bind(this);
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

  componentDidMount() {
    fetch("http://localhost:8080/workPattern").then(async (response) => {
      var data = await response.json();
      this.setState({ data });
      this.tableFilterNode.reset(this.state.data, true);
    });
  }

  submitHandler(e) {
    e.preventDefault();
    console.log(this.state);
    //Alerts the user with the informaiton they have inputted
    alert("The Team with ID  " + this.state.input + " will be deleted.");

    let URL = "http://localhost:8080";
    //Parse integers such as eid and teamId into accepted types
    this.state.input = parseInt(this.state.input);

    //fetch response from server, to check if the holiday has been deleted.
    fetch(`${URL}/teams/${this.state.input}`, { method: "delete" }).then(
      async (response) => {
        var resp = await response;
        alert(resp.status);
        if (resp.status === 200) {
          // if the status is "OK" (deleted)
          alert("Team deleted!");

          var data = resp.json();
        } else if (resp.status === 400 || resp.status === 404) {
        } else {
        }
      }
    );
  }
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

  _filterUpdated(newData, filtersObject) {
    this.setState({
      data: newData,
    });
  }

  render() {
    const { workPatternId, eid, mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours} = this.state;
    let elementsHtml;
    if (this.state.data === undefined) {
      elementsHtml = <tr></tr>;
    } else {
      console.log(this.state);
      elementsHtml = this.state.data.map((item, index) => {
        return (
          <tr key={"row_" + index}>
            <td className="cell">{item.workPatternId}</td>
            <td className="cell">{item.employee.eid}</td>
            <td className="cell">{item.employee.firstName}</td>
            <td className="cell">{item.employee.lastName}</td>
            <td className="cell">{item.mondayHours}</td>
            <td className="cell">{item.tuesdayHours}</td>
            <td className="cell">{item.wednesdayHours}</td>
            <td className="cell">{item.thursdayHours}</td>
            <td className="cell">{item.fridayHours}</td>
          </tr>
        );
      });
    }
      const{teamId} = this.state;
    return (
      <div>
        <h1>Update Working Patterns</h1>
        <h3>Current Working Patterns</h3>
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
                key="workPatternId"
                filterkey="workPatternId"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                Working Pattern ID
              </th>
              <th
                key="employee.eid"
                filterkey="employee.eid"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                Employee ID
              </th>
              <th
                key="employee.firstName"
                filterkey="employee.firstName"
                className="cell"
                alignleft={"true"}
                casesensitive={"false"}
                showsearch={"true"}
              >
                Employee's First Name
              </th>
              <th
                key="employee.lastName"
                filterkey="employee.lastName"
                className="cell"
                alignleft={"true"}
                casesensitive={"false"}
                showsearch={"true"}
              >
                Employee's Last Name
              </th>
              <th
                className="cell"
              >
                Monday's Hours
              </th>
              <th
                className="cell"
              >
                Tuesday's Hours
              </th>
              <th
                className="cell"
              >
                Wednesday's Hours
              </th>
              <th
                className="cell"
              >
                Thursday's Hours
              </th>
              <th
                className="cell"
              >
                Friday's Hours
              </th>
            </TableFilter>
          </thead>
          <tbody>{elementsHtml}</tbody>
        </table>
        <br />
        <h3>Update Work Pattern Form</h3>
        <h6>Fill in the details below and submit in order to update a work pattern.</h6>
        <form onSubmit={this.submitHandler.bind(this)}>
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
        <input type="submit" value="Update Work Pattern" />
      </form>
      </div>
    );
  }
}

export default UpdateWorkPatternScreen;
