package com.CodeAlpha.Elibrary.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodeAlpha.Elibrary.Entity.Book;
import com.CodeAlpha.Elibrary.Repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    public Optional<Book> borrowBook(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent() && book.get().isAvailable()) {
            book.get().setAvailable(false);
            bookRepository.save(book.get());
        }
        return book;
    }

    public Optional<Book> returnBook(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            book.get().setAvailable(true);
            bookRepository.save(book.get());
        }
        return book;
    }
}
