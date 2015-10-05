### Simple RESTful web-service for phone service providers.
Allows you to perform CRUD operations on inquires with multiple attributes according to offered topics.

#### OPERATIONS
Retrieve list of available topics: GET / topics

* Get inquiry list for specified customer: GET /customers/{customerName}/inquiries

* Get specified inquiry for specified customer: GET /customers/{customerName}/inquiries/{inquiryId}

* Create inquiry for specified customer: POST /customers/{customerName}/inquiries

* Update specified inquiry for specified customer: PUT /customers/{customerName}/inquiries/{inquiryId}

* Delete specified inquiry for specified customer DELETE: / customers/{customerName}/inquiries/{inquiryId}

#### ATTENTION
Be aware: setting entity identifiers explicitly while performing CREATE operations is FORBIDDEN.

#### TESTS
To perform tests you need to execute "clean test" Maven goal.

#### DATA PERSISTENCE
Data persistence is provided by HSQLDB.
Changing default path to database in
'''
src\main\resources\config\application.properties
'''
is advised.

#### SQL SCRIPTS
Test data and database schema scripts are located at

'''
src\main\resources\data\hsqldb
'''

#### Installation

##### Clone the repository

##### Dependencies

The project requires the following dependencies be installed on the host machine:

* Java Development Kit 7 or later
* Apache Maven 3 or later

#### Running

The project uses [Maven](http://maven.apache.org/) for build, package, and test workflow automation.  The following Maven goals are the most commonly used.

##### spring-boot:run

The `spring-boot:run` Maven goal performs the following workflow steps:

* compiles Java classes to the /target directory
* copies all resources to the /target directory
* starts an embedded Apache Tomcat server

To execute the `spring-boot:run` Maven goal, type the following command at a terminal prompt in the project base directory.

```
mvn spring-boot:run
```

##### test

The `test` Maven goal performs the following workflow steps:

* compiles Java classes to the /target directory
* copies all resources to the /target directory
* executes the unit test suites
* produces unit test reports

To execute the `test` Maven goal, type the following command at a terminal prompt in the project base directory.

```
mvn clean test
```
##### package

The `package` Maven goal performs the following workflow steps:

* compiles Java classes to the /target directory
* copies all resources to the /target directory
* executes the unit test suites
* produces unit test reports
* prepares an executable JAR file in the /target directory

To execute the `package` goal, type the following command at a terminal prompt in the project base directory.

```
mvn clean package
```

The application distribution artifact is placed in the /target directory and is named using the `artifactId` and `version` from the pom.xml file.  To run the JAR file use the following command:

```
java -jar phone-service-0.0.1.jar
```

