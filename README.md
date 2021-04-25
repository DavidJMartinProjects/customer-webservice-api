# Springboot based web microservice.
 - this project demonstrates the development of a RESTful web-service using an "api-driven first" approach.
 - with focus on clean, maintainable code & testing at the appropriate level to meet production quality standards.

## Install
To install the project, clone the repository and run the below command:
```
$ mvn clean install
```

## Frameworks used
- doc generation
  - open-api spec
- build
  - maven, maven code generator
- db
  - jpa, h2, RDBMS 
- utils
  - model-mapper
- test :
  - Integration Testing
    - @SpringBootTest 
  - Contract Testing
    - Spring Cloud Contract 
  
## API Documentation
 * The microservice exposes its api documentation through its actuator endpoints.
 * To view the service api docs, run the project & navigate to the below service management urls:

    - http://localhost:9090/actuator/openapi    
    - http://localhost:9090/actuator/swaggerui   


## additional info :
 - api docs are automatically generated from the openapi specification.
 - controller models and interfaces are automatically generated from the openapi spec, allowing them to be introduced in the code base.
    - by integrating the generated controller interfaces and response models, the code must always align to the behaviour outlined in the openapi specification.  
    - now, the openapi spec is "the single source of truth" in terms of the behaviour of the microservice.
    - this solves the production problem of changes in api's not being reflected in the microservice documentation. 

 - NB: for integration testing, Flux TestWebClient was used in favour of RestTemplate(soon to be deprecated).
