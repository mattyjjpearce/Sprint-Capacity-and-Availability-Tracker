import React from "react";
import ReactDOM from "react-dom";
import TableFilter from "react-table-filter";
import {} from "./UpdateTeamsScreen.scss";
import {} from "./UpdateTeamsScreen.css";

class UpdateTeamsScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      teamId: "Team ID",
      teamName: "Team Name",
      teamManagerId: "Team Manager ID",
    };

    this._filterUpdated = this._filterUpdated.bind(this);
  };

  changeHandler(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  //Once the Add New Associate button is pressed: 
  submitHandler(e) {
    e.preventDefault();
    console.log(this.state);
    //Alerts the user with the informaiton they have inputted
    alert(
      "A new team was updated with the following details:\nTeam ID: " +
        this.state.teamId +
        "\nTeam Name: " +
        this.state.teamName +
        "\nTeam Manager ID: " +
        this.state.teamManagerId
    );

    let URL = "http://localhost:8080";
    //Parse integers such as eid and teamId into accepted types
    this.state.teamId = parseInt(this.state.teamId)
    this.state.teamManagerId = parseInt(this.state.teamManagerId)



   
    // post request method 
    let PUT_REQUEST_BODY = {
      method: "PUT",
      headers: {
      "Content-Type": "application/json",
      },
      body: JSON.stringify(this.state)
    };

    //fetch response from server, to check if the holiday has been added. 
    fetch(`${URL}/teams/${this.state.teamId}`, PUT_REQUEST_BODY).then(async response => {
      var resp = await response;
      alert (resp.status)
      if (resp.status === 201) {
        alert("New team Updated!");

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
    fetch("http://localhost:8080/teams").then(async (response) => {
      var data = await response.json();
      this.setState({ data });
      this.tableFilterNode.reset(this.state.data, true);
    });
    fetch("http://localhost:8080/employees?position=Team Manager").then(async (response) => {
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
    const columns = [
      {
        name: "Team ID",
        selector: "teamId",
        sortable: true,
      },
      {
        name: "Team Name",
        selector: "teamName",
        sortable: true,
      },
      {
        name: "Team Manager First Name",
        selector: "firstName",
        sortable: true,
      },
      {
        name: "Team Manager Last Name",
        selector: "lastName",
        sortable: true,
        filterable: true,
      },
      {
        name: "Team Manager Email",
        selector: "email",
        sortable: true,
        filterable: true,
      },
    ];

    const columns1 = [
      {
        name: "Employee ID",
        selector: "eid",
        sortable: true,
      },
      {
        name: "First Name",
        selector: "firstName",
        sortable: true,
      },
      {
        name: "Last Name",
        selector: "lastName",
        sortable: true,
        filterable: true,
      },
      {
        name: "Email",
        selector: "email",
        sortable: true,
        filterable: true,
      },
    ];

    let elementsHtml, elementsHtml1;
    if (this.state.data === undefined) {
      elementsHtml = <tr></tr>;
      elementsHtml1 = <tr></tr>;
    } else {
      elementsHtml = this.state.data.map((item, index) => {
        return (
          <tr key={"row_" + index}>
            <td className="cell">{item.teamId}</td>
            <td className="cell">{item.teamName}</td>
            <td className="cell">{item.teamManager.firstName}</td>
            <td className="cell">{item.teamManager.lastName}</td>
            <td className="cell">{item.teamManager.email}</td>
          </tr>
        );
      });
      elementsHtml1 = this.state.data1.map((item1, index1) => {
        return (
          <tr key={"row_" + index1}>
            <td className="cell">{item1.eid}</td>
            <td className="cell">{item1.firstName}</td>
            <td className="cell">{item1.lastName}</td>
          </tr>
        );
      });
    }
    const { teamId, teamName, teamManagerId} = this.state;
    return (
      <div>
        <h1>Update Teams</h1>
        <h3>Current Teams</h3>
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
                key="teamId"
                filterkey="teamId"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                Team ID
              </th>
              <th
                key="teamName"
                filterkey="teamName"
                className="cell"
                casesensitive={"false"}
                showsearch={"true"}
              >
                Team Name
              </th>
              <th
                key="teamManager.firstName"
                filterkey="teamManager.firstName"
                className="cell"
                alignleft={"true"}
                casesensitive={"false"}
                showsearch={"true"}
              >
                Manager's First Name
              </th>
              <th
                key="teamManager.lastName"
                filterkey="teamManager.lastName"
                className="cell"
                alignleft={"true"}
                casesensitive={"false"}
                showsearch={"true"}
              >
                Manager's Last Name
              </th>
              <th
                key="teamManager.email"
                filterkey="teamManager.email"
                className="cell"
                alignleft={"true"}
                casesensitive={"false"}
                showsearch={"true"}
              >
                Manager's Email
              </th>
            </TableFilter>
          </thead>
          <tbody>{elementsHtml}</tbody>
        </table>
        <h3>Current Team Managers</h3>
        <table className="custom-table">
          <thead>
            <TableFilter
              ref={(node) => {
                this.tableFilterNode = node;
              }}
              rows={this.state.data1}
              onFilterUpdate={this._filterUpdated}
            >
              <th
                key="eid"
              >
                Employee ID
              </th>
              <th
                key="firstName"
              >
                First Name
              </th>
              <th
                key="lastName"
              >
                Last Name
              </th>
            </TableFilter>
          </thead>
          <tbody>{elementsHtml1}</tbody>
        </table><br />
        <h3>Update Form</h3>
        
        <h6>Fill in all the details below to update a team.</h6>
        <form onSubmit={this.submitHandler.bind(this)}>
        Team ID:
        <input
          type="text"
          name="teamId"
          value={teamId}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Team Name: 
        <input
          type="text"
          name="teamName"
          value={teamName}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        Team Manager ID:
        <input
          type="text"
          name="teamManagerId"
          value={teamManagerId}
          onChange={this.changeHandler.bind(this)}
        />
        <br />
        <input type="submit" value="Update Team" />
      </form>
      </div>
    );
  }
}

export default UpdateTeamsScreen;
