import React from 'react';
import PageCard from '../../components/PageCard';
import { CardColumns } from 'react-bootstrap';
import "./Dashboard.css";

class Dashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      position: undefined
    };
  }
  
  showAssocCards() {
    //show the correct cards for if an associate is logged in 
   if (this.props.user.position == 'Associate') {
    return (
      <div> 
        <h1 style={{padding:20}}>Welcome! {`${this.props.user.firstName} ${this.props.user.lastName}: ${this.props.user.position}`}</h1>

      <CardColumns  >    
      
    <PageCard 
        name="Add Holiday" 
        description="Use this screen to add a new holiday to the system"
        link="/add-holiday"
      />
        
      <PageCard
        name="Update Holiday"
        description="Use this screen to update a holidays details"
        link="/update-holiday"
      />
      <PageCard
        name="Add Working Pattern"
        description="Use this screen to add a new working pattern to the system"
        link="/add-work-pattern"
      />   
      <PageCard
        name="Update Work Pattern"
        description="Use this screen to update a working pattern"
        link="/update-work-pattern"
      />
      <PageCard
        name="View Sprint"
        description="Use this screen to view all sprints saved in the system"
        link="/view-sprint"
      />
    </CardColumns>
      </div>
    );
   }
   //show the correct cards for if an admin is logged in 
   if (this.props.user.position == 'Admin') {
     return( 
      <div>
       <h1>Welcome! {`${this.props.user.firstName} ${this.props.user.lastName}: ${this.props.user.position}`}</h1>
       <CardColumns>
       <PageCard 
        name="Add Associate" 
        description="Use this screen to add a new associate to the system"
        link="/add-associate" 
      />
      <PageCard
        name="View Associate"
        description="Use this screen to view all associates saved in the system"
        link="/view-associate"
      />
      <PageCard
        name="Update Associate"
        description="Use this screen to update an associates details"
        link="/update-associate"
      />      
      <PageCard
        name="Delete Associate"
        description="Use this screen to remove selected associates saved in the system"
        link="/delete-associate"
      />  
      <PageCard 
        name="" 
        description=""
        link=""
        class="hidden"
      />
      <PageCard
        name="Add Team"
        description="Use this screen to add a new team to the system"
        link="/add-teams"
      />
      <PageCard
        name="View Team"
        description="Use this screen to view all teams saved in the system"
        link="/view-teams"
      />
      <PageCard
        name="Update Team"
        description="Use this screen to update a teams details"
        link="/update-team"
      />
      <PageCard
        name="Delete Team"
        description="Use this screen to remove selected teams saved in the system"
        link="/delete-teams"
      />

      <PageCard
        name="Add Holiday"
        description="Use this screen to add a new holiday to the system"
        link="/add-holiday"
      />      
      <PageCard
        name="View Holiday"
        description="Use this screen to view all holidays saved in the system"
        link="/view-holiday"
      />
      <PageCard
        name="Update Holiday"
        description="Use this screen to update a holidays details"
        link="/update-holiday"
      />
      <PageCard
        name="Delete Holiday"
        description="Use this screen to remove selected holidays saved in the system"
        link="/delete-holiday"
      />
      
      <PageCard 
        name="" 
        description=""
        link="" 
      />

      <PageCard
        name="Add Working Pattern"
        description="Use this screen to add a new working pattern to the system"
        link="/add-work-pattern"
      />
      <PageCard
        name="View Working Patterns"
        description="Use this screen to view all working patterns saved in the system"
        link="/view-work-pattern"
      />
      <PageCard
        name="Update Working Pattern"
        description="Use this screen to update a working pattern"
        link="/update-work-pattern"
      />
      <PageCard
        name="Delete Working Pattern"
        description="Use this screen to remove selected working patterns saved in the system"
        link="/delete-work-pattern"
      />

      <PageCard
        name="Add Sprint"
        description="Use this screen to add a new sprint to the system"
        link="/add-sprint"
      />
      <PageCard
        name="Update Sprint"
        description="Use this screen to update a sprints details"
        link="/update-sprint"
      />
      <PageCard
        name="View Sprint"
        description="Use this screen to view all sprints saved in the system"
        link="/view-sprint"
      />
      <PageCard
        name="Delete Sprint"
        description="Use this screen to remove selected sprints saved in the system"
        link="/delete-sprint"
      />
      <PageCard 
        name="" 
        description=""
        link="" 
      />
      <PageCard 
        name="" 
        description=""
        link="" 
      />
      <PageCard 
        name="" 
        description=""
        link="" 
      />
      <PageCard 
        name="" 
        description=""
        link="" 
      />
      <PageCard 
        name="" 
        description=""
        link="" 
      />
      <PageCard 
        name="" 
        description=""
        link="" 
      />
       </CardColumns>
     </div>
     );
   }
   //show the correct cards for if a team manager is logged in 
   if (this.props.user.position == 'Team Manager') {
     return (
      <div>
        <h1>Welcome! {`${this.props.user.firstName} ${this.props.user.lastName}: ${this.props.user.position}`}</h1>
      <CardColumns>
      <PageCard
        name="Delete Associate"
        description="Use this screen to remove selected associates saved in the system"
        link="/delete-associate"
      />  
      <PageCard
        name="Delete Holiday"
        description="Use this screen to remove selected holidays saved in the system"
        link="/delete-holiday"
      />
      <PageCard
        name="Delete Sprint"
        description="Use this screen to remove selected sprints saved in the system"
        link="/delete-sprint"
      />
      <PageCard
        name="Delete Team"
        description="Use this screen to remove selected teams saved in the system"
        link="/delete-teams"
      />
      <PageCard
        name="Delete Working Pattern"
        description="Use this screen to remove selected working patterns saved in the system"
        link="/delete-work-pattern"
      />
      </CardColumns>
      </div>
     );
   }
   //show the correct cards for if a scrum master is logged in 
   if (this.props.user.position == 'Scrum Master') {
     return (
       <div>
         <h1>Welcome! {`${this.props.user.firstName} ${this.props.user.lastName}: ${this.props.user.position}`}</h1>
      <CardColumns>
      <PageCard
        name="Add Holiday" 
        description="Use this screen to add a new holiday to the system"
        link="/add-holiday"
      />
      <PageCard
        name="Update Holiday"
        description="Use this screen to update a holidays details"
        link="/update-holiday"
      />
      <PageCard
        name="Add Working Pattern"
        description="Use this screen to add a new working pattern to the system"
        link="/add-work-pattern"
      />   
      <PageCard
        name="Update Work Pattern"
        description="Use this screen to update a working pattern"
        link="/update-work-pattern"
      />
      <PageCard
        name="View Sprint"
        description="Use this screen to view all sprints saved in the system"
        link="/view-sprint"
      />
      </CardColumns>
       </div>
     );
   }
  }

  //create dashboard full of pagecard elements
  render() {
    return (
      this.showAssocCards()
    );
  }
}

export default Dashboard;