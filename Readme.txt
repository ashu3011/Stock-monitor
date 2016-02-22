----------------------------------------------------
Logic Monitor
Stock Monitor
----------------------------------------------------

-----------------------
Introduction
-----------------------

Java program and a web application that monitors stocks and provides REST API end points. It uses Yahoo Finance API to get the stock information.
Following are the requirements for the program to run successfully.

1) Apache Tomcat Server
2) MySql and its script to create the stockprice table and Stocks databse included in this project.


------------------------
Running the applcation
------------------------

1) Use the MySql script to create the Database and the table. Its present in Database Dump
2) Maven build the project after confguring the Tomcat Server. Eclipse is recommended to run the project. Point to Tomcat 7 and run on server.
3) You may need to change the connection string if your MySql runs on port other then 3306.
4)If changed, Set the connection variables in DatabaseClass.Java and 

The user is "root" and password is "logicmonitor" for mysql

-----------------------
API Use case
-----------------------

A user interface is provided to use the API. However if needed otherwise, below are the ways to use it.


The url depends on the host server. In Examples below I am using localhost

1) List all companies : The GET method list all companies using url:         http://localhost:8080/StockAPI/webapi/companies/list

2) Add Company : A POST request is requored to add company using url:        http://localhost:8080/StockAPI/webapi/companies/add/<company Stock Symbol>

3) Delete Company: A DELETE request is requored to delete company using url: http://localhost:8080/StockAPI/webapi/companies/delete/<company Stock Symbol>

4) Stock Performance history: GET query is required to get performance, url: http://localhost:8080/StockAPI/webapi/companies/history/<company Stock Symbol>


----------------------------
Screenshots
---------------------------
Screenshots are provided in the folder screenshots


-----------------------------
Architecture
----------------------------

The project consists of a Stand alone stock information downloader that uses the Yahoo Finance API (http://financequotes-api.com/). The downloder connects to MySql Database and if not already present, inserts the stock info in table consisting of (Name, currency, symbol, price).

The project also consists of a Web application which provides RESTfull API for the above mentioned CRUD operations. It is made on JAX-RS and consists of following packages.

1) Databse : Consists of database class that performs operation in database and returns values if required.

2) Model: Models the data, in our case just the company

3) Service: Performs operations on the model and returns the results.

4) StockAPI: API resources that maps to the URLs.

 



