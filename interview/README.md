# Interview

## Engineering

Moblie app + Server

### Components

#### Mobile (WIP)

Developed in Android(Java), using MVVM architecture and with components:

- RxJava2
- Dagger2
- FastAndroidNetworking

Boilerplate of project based on: [https://github.com/MindorksOpenSource/android-mvvm-architecture](https://github.com/MindorksOpenSource/android-mvvm-architecture)

#### Server (WIP)

Developed in Java with Quarkus.io, with two microservices users and interview.
Each service has a repository, using Hibernate and Panache that connects to a database in Postgres, where the data is saved.

**Microservices**:

- Interview(_WIP_)
- Users:
  _ Endpoints:
  _ (POST) /v1/user -> to add user
  _ (POST) /v1/user/login -> to login a user
  _ (GET) /swagger-ui -> to see documentation
  _ (GET) /health -> to state of database documentation
  _ **Run**: `./start-users-api.sh`

Depolyed with Docker using 3 containers:

- Ngix: used as proxy to the other containers, it could use kubernetes but for the size of the project I'll only use docker.
- Users: one resource and one database, to add and login users
- Interview: with two resources and one database, one for the availability of the interviewers, other to schedule interviews.

It could use all the same database, but for the complete approach of microservices I'll make the real microservices approach.

## Usage

Login with a user with Interviewer role, or create if not exists.
Schedule your availability.

Login with a user with Candiadte role, or create if not exists.
Choose an free slot.
