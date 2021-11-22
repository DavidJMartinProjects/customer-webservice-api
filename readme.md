# Project Description
A RESTful web based microservice used to perform CRUD operations on Customer records.

The API supports the following functionality:
 - <b>Pagination</b>
 - <b>Querying</b>
 - <b>Response field filtering</b>

This project was developed using an _"api-driven first"_ development approach, utilising OpenAPI.

With high focus on clean, maintainable code & behaviour driven testing.

## Technologies & Frameworks
### Development
- Open API
- Swagger UI
- SpringBoot
- Spring JPA
- PostgreSQL (production db)
- H2 (test db)
- Flyway (db migrations)
- Integration Testing
### Deployment
- Helm
- Docker
- Docker-Compose

##  Running the Project Locally
 -  Download this repository 
 -  Build the project with Maven using the below command
    ```
    mvn clean install -DskipTests
    ```
 -  Run the below docker-compose command
    ```
    docker-compose up
    ```    
 - The service is now accessible from port <b>8080</b> of your <b>localhost</b>
   

 - Open a browser and visit the below URL to confirm
    ```
    http://localhost:8080/customers
    ```

## Example API Usage:

 - <b>Querying</b> Customer records using the 'search' parameter
    ```
    http://localhost:8080/customers?search=city:paris,firstName:Joe
    ```
- <b>Filtering</b> Customer response fields using the 'fields' parameter
    ```
    http://localhost:8080/customers?fields=id,lastName,firstName,email,city
    ```
- <b>Pagination</b> support by specifying page the desired page parameters
    ```
    http://localhost:8080/customers?pageNumber=1&pageSize=10&sortKey=id&sortDirection=DESC
    ```
- Example api call using both querying and field filtering 
    ```
    http://localhost:8080/customers?search=city:athlone,lastName:hulston&fields=id,lastName,email,city
    ```

## API documentation
  -  The microservice exposes its api documentation via swagger.
  -  To view the api docs, run the project locally & navigate to the url shown below.
     ```
     http://localhost:8080/swagger-ui.html
     ```


- The OpenAPI Spec can be found at the below path:
  ```
  {root-directory}/openapi-spec/
  ``` 

## Database Migration
  - The database schema is built on startup using Flyway SQL scripts.
  - The Flyway migration scripts can be found at the below path:
    ```
    {root-directory}/src/main/resources/db/migration
    ``` 


## Integration Tests
 - Integration testcases can be found at:
    ```
    {root-directory}/src/test/java/com/customer/integration/usecase/
    ```

<!-- 

docker pull postgres:11
docker run --name dev-postgres -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres:11
# CREATE db coursedb
docker exec dev-postgres psql -U postgres -c"CREATE DATABASE coursedb" postgres

C:\Users\Dave> docker exec dev-postgres psql -U postgres -c"DROP DATABASE mydb" postgres
DROP DATABASE
C:\Users\Dave> docker exec dev-postgres psql -U postgres -c"CREATE DATABASE mydb" postgres
CREATE DATABASE

## Example API Usage:
http://localhost:8080/customers?search=city:paris,firstName:Joe
http://localhost:8080/customers?fields=id,lastName,firstName,email,city
http://localhost:8080/customers?pageNumber=1&pageSize=10&sortKey=id&sortDirection=DESC

http://localhost:8080/customers?search=city:athlone,lastName:hulston&fields=id,lastName,email,city

## Deployment

OS X, Windows, Linux:
    
```
>> mvn clean install 
>> skaffold run
```

## Frameworks used
- build
  - maven, maven code generator
- db
  - jpa, h2, RDBMS 
- utils
  - model-mapper
- doc generation
  - open-api spec, swagger ui
- test :
  - Integration Testing
    - @SpringBootTest 
  - Contract Testing
    - Spring Cloud Contract 
    
## Additional info 
 - api docs are automatically generated from the OpenAPI specification.
 - controller models and interfaces are generated from the OpenAPI spec, allowing them to be introduced in the code base.
    - by integrating the generated controller interfaces and response models, the code must always align to the behaviour outlined in the openapi specification.  
    - now, the OpenAPI spec is _"the single source of truth"_ in terms of the behaviour of the microservice.
    - this solves the production problem of changes in api's not being reflected in the microservice documentation. 

 - NB: for integration testing, Flux TestWebClient was used in favour of RestTemplate(soon to be deprecated).

## Development setup

To generate the required sources, build the project using the below command

```sh
>> mvn clean install 
```

## Release History

* 0.1.0
    * Introduced OpenAPI Spec
    * Introduced @SpringBootTest integration tests
* 0.0.1
    * Initial Release

 -->
