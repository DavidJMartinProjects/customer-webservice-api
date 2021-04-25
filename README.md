# Springboot based web microservice.
 - This project is an example of clean code and architecture.
 - built using an "api driven first" approach to web service development.
  
## frameworks used:
- doc generation - openapi
- db - jpa, h2 
- utils: model-mapper
- test :
  - integration testing (@SpringBootTest) 
  - contract testing (Spring Cloud Contract framework)
  

## additional info :
 - api documentation is automatically generated from the open api specification.
 - controller models and interfaces are also automatically generated from the openapi spec, allowing them to be used in the code base.
    - with this "api-driven first" approach, via the generated controller interfaces, the code must always align to the openapi specification & documentation.  
    - now, the openapi spec is "the single source of truth" in terms of the behaviour of the microservice.
    - this solves the production problem of changes in api's not being reflected in the microservice documentation. 
 - for the integration tests, Flux TestWebClient was used in favour of RestTemplate(soon to be deprecated).