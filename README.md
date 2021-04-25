# Springboot based web microservice.
 - this project demonstrates the development of a RESTful web-service using an "api-driven first" approach.
 - with high focus on clean, maintainable code & testing at the appropriate level to meet production quality standards.
 
## api docs :
 - to view the service api docs, run the project & navigate to the below service management urls:
  
     - http://localhost:9090/actuator   
     - http://localhost:9090/actuator/swaggerui   
     - http://localhost:9090/actuator/openapi
  
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