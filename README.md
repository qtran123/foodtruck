# Foodtruck Project
Author: Quynh Tran

Date: July 26, 2020

# How to Run
1. Download the code to a `workspace` directory
1. Make sure Java 11 is installed
1. Open up IDE with Java/SpringBoot (I used IntelliJ)
      * You will need to enable Annotation Processing on IntelliJ
      * IntelliJ IDEA > Preferences > Annotation Processing > Enable Annotation Processing
1. In the terminal, build the project `$ ./gradlew build`
1. To run the app, type in `$ ./gradlew bootRun`

# Details
The only requirement:
* at least 5 food trucks to choose from a particular latitude and longitude.

Assumptions:
* The application is standalone
* Application will load seed data upon startup to in-memory
* Users can interact with the app via Browser or Swagger

### Using Browser (and Swagger)
* Go to `http://localhost:8080/swagger-ui.html`
* Navigate to `/` and hit `try it out` to do a query, hit `Execute`
* Navigate to `/foodtrucks` and hit `try it out` to do a query
    * Enter in Latitude, Longitude, and Max
    * Hit `Execute`
    
### Example Browser HTTP commands (without Swagger):
* Get All Food Trucks: 
        `http://localhost:8080/`
* Get Food Trucks by Point: 
        `http://localhost:8080/foodtrucks?latitude=37.792385444513&longitude=-122.443991509437`
* Get Food Trucks by Point with Max Results:
        `http://localhost:8080/foodtrucks?latitude=37.792385444513&longitude=-122.443991509437&max=1`
    
### Storage:
* In-memory storage 
* Caching is an possibility for frequent food truck searches

### Tech Stack
* Springboot
* Java 11
* Gradle

### Code Design
* Provide REST Endpoints in FoodTruckController with getAllFoodTrucks and getFoodTrucksByCoordinates
* FoodTruckService class handles the processing of closest food trucks to user provided coordinates
* FoodTruckRepository class loads FoodTruck csv upon startup and provides the list of foodtrucks
* DistanceFunction class calculates the distance between two coordinates using the haversine-formula
* Domain class called FoodTruck to house all fields in the FoodTruck records

### PseudoCode for FoodTruck Service
* Given the coordinates
* Validate data is correct
* Do a map of all foodtrucks and calculate the distance in KM between two points
* Collect all results to a list in a PAIR <Distance, Food Truck>
* Sort by comparing distance 
* Return the results based on max results asked by user

### Tradeoffs with my current approach
* The CSV file contains only San Francisco data with ~600 records
* Limited Memory Storage
    * We have limited memory storage and if we were to expand the dataset to include all of California or country, we would
use up all the memory in the computer
    * Solve: We would divide up the datasets by region or city to make it easier to search
* Limited Processing Power
    * We needlessly calculates over the 600 records for every query.  This is a waste of resource.
    * I would recommend caching the queries and search results.  
* Data persistence
    * I chose to import data in each time I start up the server for quick tests, however
    long term, I would recommend storing the data in the database for quick access.
    * I would also integrate directly with the open data website to get updates on new food trucks in the area
* Scalability
    * The application is currently standalone
    * If there is a req for # of inputs, I would consider putting it behind a loadbalancer
    * I would also allow the application instances to use a shared cache / database so that
    data is managed in one place.

### Production Quality
* The code is intended to run locally
* Additional development setup in future
    * Concourse or Jenkins pipeline with security scanning tools (SonarQube, Fortify, OWASP)
    * Connect pipeline with Nexus or Artifactory to store versionings
    * Push built artifact to development space on the cloud to ensure deployability of application
    * Manage credentials with Credhub or Vault
    * Manage configurations with SpringBoot Configuration Manager (for configuration managements) 
    
* When you deploy this application to Pivotal Cloud Foundry or in a Docker Container,
you will need to do additional things:
    * Integrate this application with Identity Provider to enable single sign on
    * Integrate with a database so data is persisted 
    * [Future] Defining roles and responsibilities to manage user access to resources
    * [Consider] Integrating with front end application to visualize the code

            