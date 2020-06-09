import React from "react";
import ReactDOM from "react-dom";
import TableFilter from "react-table-filter";
import {} from "./ViewTeamsScreen.scss";
import {} from "./ViewTeamsScreen.css";

class ViewTeamsScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
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
    fetch("http://localhost:8080/teams").then(async (response) => {
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
            <td className="cell">{item.teamId}</td>
            <td className="cell">{item.teamName}</td>
            <td className="cell">{item.teamManager.firstName}</td>
            <td className="cell">{item.teamManager.lastName}</td>
            <td className="cell">{item.teamManager.email}</td>
          </tr>
        );
      });
    }

    return (
      <div>
        <h1>View Teams</h1>
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
      </div>
    );
  }
}

export default ViewTeamsScreen;
