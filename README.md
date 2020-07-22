# Vehicle Logistics Service

## 1. Preconditions
Vehicle geolocation

There is a large amount of vehicles (millions).
Each vehicle is equipped with a GPS tracker.
The tracker periodically sends vehicle geolocation to server (about every ten seconds).
The task is to create a RESTful web service that will:
- Receive current coordinates from vehicle GPS trackers;
- On request return the list of vehicles that are located within a given rectangle on the map. The rectangle coordinates are provided as the request params.

##### 1.a Assumption for development:
A vehicle can be identified by its vehicleId, which is an integer from 1 to max_int;
Coordinates are decimal values in range -90...90 for latitude and -180...180 for longitude.

## 2. Technical description of the solution

API-first approach is implemented in this project. 
API is described in the file with path "src/main/resources/swagger/api.yml". 
It allows us to generate controller interfaces and DTO object by adding "openapi-generator-maven-plugin" to pom.xml.
DTO object includes value limits, which are checked by Java validation.
Errors are handled using ExceptionHandler annotation, which is covered by ControllerAdvice in a single file.
Vehicle data is stored in PostgreSQL DB using Spring JPA  through the repository layer and entity-class. The fields, which are used in conditions for selection, are covered by indexes (by default it is the b-tree index for PostgreSQL, which is appropriate for range selection).
Interaction between controller and repository goes through the service layer.
Tests are included in the project.
API documenting is implemented with Swagger help.

## 3. Deployment
PostgreSQL DB with name "vehicle_logistics" and credentials: "username: vehicle_admin" and "password: test12345" must be created before project building.

#### 3.1 Building of app

    mvn clean package -DskipTests
    
#### 3.2 Launch of app

    java -jar vehiclelogistics-0.0.1-SNAPSHOT.jar  
    
## 4. Using
- Receive current coordinates from vehicle GPS trackers:

It is POST request to URL:

    http://localhost:8080/vehicle/location

with request body, for example:

    {
      "latitude": value(double),
      "longitude": value(double),
      "vehicleId": value(integer)
    }
    
curl:

    curl -X POST "http://localhost:8080/vehicle/location" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"latitude\": 0,  \"longitude\": 0,  \"vehicleId\": 0}"
    
- On request return the list of vehicles that are located within a given rectangle on the map. The rectangle coordinates are provided as the request params.

It is GET request with parameters(for example) to URL:
    
    http://localhost:8080/vehicle/vehicles?bottomLatitude=0&leftLongitude=0&rightLongitude=50&topLatitude=50
        
curl:
    
    curl -X GET "http://localhost:8080/vehicle/vehicles?bottomLatitude=0&leftLongitude=0&rightLongitude=50&topLatitude=50" -H  "accept: application/json"
    
## 5. Testing
#### 5.1 Unit tests
To launch your app's tests, run:

    mvn clean test
    
#### 5.2 Checking API through Swagger API Documentation
The app must be launched. Open address in a browser:

    http://localhost:8080/vehicle/swagger
    
Choose the controller and the method for checking.