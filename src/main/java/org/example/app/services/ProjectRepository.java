package org.example.app.services;

import org.example.web.dto.Book;

import java.util.List;
import java.util.Map;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    List<String> listOfFiles();
    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);


    List<String> getUniqueAuthors();

    List<String> getUniqueTitle();

    List<Integer> getUniqueSize();

    void resetFilter();

    void removeBookByAuthor(String rSelectAuthor);

    void removeBookBySize(Integer rSelectSize);

    void removeBookByTitle(String rSelectTitle);

    void removeBookByAuthorTitle(String rSelectAuthor, String rSelectTitle);

    void removeBookByAuthorSize(String rSelectAuthor, Integer rSelectSize);

    void removeBookByTitleSize(String rSelectTitle, Integer rSelectSize);

    void removeBookByAuthorTitleSize(String rSelectAuthor, String rSelectTitle, Integer rSelectSize);

     List<Book> filterBookByAuthor(String rSelectAuthor);

    List<Book> filterBookBySize(Integer rSelectSize);

    List<Book> filterBookByTitle(String rSelectTitle);

    List<Book> filterBookByAuthorTitle(String rSelectAuthor, String rSelectTitle);

    List<Book> filterBookByAuthorSize(String rSelectAuthor, Integer rSelectSize);

    List<Book> filterBookByTitleSize(String rSelectTitle, Integer rSelectSize);

    List<Book> filterBookByAuthorTitleSize(String rSelectAuthor, String rSelectTitle, Integer rSelectSize);
}

