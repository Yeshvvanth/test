# Palindrome REST-API

This repository contains the open source Java code for Palindrome REST API. Documentation can be found at: (http://13.53.136.40:8080/api/v2-docs)

## Steps to setup

### 1. Clone the application

```
    https://github.com/Yeshvvanth/Palindrome-API.git
```

### 2.

## Explore Rest Api's

The app defines follwing CRUD APIs.

| METHOD | PATH                      |
| ------ | ------------------------- |
| GET    | /api/messages             |
| POST   | /api/messages             |
| DELETE | /api/messages/{messageId} |
| GET    | /api/messages/{messageId} |

## PALINDROME REST API Sequence Diagrams

In the above sections, we have gone through all the components/layers we will be engaging to implement the RESTful service. The next step would be to see how can we use these layers to perform each of the functionalities. A sequence diagram would be a perfect tool to visualize and describe the flow of the process to develop the code. In this section, we will go through the sequence diagrams for each functionality.

1. Add Messages API – Sequence Diagram
   The below image shows the sequence diagram for the add new message functionality. As you can see, the request comes to the controller; then it goes through the Service layer; finally, it’s handled by the Data Access Object to create the document in the database.

![alt text](https://github.com/Yeshvvanth/Palindrome-API/blob/main/src/main/resources/Images/Post%20Request%20Rest%20API.png?raw=true)
