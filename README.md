# Springboot based web microservice.
 - This project is an example of clean code and architecture.
 - it was developed using the "api-driven first" approach.
  
## frameworks used:
- doc generation - openapi
- db - jpa, h2 
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