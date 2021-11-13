## Project Description
A RESTful web based microservice used to perform CRUD operations on Customer records.

This project demonstrates the development of a RESTful web-service using an _"api-driven first"_ approach.
With focus on clean, maintainable code & high value testcases.

## Technologies & Frameworks

- Open API
- Swagger UI
- SpringBoot
- Spring JPA
- PostgreSQL
- Flyway (db migrations)
- H2 
- Integration Testing

## API documentation
  -  The microservice exposes its api documentation through its actuator endpoints.
  -  To view the service api docs, run the project & navigate to the service management urls shown below.
     
     - http://localhost:8080/v3/api-docs 
     - http://localhost:8080/swagger-ui/index.html


- The OpenAPI Spec can be found at the below path: 

  -  /src/main/resources/openapi-spec/


## Integration Tests:
Integration testcases can be found at:

 -   src/test/java/com/customer/integration/usecase/


## CI/CD Pipeline (using CircleCi)
 - https://app.circleci.com/pipelines/github/DavidJMartinProjects/customer-webservice   



<!-- 

## Example API Usage:
http://localhost:8080/customers?search=city:paris,firstName:Joe
http://localhost:8080/customers?fields=id,lastName,firstName,email,city
http://localhost:8080/customers?pageNumber=1&pageSize=10&sortKey=id&sortDirection=DESC

http://localhost:8080/customers?search=city:athlone,lastName:hulston&fields=id,lastName,firstName,email,city

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