package com.example.testgraphdbproject.domain.book.service;

import com.example.testgraphdbproject.domain.book.entity.Book;
import com.example.testgraphdbproject.domain.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}

