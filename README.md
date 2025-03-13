# qualitymatters-springboot-restfulapi

## Application Overview
This application creates a simple springboot application service that manages hotel bookings.  It stores bookings in an H2 in-memory database, with access via JPA.  The app is wrapped with Spring MVC layer to allow access over the Internet.

The application also references HATEOES Spring project aimed to write hypermedia-driven outputs.

All of this was created to overcome the freeware Restful API sites limitations encountered on the internet (and we understand that, as everything comes with a price), enabling successful Restful API implementation without restrictions.

## Application Run

```terminal
mvn clean spring-boot:run
```




