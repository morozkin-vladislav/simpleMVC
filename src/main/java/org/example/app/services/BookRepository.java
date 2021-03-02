package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private List<Book> repo = new ArrayList<>();
    private ApplicationContext context;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String folderPath = "D:/Projects/apache-tomcat-9.0.41/apache-tomcat-9.0.41/external_uploads";


    @Autowired
    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> listOfFiles() {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> listOfFilesNames = new ArrayList<>();
        for (File file : listOfFiles) {
            listOfFilesNames.add(file.getName());
            logger.info("File name: " + file.getName());
        }
        return new ArrayList<>(listOfFilesNames);
    }

    @Override
    public List<Book> retreiveAll() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });
        return new ArrayList<>(books);
    }


    @Override
    public void store(Book book) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", book.getAuthor());
        parameterSource.addValue("title", book.getTitle());
        parameterSource.addValue("size", book.getSize());
        jdbcTemplate.update("INSERT INTO books(author,title,size) VALUES(:author, :title, :size)", parameterSource);

        logger.info("store new book: " + book);

    }


    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", bookIdToRemove);
        jdbcTemplate.update("DELETE FROM books WHERE id = :id", parameterSource);

        logger.info("remove book completed");
        return true;
    }


    @Override
    public List<String> getUniqueAuthors() {
        ArrayList<String> tempAuthors = new ArrayList<>();
        for (Book book : retreiveAll()) {
            tempAuthors.add(book.getAuthor());
        }

        return tempAuthors.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getUniqueTitle() {
        ArrayList<String> tempTitle = new ArrayList<>();
        for (Book book : retreiveAll()) {
            tempTitle.add(book.getTitle());
        }
        return tempTitle.stream().distinct().collect(Collectors.toList());
    }


    @Override
    public List<Integer> getUniqueSize() {
        ArrayList<Integer> tempSize = new ArrayList<>();
        for (Book book : retreiveAll()) {
            tempSize.add(book.getSize());
        }

        return tempSize.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void removeBookByAuthor(String rSelectAuthor) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", rSelectAuthor);
        jdbcTemplate.update("DELETE FROM books WHERE author = :author", parameterSource);

        logger.info("remove book completed");

    }

    @Override
    public void removeBookByAuthorTitle(String rSelectAuthor, String rSelectTitle) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", rSelectAuthor);
        parameterSource.addValue("title", rSelectTitle);
        jdbcTemplate.update("DELETE FROM books WHERE author = :author and title = :title", parameterSource);

        logger.info("remove book completed");
    }

    @Override
    public void removeBookByAuthorTitleSize(String rSelectAuthor, String rSelectTitle, Integer rSelectSize) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", rSelectAuthor);
        parameterSource.addValue("title", rSelectTitle);
        parameterSource.addValue("size", rSelectSize);
        jdbcTemplate.update("DELETE FROM books WHERE author = :author and title = :title and size = :size", parameterSource);

        logger.info("remove book completed");
    }

    @Override
    public void removeBookByAuthorSize(String rSelectAuthor, Integer rSelectSize) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", rSelectAuthor);
        parameterSource.addValue("size", rSelectSize);
        jdbcTemplate.update("DELETE FROM books WHERE author = :author and size = :size", parameterSource);

        logger.info("remove book completed");
    }

    @Override
    public void removeBookBySize(Integer rSelectSize) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("size", rSelectSize);
        jdbcTemplate.update("DELETE FROM books WHERE size = :size", parameterSource);

        logger.info("remove book completed");
    }

    @Override
    public void removeBookByTitleSize(String rSelectTitle, Integer rSelectSize) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("title", rSelectTitle);
        parameterSource.addValue("size", rSelectSize);
        jdbcTemplate.update("DELETE FROM books WHERE title = :title and size = :size", parameterSource);

        logger.info("remove book completed");
    }

    @Override
    public void removeBookByTitle(String rSelectTitle) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("title", rSelectTitle);
        jdbcTemplate.update("DELETE FROM books WHERE title = :title", parameterSource);

        logger.info("remove book completed");
    }

    @Override
    public void resetFilter() {
        repo.clear();
        repo.addAll(retreiveAll());
    }

    @Override
    public List<Book> filterBookByAuthor(String rSelectAuthor) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", rSelectAuthor);
        List<Book> books = jdbcTemplate.query("select * from BOOKS where author = :author", parameterSource, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });

        return new ArrayList<>(books);

    }

    @Override
    public List<Book> filterBookByAuthorTitle(String rSelectAuthor, String rSelectTitle) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", rSelectAuthor);
        parameterSource.addValue("title", rSelectTitle);
        List<Book> books = jdbcTemplate.query("select * from BOOKS where author = :author and title = :title", parameterSource, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });

        return new ArrayList<>(books);
    }

    @Override
    public List<Book> filterBookByAuthorTitleSize(String rSelectAuthor, String rSelectTitle, Integer rSelectSize) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", rSelectAuthor);
        parameterSource.addValue("title", rSelectTitle);
        parameterSource.addValue("size", rSelectSize);
        List<Book> books = jdbcTemplate.query("select * from BOOKS where author = :author and title = :title and size = :size", parameterSource, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });

        return new ArrayList<>(books);
    }

    @Override
    public List<Book> filterBookByAuthorSize(String rSelectAuthor, Integer rSelectSize) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", rSelectAuthor);
        parameterSource.addValue("size", rSelectSize);
        List<Book> books = jdbcTemplate.query("select * from BOOKS where author = :author and size = :size", parameterSource, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });

        return new ArrayList<>(books);
    }

    @Override
    public List<Book> filterBookBySize(Integer rSelectSize) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("size", rSelectSize);
        List<Book> books = jdbcTemplate.query("select * from BOOKS where size = :size", parameterSource, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });

        return new ArrayList<>(books);
    }

    @Override
    public List<Book> filterBookByTitleSize(String rSelectTitle, Integer rSelectSize) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("title", rSelectTitle);
        parameterSource.addValue("size", rSelectSize);
        List<Book> books = jdbcTemplate.query("select * from BOOKS where title = :title and size = :size", parameterSource, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });

        return new ArrayList<>(books);
    }

    @Override
    public List<Book> filterBookByTitle(String rSelectTitle) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("title", rSelectTitle);
        List<Book> books = jdbcTemplate.query("select * from BOOKS where title = :title", parameterSource, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });

        return new ArrayList<>(books);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;

    }

    private void defaultInit() {
        logger.info("default INIT in book repo bean");
    }

    private void defaultDestroy() {
        logger.info("default DESTROY in book repo bean");
    }
}

