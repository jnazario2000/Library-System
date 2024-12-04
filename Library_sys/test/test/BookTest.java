package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import project.Book;

public class BookTest {
    private Book book;

    @Before
    public void setUp() {
        book = new Book("Java Programming", "Author A", "ISBN123");
    }

    @Test
    public void testBookCreation() {
        // 1. assertEquals
        assertEquals("Title should match", "Java Programming", book.getTitle());
        assertEquals("Author should match", "Author A", book.getAuthor());
        assertEquals("ISBN should match", "ISBN123", book.getIsbn());
    }

    @Test
    public void testAvailability() {
        // 2. assertTrue
        assertTrue("New book should be available", book.isAvailable());
        
        book.borrowBook();
        // 3. assertFalse
        assertFalse("Book should not be available after borrowing", book.isAvailable());
    }

    @Test
    public void testBookEquality() {
        Book sameBook = new Book("Java Programming", "Author A", "ISBN123");
        Book differentBook = new Book("Python Programming", "Author B", "ISBN456");
        
        // 4. assertNotSame
        assertNotSame("Different book objects with same data", book, sameBook);
        
        // 5. assertNotNull
        assertNotNull("Book object should not be null", book);
    }

    @Test
    public void testBookReturn() {
        book.borrowBook();
        book.returnBook();
        // 6. assertTrue
        assertTrue("Book should be available after return", book.isAvailable());
    }

    @Test(expected = IllegalStateException.class)
    public void testBorrowUnavailableBook() {
        book.borrowBook(); // First borrow
        book.borrowBook(); // Should throw IllegalStateException
    }
} 