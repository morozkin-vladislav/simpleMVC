package org.example.app.services;

import java.util.List;
import java.util.Map;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

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

    void filterBookByAuthor(String rSelectAuthor);

    void filterBookBySize(Integer rSelectSize);

    void filterBookByTitle(String rSelectTitle);

    void filterBookByAuthorTitle(String rSelectAuthor, String rSelectTitle);

    void filterBookByAuthorSize(String rSelectAuthor, Integer rSelectSize);

    void filterBookByTitleSize(String rSelectTitle, Integer rSelectSize);

    void filterBookByAuthorTitleSize(String rSelectAuthor, String rSelectTitle, Integer rSelectSize);
}

