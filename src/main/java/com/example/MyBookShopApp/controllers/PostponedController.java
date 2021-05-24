package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Controller
@RequestMapping("/books")
public class PostponedController {

    @ModelAttribute(name = "bookShelv")
    public List<Book> bookShelv() {
        return new ArrayList<>();
    }

    private final BookRepository bookRepository;

    @Autowired
    public PostponedController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/postponed")
    public String handleShelvRequest(@CookieValue(value = "shelvContents", required = false) String shelvContents,
                                    Model model) {
        if (shelvContents == null || shelvContents.equals("")) {
            model.addAttribute("isShelvEmpty", true);
        } else {
            model.addAttribute("isShelvEmpty", false);
            shelvContents = shelvContents.startsWith("/") ? shelvContents.substring(1) : shelvContents;
            shelvContents = shelvContents.endsWith("/") ? shelvContents.substring(0, shelvContents.length() - 1) : shelvContents;
            String[] cookieSlugs = shelvContents.split("/");
            List<Book> booksFromCookieSlugs = bookRepository.findBooksBySlugIn(cookieSlugs);
            model.addAttribute("bookShelv", booksFromCookieSlugs);
        }

        return "postponed";
    }

    @PostMapping("/changeBookStatus/postpone/{slug}")
    public String handleChangeBookShelfStatus(@PathVariable("slug") String slug, @CookieValue(name = "shelvContents",
            required = false) String shelvContents, HttpServletResponse response, Model model) {

        if (shelvContents == null || shelvContents.equals("")) {
            Cookie cookie = new Cookie("shelvContents", slug);
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isShelvEmpty", false);
        } else if (!shelvContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(shelvContents).add(slug);
            Cookie cookie = new Cookie("shelvContents", stringJoiner.toString());
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isShelvEmpty", false);
        }

        return "redirect:/books/" + slug;
    }

    @PostMapping("/changeBookStatus/cart/remove/postpone/{slug}")
    public String handleRemoveBookFromCartRequest(@PathVariable("slug") String slug, @CookieValue(name =
            "shelvContents", required = false) String shelvContents, HttpServletResponse response, Model model){

        if (shelvContents != null && !shelvContents.equals("")){
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(shelvContents.split("/")));
            cookieBooks.remove(slug);
            Cookie cookie = new Cookie("shelvContents", String.join("/", cookieBooks));
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isShelvEmpty", false);
        }else {
            model.addAttribute("isShelvEmpty", true);
        }

        return "redirect:/books/postponed";
    }
}
