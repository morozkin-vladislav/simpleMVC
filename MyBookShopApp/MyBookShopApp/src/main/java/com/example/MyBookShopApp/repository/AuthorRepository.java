package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository  extends JpaRepository<Author,Integer> {
    List<Author> findAuthorByFirstName(String name);

    @Query("from Author")
    List<Book> customFindAllAuthors();
}
