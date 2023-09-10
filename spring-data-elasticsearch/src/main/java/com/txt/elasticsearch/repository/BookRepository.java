package com.txt.elasticsearch.repository;

import com.txt.elasticsearch.entities.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {

    List<Book> findByAuthorName(String authorName);

    Optional<Book> findByIsbn(String isbn);
}
