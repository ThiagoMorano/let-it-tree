# Let it tree

## Overview

Let it tree is a small service to help track plants that need watering.
A personal project to experiment with Spring Boot and backend development with Java, it was bootstrapped using [Spring initializr](https://start.spring.io/)

## Prerequisites
* Java 17 or higher

## Usage
To run the project, you can use Docker Compose to set up both the application and its PostgreSQL database by running
`$ docker-compose up`

To run the tests suites, you can use the following Maven command
`$ ./mvnw test`

## API Endpoints

### `GET /api/v1/plant/`: Get all plants
This endpoint returns the complete set of available plants. No input data is required.
The response is a list of all plants in JSON format.


### `GET /api/v1/plant/{id}`: Get a plant by ID
This endpoint returns an individual plant by ID. The ID is provided as a URI parameter.
Responds with a 404 response conde in case no plant of given ID was found. Otherwise returns a 200 response code, and a plant in given format:
```
body: {
    id: String, /* UUID */
    name: String,
    lastWateringDate: String,
    daysBetweenWatering: Number /* int */
}
```


### `POST /api/v1/plant/`: Create a new plant
This endpoint creates a new plant. An example of the payload is provided below:
```
body: {
    name: String,
    lastWateringDate: String,
    daysBetweenWatering: Number /* int */
}
```
Responds with 201 response code, and a response body similar to the `GET /api/v1/plant/{id}` endpoint.


### `PUT /api/v1/plant/{id}`: Update a plant by ID
This endpoint updates an existing plant by ID. The payload format is the same as in `POST /api/v1/plant`.
Responds with a 204 if the plant was updated successfully or a 404 response if the plant wasn't found.


### `DELETE /api/v1/plant/{id}`: Delete a plant by ID
This endpoint deletes an individual plant by ID. The ID is provided as a URI parameter.
Responds with a 204 if the plant was deleted successfully or a 404 response if the plant wasn't found.


### `GET /api/v1/plant/need-water`: Get plants that need water
This endpoint returns a list of plants that need to be watered.
Response is an array of plants. In case no plant needs watering, the array is empty.