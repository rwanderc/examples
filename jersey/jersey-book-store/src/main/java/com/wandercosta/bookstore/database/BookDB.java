package com.wandercosta.bookstore.database;

import com.wandercosta.bookstore.entity.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton class to represent the in-memory Book database.
 *
 * @author Wander Costa (www.wandercosta.com)
 */
public class BookDB {

    private static final String NOT_FOUND = "Book not found.";
    private static final String ALREADY_EXISTS = "ISBN already registered.";

    private static BookDB instance;

    public static BookDB getInstance() {
        if (instance == null) {
            instance = new BookDB();
        }
        return instance;
    }

    private final Map<String, Book> books = new HashMap<>();

    /**
     * Returns all books.
     *
     * @return all the books.
     */
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    /**
     * Returns a single book.
     *
     * @param isbn The book's ISBN.
     * @return a single book.
     */
    public Book find(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new NullPointerException(NOT_FOUND);
        }
        return book;
    }

    /**
     * Saves a new book.
     *
     * @param book The new book.
     */
    public void save(Book book) {
        if (books.get(book.getIsbn()) != null) {
            throw new IllegalArgumentException(ALREADY_EXISTS);
        }
        books.put(book.getIsbn(), book);
    }

    /**
     * Updates an existing book.
     *
     * @param book The book to be updated.
     */
    public void update(Book book) {
        find(book.getIsbn());
        books.put(book.getIsbn(), book);
    }

    /**
     * Removes an existing book.
     *
     * @param isbn The book's ISBN.
     */
    public void remove(String isbn) {
        find(isbn);
        books.remove(isbn);
    }

}
