package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.data.BookReview;
import com.example.MyBookShopApp.repository.BookReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class ReviewsController {
    private final BookReviewRepository bookReviewRepository;

    @Autowired
    public ReviewsController(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    @PostMapping("/bookReview/{bookId}/{slug}")
    public String changeBookRating(@RequestParam("bookId") Integer bookId, @RequestParam("review") String review, @PathVariable("slug") String slug){
        BookReview bookReview = new BookReview();
        bookReview.setBookId(bookId);
        bookReview.setText(review);
        bookReviewRepository.save(bookReview);


        return ("redirect:/books/" + slug);
    }


}
