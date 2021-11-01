# distanceCalculatorV2
Calculator for calculating the distance between cities by coordinates

The application has endpoints:
1)view all cities, and their coordinates in the database;
2)upload a xml file with a list of cities, and a list of distances between cities;
3)calculation of the distance on the sphere between coordinates;
4)calculation of the distance between cities based on the distance matrix in the database 

Tools/Libraries used:
IDEA EE,
Maven,
MySQL db,
Liquibase (not fully configured),
Java 8,
JAXB,
Spring + Tomcat.

There is the example of xml file for the upload in the "resources" directory.

Also in the directory "resources" there are test examples with app "postmen" and url for endpoints.
