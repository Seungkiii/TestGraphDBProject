package com.example.testgraphdbproject.domain.person.api;

import com.example.testgraphdbproject.domain.person.dto.request.AddPersonRequestDto;
import com.example.testgraphdbproject.domain.person.dto.request.AddReadBookRequestDto;
import com.example.testgraphdbproject.domain.person.dto.response.BookResponseDto;
import com.example.testgraphdbproject.domain.person.dto.response.PersonMovieBookResponseDto;
import com.example.testgraphdbproject.domain.person.dto.response.PersonResponseDto;
import com.example.testgraphdbproject.domain.person.entity.Person;
import com.example.testgraphdbproject.domain.person.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // 1. 사람 추가 API
    @PostMapping
    public PersonResponseDto addPerson(@RequestBody AddPersonRequestDto request) {
        return personService.addPerson(request);
    }

    // 특정 영화에 출연하고 그 영화의 원작을 읽은 사람들을 조회하는 API
    @GetMapping("/who-read-and-acted")
    public ResponseEntity<List<PersonMovieBookResponseDto>> getPersonsWhoReadAndActedInMovie(
            @RequestParam String movieTitle) {
        List<PersonMovieBookResponseDto> response = personService.getPersonsWhoActedInMovieAndReadBook(movieTitle);
        return ResponseEntity.ok(response);
    }

    // 사용자가 읽은 책 추가 API
    @PostMapping("/{personName}/books")
    public ResponseEntity<String> addBookToPerson(
            @PathVariable String personName,
            @RequestBody AddReadBookRequestDto bookRequestDto) {
        personService.addBookToPerson(personName, bookRequestDto);
        return ResponseEntity.ok("Book added to person successfully");
    }

    // 사용자가 읽은 책 조회 API
    @GetMapping("/{personName}/books")
    public ResponseEntity<List<BookResponseDto>> getBooksReadByPerson(@PathVariable String personName) {
        List<BookResponseDto> books = personService.getBooksReadByPerson(personName);
        return ResponseEntity.ok(books);
    }
}

