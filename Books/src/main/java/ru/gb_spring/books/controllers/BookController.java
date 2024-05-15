package ru.gb_spring.books.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ru.gb_spring.books.services.BookService;
import ru.gb_spring.books.models.Book;

/**
 * The type Book controller.
 */
@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    /**
     * Gets all book.
     *
     * @return the all book
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBook() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    /**
     * Gets book.
     *
     * @param id the id
     * @return the book
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book;
        try {
            book = bookService.getBookById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     * Add book response entity.
     *
     * @param book the book
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    /**
     * Update book response entity.
     *
     * @param id   the id
     * @param book the book
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updBook;
        try {
            updBook = bookService.updateBook(book, id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(updBook, HttpStatus.OK);
    }

    /**
     * Delete book response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook (@PathVariable Long id) {
        Book delBook;
        try {
            delBook = bookService.removeBook(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(delBook, HttpStatus.OK);
    }
}
