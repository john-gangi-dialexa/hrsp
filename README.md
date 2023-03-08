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
        - [***Database Setup:*** I used a free-tier AWS RDS Postgres instance (db.t4g.micro) to "infrastructure" the  data storage layer. If you choose to follow this approach, ensure that your application/IP address is included in the designated security group that is authorized to access the AWS resource.](#database-setup-i-used-a-free-tier-aws-rds-postgres-instance-dbt4gmicro-to-infrastructure-the--data-storage-layer-if-you-choose-to-follow-this-approach-ensure-that-your-applicationip-address-is-included-in-the-designated-security-group-that-is-authorized-to-access-the-aws-resource)
      - [**Create getting-started app**](#create-getting-started-app)
      - [**Our first query (GraphQl)**](#our-first-query-graphql)
      - [**Our first migration (Postgres/Flyway)**](#our-first-migration-postgresflyway)
  - [Installation:](#installation)
  - [Usage](#usage)
  - [Contributing](#contributing)

## Development Log
---

#### **Prepare the environment**
Check to make sure that all prequisite software is installed: 

- Java 19
- Gradle 8
- VS Code 1.76 (Optional)
- PG Admin 4 (Optional)

##### ***Database Setup:*** I used a free-tier AWS RDS Postgres instance (db.t4g.micro) to "infrastructure" the  data storage layer. If you choose to follow this approach, ensure that your application/IP address is included in the designated security group that is authorized to access the AWS resource.



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
 Now when we build, gradle will include this schema in the project. We can start satisfying the schema by writting our first controller (`UserController.java`):

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

#### **Our first migration (Postgres/Flyway)**

Flyway provides an uncomplicated interface for easy version control over a Postgres resource (i.e. migration pattern). 

The simplest way to run flyway is to:
1. make sure to include Flyway Migration as a dependency from start.spring.io
2. create an initial schema at the below directory (following the naming convetion of V1__Name ... V2__Name2 ... and so on):
`resources/db/migration/V1__Create_first_db_schema.sql`

to start with something simple:
```sql
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL
);
```
3. add environmental variables for postgresql database connection:


For mac users edit ~./zshenv:
```bash
export HRSP_DB_URL='jdbc:postgresql:{URL}:{PORT}/{DB}'
export HRSP_DB_USER='{USER}'
export HRSP_DB_KEY='{PASSWORD}'
```
4. Add a config class to hold our connection logic and inject it into the main driver:

so hrsp/config/FlywayConfig.java will look like this:
```java

package com.dialexa.hrsp.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Value("${HRSP_DB_URL}")
    private String url;

    @Value("${HRSP_DB_USER}")
    private String user;

    @Value("${HRSP_DB_KEY}")
    private String password;

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                              .dataSource(url,user,password)
                              .load();
        flyway.migrate(); //triggers migration
        return flyway;
    }
}

```

and we inject it into our main driver with this:

```java

package com.dialexa.hrsp;

import org.springframework.boot.SpringApplication;

import org.springframework.context.annotation.Import; // import this

import com.dialexa.hrsp.config.FlywayConfig; //import this

@SpringBootApplication
@Import(FlywayConfig.class) //add this annotation
public class HrspApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrspApplication.class, args)
    }
}

```

Now when you run the applcation (`./gradlew -bootRun`), you should see successfull migration logs in the console output.


---

## Installation:

(Instructions for MacOS)

**Prerequisites:**

- Java 19
 ```zsh
brew install java
```
- Gradle 8

```zsh
brew install gradle
```
  
**Other Tooling:**
- PG Admin
- VS Code
- .zsh


## Usage

TODO: How to use the project, examples and demos.


## Contributing

TODO: Guidelines for contributing to the project. 

1. Fork the repository
2. Create a new branch
3. Make your changes and commit them
4. Push to your fork and submit a pull request

