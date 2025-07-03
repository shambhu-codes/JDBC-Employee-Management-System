# JDBC-Employee-Management-System
A Java console application using JDBC for managing employee records in a MySQL database. Includes features like create, insert, update, view, delete, and drop operations on an employee table.
# JDBC Employee Management System 🗃
A Java console-based application that performs CRUD operations (Create, Read, Update, Delete) on an `employee` table using JDBC with a MySQL database.

## Features
-  Connect to an existing MySQL database
-  Create table (if not exists)
-  Insert new employee records
-  Update employee details
-  View all employee records
-  Delete employee by ID
-  Drop the table
  
##  Requirements
- Java JDK 8 or later
- MySQL Server installed
- MySQL JDBC Driver (Connector/J)
- Any Java IDE (e.g., NetBeans, IntelliJ IDEA, Eclipse,VSCode)

##  How to Run
1. Create a MySQL database (example: `company`)
2. Add the MySQL JDBC driver `.jar` to your project libraries
3. Copy the code from `JDBC.java` into your project
4. Run the program and follow the terminal prompts

## Sample Output
Enter MySQL Database Name Already Exist in DataBase:(e.g., company): company
Enter MySQL Password for 'root': ********
Database Connection Created!

* * * DATABASE MANAGEMENT MENU * * *

1.Create Table
2.Insert Data into Table
3.Update Data in Table
4.View Data from Table
5.Delete Data from Table
6.Drop Table
7.Exit
Choose an Option (1-7):

## Table Structure
employee
|____empid(primary key and auto_increment)
|____empname
|____salary
