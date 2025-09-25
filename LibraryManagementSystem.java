import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Book Class
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issueBook() { isIssued = true; }
    public void returnBook() { isIssued = false; }

    @Override
    public String toString() {
        return "Book ID: " + id + " | " + title + " by " + author +
                (isIssued ? " (Issued)" : " (Available)");
    }
}

// User Class
class User {
    private int id;
    private String name;
    private List<Book> borrowedBooks;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "User ID: " + id + " | Name: " + name;
    }
}

// Library Class
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Add Book
    public void addBook(Book book) {
        books.add(book);
    }

    // Add User
    public void addUser(User user) {
        users.add(user);
    }

    // Show all books
    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Show available books
    public void showAvailableBooks() {
        boolean found = false;
        for (Book book : books) {
            if (!book.isIssued()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books are available right now.");
        }
    }

    // Show issued books
    public void showIssuedBooks() {
        boolean found = false;
        for (Book book : books) {
            if (book.isIssued()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books are issued.");
        }
    }

    // Show all users
    public void showUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    // Issue book
    public void issueBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book != null && user != null) {
            if (!book.isIssued()) {
                book.issueBook();
                user.borrowBook(book);
                System.out.println(user.getName() + " issued " + book.getTitle());
            } else {
                System.out.println("Book is already issued!");
            }
        } else {
            System.out.println("Book/User not found!");
        }
    }

    // Return book
    public void returnBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book != null && user != null) {
            if (book.isIssued() && user.getBorrowedBooks().contains(book)) {
                book.returnBook();
                user.returnBook(book);
                System.out.println(user.getName() + " returned " + book.getTitle());
            } else {
                System.out.println("This book was not issued to " + user.getName());
            }
        } else {
            System.out.println("Book/User not found!");
        }
    }

    // Search Book
    public Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public User findUser(int id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }
}

// Main Class with Menu
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n====== Library Menu ======");
            System.out.println("1. Add new books");
            System.out.println("2. Add a new user (with option to issue a book)");
            System.out.println("3. Show all books");
            System.out.println("4. Show available books");
            System.out.println("5. Show issued books");
            System.out.println("6. Show all users");
            System.out.println("7. Return a book");
            System.out.println("8. Search a book by ID (issue/return)");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("How many books do you want to add? ");
                    int count = sc.nextInt();
                    sc.nextLine(); // consume newline
                    for (int i = 0; i < count; i++) {
                        System.out.println("\nEnter details for Book " + (i + 1) + ":");
                        System.out.print("Enter Book ID: ");
                        int bookId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Book Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Author Name: ");
                        String author = sc.nextLine();
                        library.addBook(new Book(bookId, title, author));
                    }
                    System.out.println(count + " book(s) added successfully!");
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter User Name: ");
                    String userName = sc.nextLine();
                    library.addUser(new User(userId, userName));
                    System.out.println("User added successfully!");

                    // Ask if they want to issue a book immediately
                    System.out.print("Do you want to issue a book to " + userName + " now? (yes/no): ");
                    String ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("yes")) {
                        library.showAvailableBooks();
                        System.out.print("Enter Book ID to issue: ");
                        int issueBookId = sc.nextInt();
                        library.issueBook(issueBookId, userId);
                    }
                    break;

                case 3:
                    System.out.println("\nAll Books:");
                    library.showBooks();
                    break;
                case 4:
                    System.out.println("\nAvailable Books:");
                    library.showAvailableBooks();
                    break;
                case 5:
                    System.out.println("\nIssued Books:");
                    library.showIssuedBooks();
                    break;
                case 6:
                    System.out.println("\nAll Users:");
                    library.showUsers();
                    break;

                case 7:
                    System.out.print("Enter Book ID to return: ");
                    int rbId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int ruId = sc.nextInt();
                    library.returnBook(rbId, ruId);
                    break;
                case 8:
                    System.out.print("Enter Book ID to search: ");
                    int searchId = sc.nextInt();
                    Book book = library.findBook(searchId);
                    if (book != null) {
                        System.out.println("Book Found: " + book);
                        if (!book.isIssued()) {
                            System.out.print("This book is available. Do you want to issue it? (yes/no): ");
                            sc.nextLine();
                            String issueAns = sc.nextLine();
                            if (issueAns.equalsIgnoreCase("yes")) {
                                System.out.print("Enter User ID: ");
                                int uid = sc.nextInt();
                                library.issueBook(searchId, uid);
                            }
                        } else {
                            System.out.print("This book is issued. Do you want to return it? (yes/no): ");
                            sc.nextLine();
                            String returnAns = sc.nextLine();
                            if (returnAns.equalsIgnoreCase("yes")) {
                                System.out.print("Enter User ID: ");
                                int uid = sc.nextInt();
                                library.returnBook(searchId, uid);
                            }
                        }
                    } else {
                        System.out.println("Book not found!");
                    }
                    break;
                case 9:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 9);

        sc.close();
    }
}
