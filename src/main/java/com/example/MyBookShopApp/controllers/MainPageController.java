package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.*;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.GenreService;
import com.example.MyBookShopApp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    private final BookService bookService;
    private final TagService tagService;
    private final GenreService genreService;

    @Autowired
    public MainPageController(BookService bookService, TagService tagService, GenreService genreService) {
        this.bookService = bookService;
        this.tagService = tagService;
        this.genreService = genreService;
    }


    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("recentBooks")
    public List<Book> recentBooks() {
        return bookService.getRecentBooks(0, 20);
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks() {
        return bookService.getPopularBooks(0, 20);
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("tags")
    public List<Tag> getTags() {
        return tagService.getAllTags();
    }

    @ModelAttribute("genres")
    public List<Genre> getGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/genres")
    public String genre() {
        return "/genres/index";
    }

    @GetMapping("/genres/slug")
    public String genreSlug(@RequestParam("genreId") Integer genreId, Model model) {
        model.addAttribute("bookByGenre", bookService.getBooksByGenreId(genreId, 0, 20));
        return "/genres/slug";
    }

    @GetMapping("/tags/slug")
    public String tagsSlug(@RequestParam("tagId") Integer tagId, Model model) {
        model.addAttribute("bookByGenre", bookService.getBooksByTagId(tagId, 0, 20));
        return "/genres/slug";
    }



    @GetMapping("/recentBooks")
    public String recent() {
        return "/books/recent";
    }

    @GetMapping("/popularBooks")
    public String popular() {
        return "/books/popular";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getBooksPage(@RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksPageDto getPopularBooksPage(@RequestParam("offset") Integer offset,
                                            @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPopularBooks(offset, limit));
    }



    @GetMapping("/recent")
    @ResponseBody
    public BooksPageDto getRecentBooksPage(@RequestParam("from") Date date1, @RequestParam("to") Date date2, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getBooksByDate(date1, date2, offset, limit));
    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                   Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent());
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {
        return new BooksPageDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent());
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDto getRecentBooksPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getRecentBooks(offset, limit));
    }


}
