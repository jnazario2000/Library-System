package test;

import project.User;
import project.Book;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserBookIntegrationTests {
    @Test
    void userBorrowsBook(){
        // Create user instance
        User user = new User("Steve Harvey", "User001");

        // Create book instance
        Book book = new Book("Java Programming", "Author A", "ISBN123");

        // User borrows the book
        user.borrowBook(book);
        assertFalse(book.isAvailable(), "Book should be unavailable after borrowing.");
    }

    @Test 
    void userReturnsBook(){
        // Create user instance
        User user = new User("Steve Harvey", "User001");

        // Create book instance
        Book book = new Book("Java Programming", "Author A", "ISBN123");

        // User borrows the book
        user.borrowBook(book);

        // User returns book
        user.returnBook(book);
        assertTrue(book.isAvailable(), "Book should be available after being returned.");
    }

    @Test
    void userGetBorrowedBooks(){
        // Create user instance
        User user = new User("Steve Harvey", "User001");

        // Create book instance
        Book book1 = new Book("Java Programming", "Author A", "ISBN123");
        Book book2 = new Book("Data Structures", "Author B", "ISBN456");

        // User borrows books
        user.borrowBook(book1);
        user.borrowBook(book2);

        // Add books to list
        List<Book> l = new ArrayList<Book>();
        l.add(book1);
        l.add(book2);

        // Get borrowed books
        assertTrue(l.equals(user.getBorrowedBooks()), "List of borrowed books should be accurate.");
        assertFalse(book1.isAvailable()&&book2.isAvailable(), "Books that have been borrowed should be unavailable.");
    }

    @Test
    void userBorrowsBookNotAvailable(){
        // Create user instance
        User user1 = new User("Steve Harvey", "User001");
        User user2 = new User("Jim Carrey", "User002");

        // Create book instance
        Book book = new Book("Java Programming", "Author A", "ISBN123");

        // user1 borrows book
        user1.borrowBook(book);

        // user2 tries to borrow the same book
        user2.borrowBook(book);
        // Get borrowed books
        assertTrue(user2.getBorrowedBooks().isEmpty(), "List of borrowed books for user2 should be empty.");

    }

    @Test
    void userGetBorrowedBooksNoneBorrowed(){
        // Create user instance
        User user = new User("Steve Harvey", "User001");

        // Get borrowed books
        assertTrue(user.getBorrowedBooks().isEmpty(), "List of borrowed books should be empty.");
    }
}