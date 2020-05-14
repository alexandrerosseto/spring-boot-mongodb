# API Restful using Spring Boot + MongoDB

It is an API that registers Users who can be authors of posts or/and comments of posts.

## Author

**Alexandre Antonio Lana Rosseto** 
* *Initial work* - [wbshopping](https://github.com/alexandrerosseto/wbshopping) 
* My professional profile on [LinkedIn](https://www.linkedin.com/in/alexandrerosseto)

## License

This project is free to use and it was designed for demonstration purposes.

## Expectations

This project was designed to demonstrate the use of:

* Use of Spring Boot libraries
* Lambda expressions
* Knowledge of Exceptions hierarchy and inheritance
* Application of custom exceptions
* MVC with DTO standard
* Use of NoSQL database - MongoDB
* JUnit application
* Dependency injection to address the impedance between the relational structure and the NoSQL database

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them.

```
* Java 1.8
* Spring framework 2.2.6
* Swagger 2.9.2
* Maven
* MongoDB stable release
* JUnit4
```
Recomended complementary tools.

```
* [Mongo Compass](https://www.mongodb.com/products/compass)
* [Postman](https://www.postman.com)
```

### Installing

A step by step series of examples that tell you how to get a development environment running.

```
* Checklist Windows: 
- [MongoDB](https://www.mongodb.com) -> Download -> Community Server 
- Download and mark the option "Complete"
-- [MongoDB manual](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/) -> Set up the MongoDB environment 
* Create folder \data\db 
* Include on PATH: C:\Program Files\MongoDB\Server\3.6\bin (or similar to your version) 
* Test on a terminal (cmd for instance): mongod 
```
```
Checklist Mac:  https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/ 
 
* Install brew: 
	* https://brew.sh 
* Install MongoDB: 
	* brew install mongodb 
* Create folder /data/db:
	* sudo mkdir -p /data/db 
* Release access to the created folder
	* whoami (in order to see your username, ie: arosseto) 
	* sudo chown -Rv arosseto /data/db 
	* Test on a terminal (cmd for instance): mongod
```
```
From GitHub repository, use users.json and posts.json in order to import samples of data using Mongo Compass.
```

After you have loaded data into the database, make sure MongoDB is running and follow the instructions below to run a test.

## Running the tests

From the project root, go to src/test/java/com.arosseto.springbootmongodb and click on UserControllerTest.java with the right button
* choose Run as -> JUnit Test...

Note: Check on Run Configuration if JUnit 4 is selected.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

