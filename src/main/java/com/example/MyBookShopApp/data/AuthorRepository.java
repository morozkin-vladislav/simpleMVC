package com.example.MyBookShopApp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository  extends JpaRepository<Author,Integer> {
    List<Author> findAuthorByFirstName(String name);

    @Query("from Author")
    List<Book> customFindAllAuthors();
}
