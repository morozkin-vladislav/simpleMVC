package org.example.app.services;

import java.util.List;
import java.util.Map;

public interface ProjectRepository<T> {
    List<T> retreivell();

    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);





    List<String> getUnicAuthors();

    List<String> getUnicTitle();

    List<Integer> getUnicSize();

    void removeMethod1(String rSelectAuthor);

    void removeMethod2(String rSelectAuthor, String rSelectTitle);

    void removeMethod3(String rSelectAuthor, String rSelectTitle, Integer rSelectSize);

    void removeMethod4(String rSelectAuthor, Integer rSelectSize);

    void removeMethod5(Integer rSelectSize);

    void removeMethod6(String rSelectTitle, Integer rSelectSize);

    void removeMethod7(String rSelectTitle);

    void resetFilter();

    void filterMethod1(String rSelectAuthor);

    void filterMethod2(String rSelectAuthor, String rSelectTitle);

    void filterMethod3(String rSelectAuthor, String rSelectTitle, Integer rSelectSize);

    void filterMethod4(String rSelectAuthor, Integer rSelectSize);

    void filterMethod5(Integer rSelectSize);

    void filterMethod6(String rSelectTitle, Integer rSelectSize);

    void filterMethod7(String rSelectTitle);


}

