# YouTap Interview Test
My submission for the Youtap Interview Test

# Summary
I have created a standalone microservice that exposes 1 API to provide the contact details of a single user.The user data exists in a third party external microservice so my the Microservice
**CONTACT-SERVICE-STAND-ALONE** consumes  this External API to provide search data.

The API url is as follows.

/getusercontacts

This API can accept two input parameters.
User ID or Username.

Your API returns a JSON response containing the users contact information. An example output is provided below.

{
    "id": 2
    "email": "Shanna@melissa.tv",
    "phone": "010-692-6593 x09125",
  }

The microservice searches using either the username or userId

I Have also created a microservice enviroment to illustrate The **CONTACT SERVICE** working in a microservice architecture. You can find the configuration below

# Stand Alone Configuration
**CONTACT-SERVICE-STAND-ALONE** - the microservice requested in the test runs at  port **8081**

# Microservice Configuration
**CONTACT-SERVICE** - the microservice requested in the test runs at  port **8081**

**SERVICE-REGISTRY** - Eureka Service Discouvery runs at default port **8761**

**API-GATEWAY** - Zuul Gateway runs at port **9090**

**CONFIG-SERVICE** - The Config server whic uses a git Url for easy configurtation updates runs at port **8082**

**HYSTRIX-DASHBOARD** - To monitor hystrix streams runs at port **8083**

## How to Run The Stand Alone Micro Service

You must have Maven, run the following command in a terminal window (in the complete) directory (contact-service-stand-alone):

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

4. You must have Maven, run the following command in a terminal window (in the complete) directory **(contact-service)**:

```bash
./mvnw spring-boot:run
```

5. You must have Maven, run the following command in a terminal window (in the complete) directory **(hystrix-dashboard)** :

```bash
./mvnw spring-boot:run
```
