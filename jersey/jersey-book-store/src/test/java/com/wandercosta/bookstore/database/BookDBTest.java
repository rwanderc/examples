package com.wandercosta.bookstore.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.wandercosta.bookstore.entity.Book;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Class to test BookDB implementation.
 *
 * @author Wander Costa (www.wandercosta.com)
 */
public class BookDBTest {

    private static final String NOT_FOUND = "Book not found.";
    private static final String ALREADY_EXISTS = "ISBN already registered.";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private final BookDB db = BookDB.getInstance();

    @Before
    public void before() {
        db.findAll().stream().map(Book::getIsbn).forEach(db::remove);
    }

    @Test
    public void shouldGetInstance() {
        assertNotNull(db);
    }

    @Test
    public void shouldSaveAndFindAll() {
        assertEquals(0, db.findAll().size());

        db.save(randomBook());
        assertEquals(1, db.findAll().size());

        db.save(randomBook());
        assertEquals(2, db.findAll().size());
    }

    @Test
    public void shouldSaveAndFind() {
        Book book = randomBook();
        db.save(book);
        assertEquals(book, db.find(book.getIsbn()));
    }

    @Test
    public void shouldFailToFindNonExistentIsbn() {
        exception.expect(NullPointerException.class);
        exception.expectMessage(NOT_FOUND);

        db.find(RandomStringUtils.randomAlphanumeric(8));
    }

    @Test
    public void shouldFailToSaveWithExistentIsbn() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(ALREADY_EXISTS);

        Book book = randomBook();
        db.save(book);
        db.save(book);
    }

    @Test
    public void shouldSaveAndUpdate() {
        String isbn = RandomStringUtils.randomAlphanumeric(8);
        Book book1 = randomBook();
        book1.setIsbn(isbn);
        Book book2 = randomBook();
        book2.setIsbn(isbn);

        assertNotEquals(book1, book2);
        assertEquals(book1.getIsbn(), book2.getIsbn());
        assertNotEquals(book1.getAuthor(), book2.getAuthor());
        assertNotEquals(book1.getPrice(), book2.getPrice());
        assertNotEquals(book1.getTitle(), book2.getTitle());

        db.save(book1);
        assertEquals(book1, db.find(isbn));

        db.update(book2);
        assertEquals(book2, db.find(isbn));
    }

    @Test
    public void shouldFailToUpdateNonExistentBook() {
        exception.expect(NullPointerException.class);
        exception.expectMessage(NOT_FOUND);
        db.update(randomBook());
    }

    @Test
    public void shouldSaveAndRemove() {
        Book book = randomBook();

        assertEquals(0, db.findAll().size());

        db.save(book);
        assertEquals(1, db.findAll().size());

        db.remove(book.getIsbn());
        assertEquals(0, db.findAll().size());
    }

    @Test
    public void shouldFailToRemoveNonExistentBook() {
        exception.expect(NullPointerException.class);
        exception.expectMessage(NOT_FOUND);
        db.remove(randomBook().getIsbn());
    }

    private Book randomBook() {
        Book book = new Book();
        book.setIsbn(RandomStringUtils.randomAlphanumeric(8));
        book.setAuthor(RandomStringUtils.randomAlphanumeric(8));
        book.setTitle(RandomStringUtils.randomAlphanumeric(8));
        book.setPrice(RandomUtils.nextFloat() * 100);
        return book;
    }

}
