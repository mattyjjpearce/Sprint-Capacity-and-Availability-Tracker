import React from "react";
import ReactDOM from "react-dom";
import TableFilter from "react-table-filter";
import {} from "./DeleteTeamsScreen.scss";
import {} from "./DeleteTeamsScreen.css";

class DeleteTeamsScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    };

    this._filterUpdated = this._filterUpdated.bind(this);

    this.handleChange = this.changeHandler.bind(this);
    this.handleSubmit = this.submitHandler.bind(this);
  }

  changeHandler(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    this.setState({ [name]: value });
  }

  submitHandler(event) {
    const target = event.target;
    target.name = "id";
  }

  componentDidMount() {
    fetch("http://localhost:8080/teams").then(async (response) => {
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
    let elementsHtml;
    if (this.state.data === undefined) {
      elementsHtml = <tr></tr>;
    } else {
      console.log(this.state);
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
    }
      const{teamId} = this.state;
    return (
      <div>
        <h1>Delete Teams</h1>
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
        <br />
        <h3>Delete Tea, Form</h3>
        <h6>Fill in the ID and press delete to permanently remove a sprint.</h6>
        <form onSubmit={this.submitHandler.bind(this)}>
          Team ID:
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input
            type="text"
            value={this.state.input}
            onChange={this.handleInputChange.bind(this)}
          />
          <br />
          <input type="submit" value="Delete Team" />
        </form>
      </div>
    );
  }
}

export default DeleteTeamsScreen;
