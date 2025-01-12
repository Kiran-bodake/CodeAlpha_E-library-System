package com.CodeAlpha.Elibrary.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CodeAlpha.Elibrary.Entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategory(String category);
}