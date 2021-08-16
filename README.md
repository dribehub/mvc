# Order Management System
Spring Boot MVC Application using 
[MySQL database](https://www.mysql.com/), 
[JPA repository](https://www.ibm.com/docs/en/was-liberty/base?topic=overview-java-persistence-api-jpa) 
and [Thymeleaf templates](https://www.thymeleaf.org/).

## Spring Security
Implementation of [Spring Security](https://spring.io/projects/spring-security), dynamic user roles and password encryption.

## Admin Privileges
Admins are granted with CRUD privileges (create, read, update, delete customers, items, categories etc...).

#### To be implemented
* All users can view and edit their profiles (update full name, email address, password etc...).
* Customers can request admins to add specific items and categories in the system.
* Admins can respond (approve or dismiss) to above mentioned requests.
* Admins can send simple messages to customers.
* Customers can view admin messages directly from the home feed.
* Admins can elevate customer privileges on request.
