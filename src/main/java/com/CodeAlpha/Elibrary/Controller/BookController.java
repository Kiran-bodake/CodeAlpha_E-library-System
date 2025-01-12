package com.CodeAlpha.Elibrary.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodeAlpha.Elibrary.Entity.Book;
import com.CodeAlpha.Elibrary.Service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/category/{category}")
    public List<Book> getBooksByCategory(@PathVariable String category) {
        return bookService.getBooksByCategory(category);
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<?> borrowBook(@PathVariable Long id) {
        Optional<Book> book = bookService.borrowBook(id);
        return book.isPresent() ? ResponseEntity.ok(book) : ResponseEntity.badRequest().body("Book not available");
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        Optional<Book> book = bookService.returnBook(id);
        return book.isPresent() ? ResponseEntity.ok(book) : ResponseEntity.badRequest().body("Book not found");
    }
}