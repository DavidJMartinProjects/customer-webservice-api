# Springboot based web microservice.
 - This project is an example of clean code and architecture.
 - built using an "api driven first" approach to web service development.
  
#frameworks used:
- doc generation - openapi
- db - jpa, h2 
- utils: model-mapper
- test :
  - integration testing (@SpringBootTest) 
  - contract testing (Spring Cloud Contract framework)
  

#additional info :
 - for the integration tests, Flux TestWebClient was used in favour of RestTemplate(soon to be deprecated).