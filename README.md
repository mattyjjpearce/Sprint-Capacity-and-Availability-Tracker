System Description

This system is intended to be used in an office environment as a complete solution to handle employee leave and plan sprints in advance. The system uses an artificial neural network to predict when employees will be on leave. The goal of this is to aid a scrum master or equivalent member of staff to plan sprints based on employee availability and sprint capacity.

The backend is programmed using Java with Spring Boot. Data access is handled by Hibernate. The frontend is a Node.js application. The API consists of 33 endpoints in total. Details regarding the API will be found in the API section of this manual. 

The system is based on 6 main features. Employees, Holidays, EmployeeLeaveInfo, Sprints, Teams and WorkPattern. These features and their meaning will be explained in detail in the SystemTerminology section.






Build Manual

Backend

Open IntelliJ.
Import the directory ./Source/Backend/CapitalOne as a Maven project.
Execute the file src/main/java/com/team19/Main.java.

The application.properties file located in src->main->resources contains the code to connect the servlet to a database. The following three lines need to be changed.

spring.datasource.url=jdbc:mysql:{the url of the database}
spring.datasource.username={username used to access database}
spring.datasource.password={password}

Frontend

After executing the backend, execute the following commands

> cd ${ProjectRoot}/Source/Frontend/sprint-tracker/
> npm install 
> npm start 

This will install the necessary modules to run the Frontend and then it will start the application.

Prediction Model

Right now the prediction service is in its experimental phase. Therefore only 2 files (data_generator.py and holiday_forecasting.ipynb) are required to reproduce the results.

The jupyter notebook (holiday_forecasting.ipynb) can either be viewed directly on GitLab (link to file), or with the jupyter notebook app.

API

The Backend Endpoints (Contract) spreadsheet contains a detailed explanation of all endpoints.







Database
The database used to store data was hosted through Amazon Web Services’ (AWS) Relational Database Service (RDS). To connect to this database, the following information is required:


This information is inserted into MySQL Workbench, a tool used to perform queries on MySQL DBMS.


This will allow you to manage the database whilst developing the backend. It is essential that the frontend cannot interact with the database directly for security reasons.













