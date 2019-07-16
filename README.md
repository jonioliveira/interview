# Interview

## Engineering
```
 +------------------------+
 |                        |
 |   Users Microservice   |<--------------+
 |                        |               |
 +------------------------+               |            +------------+
            ^                             +------------|            |
            |                                          | Mobile App |
            |                             +------------|            |               
 +------------------------+               |            +------------+
 |                        |               |
 | Interview Microservice |<--------------+
 |                        |               
 +------------------------+               
```

A mobile app developed in Android that request data to two microservices, one about users and other about interview. 
The interview microservice requests users data to users microservice.

## Components

### Mobile 

Developed in Android(Java), using MVVM architecture and with components:

- RxJava2
- Dagger2
- FastAndroidNetworking

Boilerplate of project based on: [https://github.com/MindorksOpenSource/android-mvvm-architecture](https://github.com/MindorksOpenSource/android-mvvm-architecture)

**NOTES**: 
  1. Tests: The Android application hasn't tests, it's something that I have no experience with tests in Android, I'm creating tests only in the current project and don't feel confident to add tests to the application.
  2. Choose of Java instead Kotlin: Kotlin is the new lang that become the Google peferred lang, once more, in my current job I'm using Kotlin but still feel more confident with Java.

#### Start

- **Run**: Connect to android studio and start app

### Server 

Developed in Java with Quarkus.io, with two microservices users and interview.
Each service has a repository, using Hibernate with Panache that connects to a database in Postgres, where the data is saved.
Each service has some tests to basic funcionalities.

**Microservices**:

- Interview:
  - (POST) /v1/slots -> to add a new slot
  - (POST) /v1/slots/date -> Get a list of slots from a day
  - (POST) /v1/slots/dateuser -> Get a list of slots from a day and user
  - (PUT) /v1/slots/schedule -> Schedule an interview
  - (DELETE) /v1/slots/{id} -> Delete a Slot
  - (GET) /swagger-ui -> to see documentation
  - (GET) /health -> to state of database documentation
  - (GET) /metrics/application -> to see some metrics
- Users:
  - (POST) /v1/user -> to add user
  - (POST) /v1/user/login -> to login a user
  - (GET) /v1/user/{id}} -> to see a user
  - (GET) /swagger-ui -> to see documentation
  - (GET) /health -> to state of database documentation
  - (GET) /metrics/application -> to see some metrics

It could use all the same database, but for the complete approach of microservices I'll make the real microservices approach.

#### Start

- **Run**: `./start-db.sh` to start the docker with DB
- **Run**: `api/interview-api/mvnw quarkus:dev` to start in dev mode with hot reloading
- **Run**: `api/user-api/mvnw quarkus:dev` to start in dev mode with hot reloading

API have Dockerfiles to build images to run in prod mode

**NOTES**:
  1. Usage of Quarkus: Quarkus is a new framework developed by Red Hat, that use among other Eclipse Microprofile.io, and something that I prefer to use in my personal projects because is simple to start with, fast and are becoming popular in the community.
  2. Start with Maven and not Docker: A feature of the Quarkus is it's hot reloading, and swagger for documentation and it's only available in dev mode.
  3. Differences in testing of microservices: User service use a H2 Database for testing while Interview use Mocks, the choice was because I need to mock the REST Client and Quarkus don't support H2 and mock at same time, yet.

#### Usage

Login with a user with Interviewer role, or create if not exists. Schedule your availability or delete one available.

Login with a user with Candiadte role, or create if not exists. Choose an free slot.

## Future Work

 - CI/CD
Note: The microservices are preprared to deploy in Kubernetes with Docker. Quarkus is "Kubernetes oriented".
