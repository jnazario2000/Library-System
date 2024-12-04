package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import project.Book;
import project.Library;
import project.User;
import java.util.List;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private User user1;
    private User user2;

    @Before
    public void setUp() {
        // Initialize objects before each test
        library = new Library();
        book1 = new Book("Java Programming", "Author A", "ISBN123");
        book2 = new Book("Data Structures", "Author B", "ISBN456");
        user1 = new User("Steve Harvey", "User001");
        user2 = new User("Jim Carrey", "User002");
        
        // Add books and users to library
        library.addBook(book1);
        library.addBook(book2);
        library.registerUser(user1);
        library.registerUser(user2);
    }

    @Test
    public void testAddBook() {
        // 1. assertEquals
        assertEquals("Should have 2 books initially", 2, library.getTotalNumberOfBooks());
    }

    @Test
    public void testBookAvailability() {
        // 2. assertTrue
        assertTrue("New book should be available", book1.isAvailable());
        
        // 3. assertFalse
        library.borrowBook(user1.getUserId(), book1.getIsbn());
        assertFalse("Book should not be available after borrowing", book1.isAvailable());
    }

    @Test
    public void testNullChecks() {
        // 4. assertNull
        assertNull("Should return null for non-existent ISBN", 
                  library.findBookByIsbn("nonexistent"));
        
        // 5. assertNotNull
        assertNotNull("Library instance should not be null", library);
    }

    @Test
    public void testBookReferences() {
        // 6. assertSame
        Book foundBook = library.findBookByIsbn("ISBN123");
        assertSame("Should be the same book object", book1, foundBook);
        
        // 7. assertNotSame
        Book differentBook = new Book("Java Programming", "Author A", "ISBN123");
        assertNotSame("Should be different book objects", book1, differentBook);
    }

    @Test
    public void testBorrowAndReturn() {
        library.borrowBook(user1.getUserId(), book1.getIsbn());
        assertEquals("User should have 1 borrowed book", 
                    1, user1.getBorrowedBooks().size());

        library.returnBook(user1.getUserId(), book1.getIsbn());
        assertEquals("User should have 0 borrowed books after return", 
                    0, user1.getBorrowedBooks().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testBorrowUnavailableBook() {
        // First borrowing
        library.borrowBook(user1.getUserId(), book1.getIsbn());
        // Second borrowing should throw IllegalStateException
        library.borrowBook(user2.getUserId(), book1.getIsbn());
    }
}