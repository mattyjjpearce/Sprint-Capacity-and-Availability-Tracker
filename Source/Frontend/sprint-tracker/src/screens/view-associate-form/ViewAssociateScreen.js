import React from "react";
import ReactDOM from "react-dom";
import DataTable from "react-data-table-component";
import TableFilter from "react-table-filter";
import {} from "./ViewAssociateScreen.scss";
import {} from "./ViewAssociateScreen.css";

class ViewAssociateScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      Fname: "",
      Lname: "",
      Team: "",
    };
    this._filterUpdated = this._filterUpdated.bind(this);

  }

  componentDidMount() {
    fetch("http://localhost:8080/employees").then(async (response) => {
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

    return (
      <div>
        <h1>View Associates</h1>
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
      </div>
    );
  }
}

export default ViewAssociateScreen;
