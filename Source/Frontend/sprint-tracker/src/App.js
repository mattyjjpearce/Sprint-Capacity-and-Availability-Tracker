import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import MyNavBar from './components/MyNavBar';
//import all the screens for the system
import Dashboard from './screens/dashboard/Dashboard';

import AddAssociateScreen from './screens/add-associate-form/AddAssociateScreen';
import AddHolidayScreen from './screens/add-holiday/AddHolidayScreen';
import DeleteAssociateScreen from './screens/delete-associate/DeleteAssociateScreen';
import UpdateAssociateScreen from './screens/update-associate/UpdateAssociateScreen';

import DeleteHolidayScreen from './screens/delete-holiday/DeleteHolidayScreen';
import ViewAssociateScreen from './screens/view-associate-form/ViewAssociateScreen';
import ViewHolidayScreen from './screens/view-holiday/ViewHolidayScreen';
import UpdateHolidayScreen from './screens/update-holiday/UpdateHolidayScreen';

import AddSprintScreen from './screens/add-sprint/AddSprintScreen';
import DeleteSprintScreen from './screens/delete-sprint/DeleteSprintScreen';
import ViewSprintScreen from './screens/view-sprint/ViewSprintScreen';
import UpdateSprintScreen from './screens/update-sprint/UpdateSprintScreen';

import AddTeamScreen from './screens/add-teams/AddTeamsScreen';
import DeleteTeamScreen from './screens/delete-teams/DeleteTeamsScreen';
import ViewTeamScreen from './screens/view-teams/ViewTeamsScreen';
import UpdateTeamsScreen from './screens/update-team/UpdateTeamsScreen';

import AddWorkPatternScreen from './screens/add-work-pattern/AddWorkPatternScreen';
import DeleteWorkPatternScreen from './screens/delete-work-pattern/DeleteWorkPatternScreen';
import ViewWorkPatternScreen from './screens/view-work-pattern/ViewWorkPatternScreen';
import UpdateWorkPatternScreen from './screens/update-work-pattern/UpdateWorkPatternScreen';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      user: undefined
    };
  }
//set the state for the change of user
  switchUser(obj) {
    this.setState({ user: obj });
  }

  render() {
    return (
      <div className="App">
        <MyNavBar 
          user={this.state.user} 
          //when the user changes
          onChangeUser={this.switchUser.bind(this)} 
        />
        {this.state.user === undefined
          ? <><h1>Login</h1> Password: <input type="password" name="password"></input></>
          //set up the router for the system
          : <Router>
              <Switch>
                <Route exact path="/">
                  <Dashboard user={this.state.user} />
                </Route>
                <Route exact path="/add-associate">
                  <AddAssociateScreen user={this.state.user} />
                </Route>
                <Route exact path="/add-holiday">
                  <AddHolidayScreen user={this.state.user} />
                </Route>
                <Route exact path="/add-sprint">
                  <AddSprintScreen user={this.state.user} />
                </Route>
                <Route exact path="/add-teams">
                  <AddTeamScreen user={this.state.user} />
                </Route>
                <Route exact path="/add-work-pattern">
                  <AddWorkPatternScreen user={this.state.user} />
                </Route>
                <Route exact path="/delete-holiday">
                  <DeleteHolidayScreen user={this.state.user} />
                </Route>
                <Route exact path="/delete-sprint">
                  <DeleteSprintScreen user={this.state.user} />
                </Route>
                <Route exact path="/delete-teams">
                  <DeleteTeamScreen user={this.state.user} />
                </Route>
                <Route exact path="/delete-work-pattern">
                  <DeleteWorkPatternScreen user={this.state.user} />
                </Route>
                <Route exact path="/view-associate">
                  <ViewAssociateScreen user={this.state.user} />
                </Route>
                <Route exact path="/view-holiday">
                  <ViewHolidayScreen user={this.state.user} />
                </Route>
                <Route exact path="/view-sprint">
                  <ViewSprintScreen user={this.state.user} />
                </Route>
                <Route exact path="/view-teams">
                  <ViewTeamScreen user={this.state.user} />
                </Route>
                <Route exact path="/view-work-pattern">
                  <ViewWorkPatternScreen user={this.state.user} />
                </Route>
                <Route exact path="/delete-associate">
                  <DeleteAssociateScreen user={this.state.user} />
                </Route>
                <Route exact path="/update-holiday">
                  <UpdateHolidayScreen user={this.state.user} />
                </Route>
                <Route exact path="/update-associate">
                  <UpdateAssociateScreen user={this.state.user} />
                </Route>
                <Route exact path="/update-sprint">
                  <UpdateSprintScreen user={this.state.user} />
                </Route>
                <Route exact path="/update-team">
                  <UpdateTeamsScreen user={this.state.user} />
                </Route>
                <Route exact path="/update-work-pattern">
                  <UpdateWorkPatternScreen user={this.state.user} />
                </Route>
              </Switch>
            </Router>}
      </div>
    );
  }
}

export default App;
