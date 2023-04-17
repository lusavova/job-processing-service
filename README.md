# Job Processing Service

Job Processing Service is a Spring Boot application that sorts a list of tasks based on their dependencies and returns the sorted list in the desired format.

## Table of Contents

[Installation]()</br>
[Usage]()</br>
[API Endpoints]()</br>

## Installation
To install and run the application, follow these steps:

1. Clone the repository:
```console
git clone https://github.com/lusavova/job-processing-service.git
```

2. Change into the project directory:
```console
cd job-processing-service
```
3. Build the project:
If you are on Mac or Linux:
```console
 ./gradlew build
```
If you are on Windows:
```console
gradlew.bat build 
```

4. Run the application:
For Mac or Linux:
```console
 ./gradlew run
```
For Windows:
```console
gradlew.bat run 
```

The application will now be running on http://localhost:8080.

5. Run the tests:
For Mac or Linux:
```console
 ./gradlew test
```
For Windows:
```console
gradlew.bat test 
```

## Usage

The application provides a RESTful API for sorting a list of tasks.
The input format for the API is a JSON payload, and the output format can be specified using a query parameter.

To use the API, make a POST request to the `/api/v1/tasks/sort` endpoint with a JSON payload containing a list of tasks to be sorted. Here is an example payload:

```json
{
  "tasks": [
    {
      "name": "task-1",
      "command": "touch /tmp/file1"
    },
    {
      "name": "task-2",
      "command": "cat /tmp/file1",
      "requires": [
        "task-3"
      ]
    },
    {
      "name": "task-3",
      "command": "echo 'Hello World!' > /tmp/file1",
      "requires": [
        "task-1"
      ]
    },
    {
      "name": "task-4",
      "command": "rm /tmp/file1",
      "requires": [
        "task-2",
        "task-3"
      ]
    }
  ]
}
```

The output format can be specified using the `outputFormat` query parameter.
The supported output formats are `JSON` and `SCRIPT`.

Here is an example request to sort tasks in JSON format:

```
curl -X POST \
  'http://localhost:8080/api/v1/tasks/sort?outputFormat=JSON' \
  -H 'Content-Type: application/json' \
  -d '{
  "tasks": [
    {
      "name": "task-1",
      "command": "touch /tmp/file1"
    },
    {
      "name": "task-2",
      "command": "cat /tmp/file1",
      "requires": [
        "task-3"
      ]
    },
    {
      "name": "task-3",
      "command": "echo '\''Hello World!'\'' > /tmp/file1",
      "requires": [
        "task-1"
      ]
    },
    {
      "name": "task-4",
      "command": "rm /tmp/file1",
      "requires": [
        "task-2",
        "task-3"
      ]
    }
  ]
}'
```

### API Endpoints

The following table lists the API endpoints provided by the application:
| Endpoint           | Method | Description                                                                                        |
|--------------------|--------|----------------------------------------------------------------------------------------------------|
| /api/v1/tasks/sort | POST   | Sort a list of tasks based on their dependencies and return the sorted list in the desired format. |
