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

### 1. Add Messages API – Sequence Diagram

The below image shows the sequence diagram for the add new message functionality. As you can see, the request comes to the controller; then it goes through the Service layer; finally, it’s handled by the Data Access Object to create the document in the database.

![alt text](https://github.com/Yeshvvanth/Palindrome-API/blob/main/src/main/resources/Images/Post%20Request%20Rest%20API.png?raw=true)

### 2. Get Messages API – Sequence Diagram

The HTTP GET request will be sent to the controller to get the Messages list. The request query parameters will be parsed to extract any searching, filtering and paging information within the controller.

Then the list of Messages will be retrieved for the search, filter and paging query from the database via the service and model. Finally, the retrieved message list will be sent back to the requester in the response object.

![alt text](https://github.com/Yeshvvanth/Palindrome-API/blob/main/src/main/resources/Images/Get%20Request%20Rest%20APi%20.png?raw=true)

### 3. Delete Messages API – Sequence Diagram

The HTTP DELETE request will be sent to the controller to delete a message. The request comes with the message id which needs to be removed from the database. The controller receives the request and checks if a message exists before initiating the process of deleting a message from the database with the help of service and model components.

![alt text](https://github.com/Yeshvvanth/Palindrome-API/blob/main/src/main/resources/Images/Delete%20Request%20Rest%20API.png?raw=true)
