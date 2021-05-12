package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.Tag;
import com.example.MyBookShopApp.repositorys.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTags(){
        return tagRepository.customFindAllTag();
    }
}
