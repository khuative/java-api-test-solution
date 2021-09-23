# Java API Test
My submission for the Java API Test

# Summary
We have a collection of users and we wish to create a micro service that will perform the basic CRUD operations (Create, Read Update & Delete), plus the ability to search for a user by their first name, last name and ID. The user information must be persisted to a database (can be any database: NoSQL or SQL DB)



# Microservice Configuration
**USER-SERVICE** - the microservice requested in the test runs at  port **8081**

**SERVICE-REGISTRY** - Eureka Service Discouvery runs at default port **8761**

**API-GATEWAY** - Zuul Gateway runs at port **9090**

**CONFIG-SERVICE** - The Config server whic uses a git Url for easy configurtation updates runs at port **8082**

**HYSTRIX-DASHBOARD** - To monitor hystrix streams runs at port **8083**



```bash
./mvnw spring-boot:run
```
## How to Run The whole Microservice architecture

1. You must have Maven, run the following command in a terminal window (in the complete) directory **(service-registry)**:

```bash
./mvnw spring-boot:run
```

2. You must have Maven, run the following command in a terminal window (in the complete) directory **(cloud-config-server)**:

```bash
./mvnw spring-boot:run
```

3. You must have Maven, run the following command in a terminal window (in the complete) directory **(cloud-gateway)**:

```bash
./mvnw spring-boot:run
```

4. You must have Maven, run the following command in a terminal window (in the complete) directory **(user-service)**:

```bash
./mvnw spring-boot:run
```

5. You must have Maven, run the following command in a terminal window (in the complete) directory **(hystrix-dashboard)** :

```bash
./mvnw spring-boot:run
```
