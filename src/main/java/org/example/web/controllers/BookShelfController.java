package org.example.web.controllers;
import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "books")
public class BookShelfController {
    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;
    private String nullString = "";
    private static final String REMOVE_COMMAND = "remove";
    private static final String FILTER_COMMAND = "filter";
    private static final String RESET_COMMAND = "reset";
    private static final String DEFAULT_VALUE = "defaultValue";

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new org.example.web.dto.Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        model.addAttribute("uniqueAuthors", bookService.getAuthors());
        model.addAttribute("uniqueTitle", bookService.getTitle());
        model.addAttribute("uniqueSize", bookService.getSize());
        return "book_shelf";

    }


    @PostMapping("/save")
    public String saveBook(Book book) {
        bookService.saveBook(book);
        logger.info("current repository size: " + bookService.getAllBooks().size());
        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") Integer bookIdToRemove) {
        if (bookService.removeBookById(bookIdToRemove)) {
            return "redirect:/books/shelf";
        } else {
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/filterAndRemove")
    public String selectRemoveBook(@RequestParam(value = "RSelectAuthor", required = false, defaultValue = DEFAULT_VALUE) String RSelectAuthor,
                                   @RequestParam(value = "RSelectTitle", required = false, defaultValue = DEFAULT_VALUE) String RSelectTitle,
                                   @RequestParam(value = "RSelectSize", required = false) Integer RSelectSize,
                                   @RequestParam(value = "button") String button) {
        logger.info("RSelectAuthor: " + RSelectAuthor + " " + "RSelectTitle: " + RSelectTitle + " " + "RSelectSize: " + RSelectSize + " " + "button: " + button);
        if (button.equals(RESET_COMMAND)) {
            logger.info("METHOD RESET FILTER: controller");
            bookService.resetFilter();
        } else if (RSelectAuthor.equals(DEFAULT_VALUE) & RSelectTitle.equals(DEFAULT_VALUE) & RSelectSize == null) {
            logger.info("NO METHODS, ALL = NULL");
            return "redirect:/books/shelf";
        } else if (button.equals(REMOVE_COMMAND)) {
            logger.info("METHOD REMOVE: controller");
            bookService.removeSelectedBook(RSelectAuthor, RSelectTitle, RSelectSize);
        } else if (button.equals(FILTER_COMMAND)) {
            logger.info("METHOD FILTER: controller");
            bookService.filterBooks(RSelectAuthor, RSelectTitle, RSelectSize);
        }
        return "redirect:/books/shelf";
    }

}



