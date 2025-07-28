# Library Management System

A robust and efficient command-line interface (CLI) application built with Core Java and MySQL for managing all the essential operations of a library.

##  Overview

This project is a simulation of a real-world library management system. It provides a dual-menu interface for two types of users: Administrators and Students. 
It handles core functionalities such as managing book inventory, tracking student checkouts, and administering user access, with all data persistently stored in a MySQL database.

##  Key Features

* Dual User Roles:
  
    * **Administrator Menu:** Provides powerful tools for library staff to manage the entire system.
        * Add, update, or remove books from the inventory.
        * View the complete list of available books.
        * Manage student accounts and access rights.

    * **Student Menu**: A simple and intuitive interface for library members.
        * Browse and search for available books.
        * Check out a book from the library.
        * Return a previously checked-out book.
        * View their own checkout history.

* Database Integration: Utilizes a MySQL database to ensure data persistence and reliability.
* JDBC Connectivity: Employs the Java Database Connectivity (JDBC) API to seamlessly connect the Java application with the MySQL backend.
* Object-Oriented Design: Built using clean, object-oriented principles in Core Java for better maintainability and scalability.

## Tech Stack

* Backend: Core Java
* Database: MySQL
* Connector: Java Database Connectivity (JDBC)
