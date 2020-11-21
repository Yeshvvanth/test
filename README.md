# Palindrome REST-API

This repository contains the open source Java code for Palindrome REST API. Documentation can be found at: (http://13.53.136.40:8080/api/v2-docs)

## Steps to setup

### 1. Create an EC2 instance in aws cloud and ssh into it.

### 2. Download docker and aws-cli in the instance.

### 3. Create an IAM policy with ECR read/write permission and then create an IAM role with some name, then attach the policy to this role.

### 4. Now attach this role to the EC2 instance

### 5. Clone the repository from the EC2 instance

```
   git clone https://github.com/Yeshvvanth/Palindrome-API.git
```

### 6. Build the image using Dockerfile present in the repo.

```
   sudo docker build -t palindrome .
```

### 7. Login to ECR

```
  aws ecr get-login-password --region <region> | docker login --username AWS --password-stdin <account-id>.dkr.ecr.<region>.amazonaws.com
```

-replace <region> <account-id> of your preference

### 8. Tag the built image to ECR url

```
sudo docker tag palindrome:latest <account-id>.dkr.ecr.<region>.amazonaws.com/palindrome:latest
```

### 9. Push Docker Image to AWS ECR

```
sudo docker push <account-id>.dkr.ecr.<region>.amazonaws.com/palindrome:latest
```

### 10. To create MySql database

```
sudo docker run --name mysqldb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=<password> -e MYSQL_DATABASE=palindrome_app -d mysql
```

-replace <region> <account-id> of your preference

### 11. Create DB Table

```
sudo docker exec mysqldb -it sh
```

```
mysql -u root -p
```

```
USE palindrome_app
```

```
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(45) DEFAULT NULL,
  `palindrome` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

```

```
exit
```

### 12. PUll the image from ECR and RUN the conatiner

```
sudo docker run -p 8080:8080 --name palindrome --link mysqldb -d <account-id>.dkr.ecr.<region>.amazonaws.com/palindrome:latest
```

### To Access the API

```
13.48.44.117:8080/api/messages
```

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

### 2. Get Messages List API – Sequence Diagram

The HTTP GET request will be sent to the controller to get the Messages list. The request query parameters will be parsed to extract any searching, filtering and paging information within the controller.

Then the list of Messages will be retrieved for the search, filter and paging query from the database via the service and model. Finally, the retrieved message list will be sent back to the requester in the response object.

![alt text](https://github.com/Yeshvvanth/Palindrome-API/blob/main/src/main/resources/Images/Get%20Request%20Rest%20APi%20.png?raw=true)

### 3. Delete Messages API – Sequence Diagram

The HTTP DELETE request will be sent to the controller to delete a message. The request comes with the message id which needs to be removed from the database. The controller receives the request and checks if a message exists before initiating the process of deleting a message from the database with the help of service and model components.

![alt text](https://github.com/Yeshvvanth/Palindrome-API/blob/main/src/main/resources/Images/Delete%20Request%20Rest%20API.png?raw=true)

### 4. Get Message API – Sequence Diagram

The HTTP GET request will be sent to the controller to get a Message. The request comes with the message id which needs to be retrieved from the database. The controller receives the request and checks if a message exists before returning with the help of service and model components.

![alt text](https://github.com/Yeshvvanth/Palindrome-API/blob/main/src/main/resources/Images/Get%20a%20single%20Request%20Rest%20APi.png?raw=true)

## Implementation Architecture

The Restful service is implemented in a layered approach to achieve seperation of concerns.

### 1. Controller

In this API, the controller will handle the request, invoke services to perform that action, and process response to sending back to the requester. Often controller will make a sequence of service calls in orchestration to accomplish the request as designed.

### 2 . Service

This layer handles the business logic.
