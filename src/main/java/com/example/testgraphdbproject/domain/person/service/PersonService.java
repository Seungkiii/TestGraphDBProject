package com.example.testgraphdbproject.domain.person.service;

import com.example.testgraphdbproject.domain.book.entity.Book;
import com.example.testgraphdbproject.domain.book.repository.BookRepository;
import com.example.testgraphdbproject.domain.movie.entity.Movie;
import com.example.testgraphdbproject.domain.movie.repository.MovieRepository;
import com.example.testgraphdbproject.domain.person.dto.request.AddPersonRequestDto;
import com.example.testgraphdbproject.domain.person.dto.request.AddReadBookRequestDto;
import com.example.testgraphdbproject.domain.person.dto.response.BookResponseDto;
import com.example.testgraphdbproject.domain.person.dto.response.PersonMovieBookResponseDto;
import com.example.testgraphdbproject.domain.person.dto.response.PersonResponseDto;
import com.example.testgraphdbproject.domain.person.entity.Person;
import com.example.testgraphdbproject.domain.person.relationship.Role;
import com.example.testgraphdbproject.domain.person.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final BookRepository bookRepository;
    private final MovieRepository movieRepository;

    public PersonService(PersonRepository personRepository, BookRepository bookRepository, MovieRepository movieRepository) {
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
        this.movieRepository = movieRepository;
    }

    // 1. 사람 추가
    public PersonResponseDto addPerson(AddPersonRequestDto request) {
        // 책 조회 또는 생성
        List<Book> books = new ArrayList<>();
        for (String bookTitle : request.getBooksRead()) {
            books.add(bookRepository.findById(bookTitle).orElseGet(() -> {
                Book book = new Book();
                book.setTitle(bookTitle);
                return bookRepository.save(book);
            }));
        }

        // 영화 조회 또는 생성
        Movie movie = movieRepository.findById(request.getMovieTitle()).orElseGet(() -> {
            Movie newMovie = new Movie();
            newMovie.setTitle(request.getMovieTitle());
            return movieRepository.save(newMovie);
        });

        // 사람 생성
        Person person = new Person(request.getName(), request.getAge());
        // 책을 읽었던 관계 설정
        person.getBooksRead().addAll(books);

        Role role = new Role(person);
        // 영화에 참여 했다는 관계 설정
        movie.getActors().add(role);

        personRepository.save(person);
        movieRepository.save(movie);

        // 응답 변환
        PersonResponseDto response = new PersonResponseDto();
        response.setName(person.getName());
        response.setAge(person.getAge());
        response.setBookTitle(books.get(0).getTitle()); // 첫 번째 책만 반환
        response.setMovieTitle(movie.getTitle());

        return response;
    }


    public List<PersonMovieBookResponseDto> getPersonsWhoActedInMovieAndReadBook(String movieTitle) {
        return personRepository.findPersonsWhoActedInMovieAndReadBook(movieTitle);
    }

    public void addBookToPerson(String personName, AddReadBookRequestDto bookRequestDto) {
        personRepository.addBookToPerson(personName, bookRequestDto.getTitle(), bookRequestDto.getAuthors());
    }

    public List<BookResponseDto> getBooksReadByPerson(String personName) {
        return personRepository.getBooksReadByPerson(personName).stream()
                .map(book -> {
                    String title = book.getTitle();
                    List<String> authorsList = book.getAuthors();
                    return new BookResponseDto(title, authorsList);
                })
                .collect(Collectors.toList());
    }


}

