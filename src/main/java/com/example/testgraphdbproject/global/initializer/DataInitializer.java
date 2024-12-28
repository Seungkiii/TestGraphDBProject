package com.example.testgraphdbproject.global.initializer;

import com.example.testgraphdbproject.domain.book.entity.Book;
import com.example.testgraphdbproject.domain.book.repository.BookRepository;
import com.example.testgraphdbproject.domain.movie.entity.Movie;
import com.example.testgraphdbproject.domain.movie.repository.MovieRepository;
import com.example.testgraphdbproject.domain.person.entity.Person;
import com.example.testgraphdbproject.domain.person.relationship.Role;
import com.example.testgraphdbproject.domain.person.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final MovieRepository movieRepository;
    private final BookRepository bookRepository;

    public DataInitializer(PersonRepository personRepository, MovieRepository movieRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.movieRepository = movieRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 데이터 초기화 전에 기존 데이터 삭제
        personRepository.deleteAll();
        movieRepository.deleteAll();
        bookRepository.deleteAll();

        // Person 노드 생성
        Person sally = personRepository.save(new Person("Sally", 21));
        Person john = personRepository.save(new Person("John", 27));

        // Book 노드 생성
        Book book = new Book();
        book.setTitle("The Matrix Origins");
        book.setAuthors(Arrays.asList("Author1", "Author2"));

        // Role 관계 생성
        Role role1 = new Role(sally);
        Role role2 = new Role(john);

        // 관계를 Movie에 연결
        List<Role> roles = new ArrayList<>();
        roles.add(role1);

        List<Role> roles2 = new ArrayList<>();
        roles2.add(role2);

        Movie movie = new Movie(
                "matrix",
                "Welcome to Real World",
                roles,
                roles2,
                book,
                1997,
                0L
        );

        // Movie 노드와 Book 노드를 관계로 연결
        movie.setOriginalBook(book); // Movie와 Book의 ORIGINAL 관계 설정

        // Sally가 책을 읽었다는 관계 추가
        sally.getBooksRead().add(book);

        // 데이터 저장
        personRepository.save(sally);
        personRepository.save(john);
        movieRepository.save(movie);

        System.out.println("Initial data setup completed!");
    }
}
