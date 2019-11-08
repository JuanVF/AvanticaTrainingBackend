# AVANTICA Training 
### PROAA Project

------------

![](https://img.shields.io/maven-central/v/org.springframework.boot/spring-boot-starter-data-jpa?label=spring-boot-starter-data-jpa) ![](https://img.shields.io/maven-central/v/org.springframework.boot/spring-boot-starter-web?label=spring-boot-starter-web) ![](https://img.shields.io/maven-central/v/org.springframework.boot/spring-boot-starter-security?label=spring-boot-starter-security) ![](https://img.shields.io/maven-central/v/com.google.code.gson/gson?label=gson) ![](https://img.shields.io/maven-central/v/com.squareup.okhttp3/okhttp?label=gson) ![](https://img.shields.io/maven-central/v/io.jsonwebtoken/jjwt?label=jjwt) 

------------

### Setup

- You should first download and install MySQL

- Then make a database named '*proaa*'

- clone the backend repo by

> git clone https://github.com/JuanVF/AvanticaTraining.git

- open the project in IntelliJ

- In the application.properties you should change your MySQL username and password by:

```
spring.datasource.username =  <your db username>
spring.datasource.password = <your db password>
```


- To setup this project you should install node for the fronted [click here](https://nodejs.org/es/download/ "click here").

- Then clone the frontend repo by:

> git clone https://github.com/JuanVF/AvanticaTraining.git

- Install node dependencies by

> npm install

- and run the frontend by:

> npm start

------------


The purpose of this is to learn how to make projects with React + Spring Boot.

The challenges are:

**Backend:**

- The respectives Model for User, Topic and Resources.
- The respectives REST Controller to handle the URL's
- The DB to use is MySQL
- All the API should be saved with a JWT Authorization.

**Frontend:** [Click here to see frontend code](https://github.com/JuanVF/AvanticaTraining "Click here to see frontend code")

- Make a Home UI with a Top Ten 10 table

![](https://i.imgur.com/CvKHPRr.png)

- Make other page with email-password or with Facebook login

![](https://i.imgur.com/qPIXAo6.jpg)

- Make a SignUp UI to register with Facebook or with email-password

![](https://i.imgur.com/Sw7Y8lZ.jpg)

- Make a Topic UI where you can add a Topic and see a table with all the topics added, also this table should have the option to edit or delete a selected topic

![](https://i.imgur.com/OguCTGL.jpg)

- Make a Resource UI with the same Topic UI functions

![](https://i.imgur.com/rBSAdYs.jpg)
