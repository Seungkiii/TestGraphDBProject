package com.example.testgraphdbproject.domain.person.repository;

import com.example.testgraphdbproject.domain.person.dto.response.BookResponseDto;
import com.example.testgraphdbproject.domain.person.dto.response.PersonMovieBookResponseDto;
import com.example.testgraphdbproject.domain.person.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person, String> {

    @Query("match(p:Person)-[:ACTED_IN]->(m:Movie) "+"where m contains $title return p")
    List<Person> findPersonByRelationshipAndTitle(@Param("title") String title);

    // 특정 영화에 출연하고 그 영화의 원작인 책을 읽은 사람들을 조회하는 Cypher 쿼리
    @Query("MATCH (p:Person)-[:ACTED_IN]->(m:Movie)-[:ORIGINAL]->(b:Book)<-[:HAS_READ]-(p) " +
            "WHERE m.title = $movieTitle " +
            "RETURN p.name AS personName, m.title AS movieTitle, b.title AS bookTitle")
    List<PersonMovieBookResponseDto> findPersonsWhoActedInMovieAndReadBook(String movieTitle);

    // 사용자가 읽은 책 추가
    @Query("MATCH (p:Person {name: $personName}) " +
            "MERGE (b:Book {title: $bookTitle}) " +
            "ON CREATE SET b.authors = $authors " +
            "MERGE (p)-[:HAS_READ]->(b)")
    void addBookToPerson(String personName, String bookTitle, List<String> authors);

    // 사용자가 읽은 책 조회
    @Query("MATCH (p:Person {name: $personName})-[:HAS_READ]->(b:Book) " +
            "RETURN b.title AS title, b.authors AS authors")
    List<BookResponseDto> getBooksReadByPerson(String personName);

}

