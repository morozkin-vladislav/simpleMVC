package com.example.MyBookShopApp.repositorys;
import com.example.MyBookShopApp.data.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {


    @Query("from Genre")
    List<Genre> customFindAllGenre();


}
