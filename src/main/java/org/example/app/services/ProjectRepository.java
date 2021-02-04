package org.example.app.services;

import java.util.List;
import java.util.Map;

public interface ProjectRepository<T> {
    List<T> retreivell();

    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);

    boolean removeForAuthor(String valueToRemove);

    boolean removeToSize(String valueToRemove);

    boolean removeToTitle(String valueToRemove);

    List<String> getRemoveList();

    void resetF();

    boolean filterForAuthor(String valueToFilter);

    boolean filterToSize(String valueToFilter);

    boolean filterToTitle(String valueToFilter);
}
