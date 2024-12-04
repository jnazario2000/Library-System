package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import project.Book;
import project.User;

public class UserTest {
    private User user;
    private Book book1;
    private Book book2;

    @Before
    public void setUp() {
        user = new User("John Doe", "USER001");
        book1 = new Book("Java Programming", "Author A", "ISBN123");
        book2 = new Book("Python Programming", "Author B", "ISBN456");
    }

    @Test
    public void testUserCreation() {
        // 1. assertEquals
        assertEquals("Name should match", "John Doe", user.getName());
        assertEquals("User ID should match", "USER001", user.getUserId());
    }

    @Test
    public void testBorrowedBooks() {
        // 2. assertTrue
        assertTrue("Initial borrowed books should be empty", 
                  user.getBorrowedBooks().isEmpty());
        
        user.borrowBook(book1);
        // 3. assertFalse
        assertFalse("Borrowed books should not be empty after borrowing", 
                   user.getBorrowedBooks().isEmpty());
    }

    @Test
    public void testBorrowAndReturn() {
        user.borrowBook(book1);
        // 4. assertSame
        assertSame("Borrowed book should be the same object", 
                  book1, user.getBorrowedBooks().get(0));
        
        user.returnBook(book1);
        // 5. assertEquals
        assertEquals("No books should remain after return", 
                    0, user.getBorrowedBooks().size());
    }

    @Test
    public void testMultipleBooks() {
        // 6. assertNotNull
        assertNotNull("Borrowed books list should not be null", 
                     user.getBorrowedBooks());
        
        user.borrowBook(book1);
        user.borrowBook(book2);
        // 7. assertEquals
        assertEquals("Should have 2 borrowed books", 
                    2, user.getBorrowedBooks().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testBorrowUnavailableBook() {
        Book unavailableBook = new Book("Unavailable", "Author", "ISBN789");
        unavailableBook.borrowBook(); // Make it unavailable
        user.borrowBook(unavailableBook); // Should throw IllegalStateException
    }
} 