# qualitymatters-springboot-restfulapi

## Application Overview
A simple springboot application service that manages bookings (any kind of booking - use your imagination). 
   
It stores bookings in an H2 in-memory database, with access via JPA.  The app is wrapped with Spring MVC layer to allow access over the Internet.

The application also references HATEOES Spring project aimed to write hypermedia-driven outputs.

All of this was created to overcome the freeware Restful API sites limitations encountered on the internet (and we understand that, as everything comes with a price), enabling successful Restful API implementation without restrictions.

## Application Run

```terminal
mvn clean spring-boot:run
```

## Basic Authentication
Every API call requires basic authentication, therefore, when calling the API programmatically, the below credentials needs to be passed

Username: admin  
Password: secret

Credentials can be modified in the application.properties file.

### Swagger Access
Run the Application, and navigate to the below URL to get all Swagger tests functioanlity towards the APIs.  This helps in automating API calls and making sure that the APIs are returning a value. 

```terminal
http://localhost:8080/swagger-ui/index.html
```
Swagger access needs basic authentication.

