# Demo API Project with Java 11, Spring Boot 2, Docker e PostgreSQL

This project is a demo bank API to save accounts and transactions and get account by id.

## Running Tests

To run tests, run the following command

```bash
  mvn clean install
```

## Installation

After running command above, go to root folder and create required services with docker:

```bash
  docker-compose up
```

## Improvements

We could use MongoDB instead of PostgreSQL, because it is horizontally scalable and also has a better performance when
we have a huge number of Read/Write operations.
    