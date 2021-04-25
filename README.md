# Springboot based web microservice.
 - this project demonstrates the development of a RESTful web-service using an "api-driven first" approach.
 - high focus on clean, maintainable code, tested at the appropriate level for production.
 
  
## frameworks used:
- doc generation: openapi
- build: maven, maven code generator
- db: jpa, h2 (relational db) 
- utils: model-mapper
- test :
  - integration testing (@SpringBootTest) 
  - contract testing (Spring Cloud Contract framework)
  

## additional info :
 - api documentation are automatically generated from the openapi specification.
 - controller models and interfaces are also automatically generated from the openapi spec, allowing them to be used in the code base.
    - by integrating the generated controller interfaces, the code must always align to the behaviour outlined in the openapi specification.  
    - now, the openapi spec is "the single source of truth" in terms of the behaviour of the microservice.
    - this solves the production problem of changes in api's not being reflected in the microservice documentation. 
 - for the integration tests, Flux TestWebClient was used in favour of RestTemplate(soon to be deprecated).