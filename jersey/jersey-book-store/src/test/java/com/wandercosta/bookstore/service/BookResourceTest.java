package com.wandercosta.bookstore.service;

import static org.mockito.Mockito.verify;

import com.wandercosta.bookstore.database.BookDB;
import com.wandercosta.bookstore.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Class to test BookResource.
 *
 * @author Wander Costa (www.wandercosta.com)
 */
@RunWith(MockitoJUnitRunner.class)
public class BookResourceTest {

    private static final String DUMMY_ISBN = "abcdef";
    private static final Book DUMMY_BOOK = new Book();

    @Mock
    private BookDB db;

    @Test
    public void shouldList() {
        new BookResource(db).list();
        verify(db).findAll();
    }

    @Test
    public void shouldGet() {
        new BookResource(db).get(DUMMY_ISBN);
        verify(db).find(DUMMY_ISBN);
    }

    @Test
    public void shouldSave() {
        new BookResource(db).save(DUMMY_BOOK);
        verify(db).save(DUMMY_BOOK);
    }

    @Test
    public void shouldUpdate() {
        Book book = DUMMY_BOOK;
        book.setIsbn(DUMMY_ISBN);

        new BookResource(db).update(DUMMY_ISBN, DUMMY_BOOK);
        verify(db).update(book);
    }

    @Test
    public void shouldRemove() {
        new BookResource(db).remove(DUMMY_ISBN);
        verify(db).remove(DUMMY_ISBN);
    }

}
