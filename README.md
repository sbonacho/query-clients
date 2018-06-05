# Command Handler - Create Client

This microservice acts as a Command Handler API for the functionality Create Client in a Insurance POC.

Is Asynchronous: Only send event to kafka, is not waiting for the last event sent by Saga microservice.

# Running Microservice

```
mvn package
java -jar target/query-clients-0.1.0.jar
```

or

```
mvn spring-boot:run
```

## The run.sh Script

This script is used to wrap how to start/stop the microservice. Write the way you want to start/stop the microservice

# Docker Generation

```
mvn install dockerfile:build
```

# Run the service

This command starts the service with ch-create-clientPorfolio name

```
docker run --rm -p 8082:8082 -dit --name query-clients sbonacho/query-clients
```

Watching logs

```
docker logs query-clients -f
```

Stopping the service

```
docker stop query-clients
```

# Issues

- If spring boot starts and kafka is not up
    - 1. There is no error.
    - 2. If after that kafka starts CreateService never gets recovered. Restart service is needed.
- If kafka is stopped timeout return 200 OK
