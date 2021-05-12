package com.example.MyBookShopApp.repositorys;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("from Tag")
    List<Tag> customFindAllTag();
}
