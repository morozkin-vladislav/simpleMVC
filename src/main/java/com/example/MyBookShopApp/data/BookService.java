package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
      return  bookRepository.findAll();
    }

//    private String getAuthorByAuthorId(int author_id) {
//        List<Author> authors = jdbcTemplate.query("SELECT * from authors WHERE id = " + author_id, (ResultSet rs,
//                                                                                                           int rowNum) -> {
//            Author author = new Author();
//            author.setId(rs.getInt("id"));
//            author.setFirsName(rs.getString("first_name"));
//            author.setLastName(rs.getString("last_name"));
//            return author;
//        });
//        return authors.get(0).toString();
//    }


}
