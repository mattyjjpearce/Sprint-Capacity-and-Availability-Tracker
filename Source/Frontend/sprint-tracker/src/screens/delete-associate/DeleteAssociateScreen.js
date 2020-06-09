import React from "react";
import TableFilter from "react-table-filter";
import {} from "./DeleteAssociateScreen.scss";
import "./DeleteAssociateScreen.css";

class DeleteAssociateScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        input: 0,
        id: "",
        Fname: "",
        Lname: "",
        Team: "",
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
    fetch("http://localhost:8080/employees").then(async (response) => {
      var data = await response.json();
      this.setState({ data });
      this.tableFilterNode.reset(this.state.data, true);
    });
  }

  submitHandler(e) {
    e.preventDefault();
    console.log(this.state);
    //Alerts the user with the informaiton they have inputted
    alert("The associate with ID  " + this.state.input + " will be deleted.");

    let URL = "http://localhost:8080";
    //Parse integers such as eid and teamId into accepted types
    var num = parseInt(this.state.input);

    //fetch response from server, to check if the associate has been deleted.
    fetch(`${URL}/employees/${num}`, { method: "delete" }).then(
      async (response) => {
        var resp = await response;
        alert(resp.status);
        if (resp.status === 200) {
          // if the status is "OK" (deleted)
          alert("Associate deleted!");

          //var data = resp.json();
        } else if (resp.status === 400 || resp.status === 404) {
        } else {
        }
      }
    );
  }

  //print event occured and change the state of input to value
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

    const { associateId } = this.state;
    return (
      <div>
        <h1>Delete Associates</h1>
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
        <h3>Delete Associate Form</h3>
        <h6>Fill in the ID and press delete to permanently remove an associate.</h6>
        <form onSubmit={this.submitHandler.bind(this)}>
          Employee ID:
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input
            type="text"
            value={this.state.input}
            onChange={this.handleInputChange.bind(this)}
          />
          <br />
          <input type="submit" value="Delete Associate" />
        </form>
      </div>
    );
  }
}

export default DeleteAssociateScreen;

