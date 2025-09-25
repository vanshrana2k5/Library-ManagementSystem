# 📚 Library Management System (Java)

A simple **console-based Library Management System** built in Java.
This project allows you to manage books and users, issue and return books, and search for books using their ID.

---

## Features

* Add multiple books at once (with ID, Title, Author).
* Add new users with ID and Name.
* Option to issue a book **directly while adding a new user**.
* Show:

  * All books
  * Available books
  * Issued books
  * All users
* Issue a book to a user.
* Return a book from a user.
* Search a book by ID and **issue/return directly**.

---

## Technologies Used

* **Java** (Core Java, OOP concepts like classes, objects, and lists)
* **Scanner** class for user input
* **ArrayList** for storing books and users

---

##  Project Structure

```
LibraryManagementSystem.java   # Main class with menu-driven system
Book.java                      # Book class
User.java                      # User class
Library.java                   # Library class (manages books & users)
```

*(In our case, all classes are in a single file for simplicity.)*

---

##  Sample Menu

```
====== Library Menu ======
1. Add new books
2. Add a new user (with option to issue a book)
3. Show all books
4. Show available books
5. Show issued books
6. Show all users
7. Issue a book
8. Return a book
9. Search a book by ID (issue/return)
10. Exit
```

---

##  Example Flow

1. Add 3 new books.
2. Add a user (with ID & Name).
3. Choose "yes" to issue a book immediately.
4. View available books → Issued books → Users.
5. Return a book.
6. Search for a book by ID and issue/return it directly.

---

##  Future Improvements

* Prevent duplicate Book IDs / User IDs.
* Save and load data from a file or database.
* Add user authentication (admin/user roles).
* GUI (Swing/JavaFX) version.
o you also want me to make a **sample repo description** (the short 1–2 line summary that shows under the repo name on GitHub)?
