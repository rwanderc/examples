package com.wandercosta.bookstore.service;

import com.wandercosta.bookstore.database.BookDB;
import com.wandercosta.bookstore.entity.Book;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Class to represent the service.
 *
 * @author Wander Costa (wwww.wandercosta.com)
 */
@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private final BookDB bookDB;

    public BookResource() {
        this(BookDB.getInstance());
    }

    public BookResource(BookDB db) {
        this.bookDB = db;
    }

    /**
     * Returns all the books.
     *
     * @return a {@link Response} with all the books.
     */
    @GET
    public List<Book> list() {
        return bookDB.findAll();
    }

    /**
     * Returns a single book.
     *
     * @param isbn The book's ISBN.
     * @return a {@link Response} with a single book, or 404 if not found.
     */
    @GET
    @Path("{isbn}")
    public Book get(@PathParam(value = "isbn") String isbn) {
        return bookDB.find(isbn);
    }

    /**
     * Saves a new book.
     *
     * @param book The new book.
     * @return a {@link Response} with 201 if created; 400, otherwise.
     */
    @POST
    public Response save(Book book) {
        bookDB.save(book);
        return Response.status(201)
                .entity(Collections.singletonMap("message", "Book saved."))
                .build();
    }

    /**
     * Updates a book.
     *
     * @param isbn The book's ISBN.
     * @param book The book.
     * @return a {@link Response} with 200 if updated; 404, otherwise.
     */
    @PUT
    @Path("{isbn}")
    public Response update(@PathParam(value = "isbn") String isbn, Book book) {
        book.setIsbn(isbn);
        bookDB.update(book);
        return Response.ok(Collections.singletonMap("message", "Book updated.")).build();
    }

    /**
     * Removes a book.
     *
     * @param isbn The book's ISBN.
     * @return a {@link Response} with 204.
     */
    @DELETE
    @Path("{isbn}")
    public Response remove(@PathParam(value = "isbn") String isbn) {
        bookDB.remove(isbn);
        return Response.noContent()
                .entity(Collections.singletonMap("message", "Book removed."))
                .build();
    }

}
