# Hotel Reservation System Project

The basic purpose of this project is to implement a **room reservation** service via Java SpringBoot. 
  
  ```
  Development Outline:
   1. Prepare the environment
   2. Create getting-started app
   3. Our first query (GraphQl)
   4. Our first migration (Postgres)
   5. User Authentication
   6. Tables & Logic for Availability 
   7. Additional Features
  ```

## Table of Contents

- [Hotel Reservation System Project](#hotel-reservation-system-project)
  - [Table of Contents](#table-of-contents)
  - [Development Log](#development-log)
      - [**Prepare the environment**](#prepare-the-environment)
      - [**Create getting-started app**](#create-getting-started-app)
      - [**Our first query (GraphQl)**](#our-first-query-graphql)
      - [**Our first migration (Postgres)**](#our-first-migration-postgres)
  - [Installation](#installation)
  - [Usage](#usage)
  - [Contributing](#contributing)

## Development Log
---

#### **Prepare the environment**
Check to make sure that all prequisite software is installed: 

- Java 19
- Gradle 8
  - `Brew install gradle`
- VS Code 1.76 (Optional)
- PG Admin 4 (Optional)

---
#### **Create getting-started app**

Begin by creating a template for the application at [start.spring.io](https://start.spring.io) 

The settings for this project are:

    Project: Gradle Groovy
    Language: Java 19
    Spring Boot: 2.7.9
    Package: JAR
    Dependecencies: 
     - spring-boot-starter-graphql
	 - spring-boot-starter-web
	 - spring-boot-starter-data-jdbc
	 - flyway-core
     - postgresql
---

#### **Our first query (GraphQl)**

Before connecting to any persitance layer, we "wire-up" our first query.

The basic shape of the n-tier architecture for this application is as follows:

```
--- java/com/dialexa/hrsp/

  ** Presentation Layer **
  --- controller/
    --- UserController.java

  ** Data Access Layer **
  --- dao/
    --- UserDao.java (interface)
    --- jdbc/
      --- UserDaoJdbc.java 

  ** Domain Layer **
  --- model/
    --- User.java (POJO)

  ** Application Layer **
  --- service/
    --- UserService.java

  ** Spring Boot Application **
  --- HrspApplication.java

--- resources/

  ** GraphQL Schema **
  --- schema.graphqls
```


 The road to our first GraphQL query starts with `schema.graphqls`:
 ```graphql

type Query {
  roomByRoomNumber(roomNumber: String!): Room
}

type Room {
    id: String
    roomNumber: String
}
 ```
 Now when we build, gradle will include this schema in the project. We can start satisfying the schema by writting our accompanying controller (`UserController.java`):

 ```java

...

@Controller
public class RoomController {
    @QueryMapping
    public Room roomByRoomNumber(@Argument String roomNumber) {
        return Room.getByRoomNumber(roomNumber);
    }
}
 ```
The key annotations here are the `@QueryMapping` and `@Argument` annotations from `spring-boot-starter-graphql` package.

We can continue by building out a POJO for Room (hardcoding some dummy data before we have any DB/service layer) (`Room.java`):
```java

...

public class Room {

    private String id;
    private String roomNumber;

    public Room(String id, String roomNumber){
        this.id=id;
        this.roomNumber=roomNumber;
    }

    public static List<Room> rooms = Arrays.asList(
        new Room("Presidents' Suite", "01"),
        new Room("Ocean View Room 1", "02"),
        new Room("Ocean View Room 2", "03"),
        new Room("Harborside 2 Double", "04"),
        new Room("Harborside 1 King", "05"),
        new Room("Harborside 2 Queen", "06")
    );

    public static Room getByRoomNumber(String k) {
        return rooms.stream().filter(room -> room.getRoomNumber().equals(k)).findFirst().orElse(null);
    }

    public Object getRoomNumber() {
        return this.roomNumber;
    }

    public String getId() {
        return this.id;
    }

}

```

This should be everything needed to run our very basic data fetching query.

Make sure you add:

```
spring.graphql.graphiql.enabled=true
spring.graphql.graphiql.path=/graphiql
```
to `applcation.properties`

Now run `./gradlew bootRun` from the top-level project directory and you should be able to see an interactive query tool. You can run initial query to see this working:

```graphql 
query {
  roomByRoomNumber(roomNumber:"01") {
    id
  }
}
```

---

#### **Our first migration (Postgres)**

## Installation

The process to get up and running with the repo. 

**Prerequisites:**
- Java 19
- PG Admin

```sh
npm install
```

## Usage

How to use the project, examples and demos.


## Contributing

Guidelines for contributing to the project. 

1. Fork the repository
2. Create a new branch
3. Make your changes and commit them
4. Push to your fork and submit a pull request

