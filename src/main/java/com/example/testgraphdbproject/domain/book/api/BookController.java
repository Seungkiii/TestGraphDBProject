package com.example.testgraphdbproject.domain.book.api;

import com.example.testgraphdbproject.domain.book.entity.Book;
import com.example.testgraphdbproject.domain.book.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}

