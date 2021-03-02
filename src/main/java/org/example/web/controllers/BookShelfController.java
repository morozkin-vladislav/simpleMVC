package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.example.web.dto.BookIdToRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;


@Controller
@RequestMapping(value = "books")
@Scope("singleton")
public class BookShelfController {
    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;
    private static final String REMOVE_COMMAND = "remove";
    private static final String FILTER_COMMAND = "filter";
    private static final String RESET_COMMAND = "reset";
    private static final String DEFAULT_VALUE = "defaultValue";
    private static final String folderPath = "D:/Projects/apache-tomcat-9.0.41/apache-tomcat-9.0.41/external_uploads";

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info(this.toString());
        model.addAttribute("book", new org.example.web.dto.Book());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("bookList", bookService.getAllBooks());
        model.addAttribute("uniqueAuthors", bookService.getAuthors());
        model.addAttribute("uniqueTitle", bookService.getTitle());
        model.addAttribute("uniqueSize", bookService.getSize());
        model.addAttribute("files", bookService.listOfFiles());

        return "book_shelf";


    }


    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new org.example.web.dto.Book());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("uniqueAuthors", bookService.getAuthors());
            model.addAttribute("uniqueTitle", bookService.getTitle());
            model.addAttribute("uniqueSize", bookService.getSize());
            model.addAttribute("files", bookService.listOfFiles());
            return "book_shelf";
        } else {
            bookService.saveBook(book);
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }

    }

    @PostMapping("/remove")
    public String removeBook(@Valid BookIdToRemove bookIdToRemove, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("bindingResult.hasErrors()" + " IN REMOVE");
            model.addAttribute("book", new org.example.web.dto.Book());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("uniqueAuthors", bookService.getAuthors());
            model.addAttribute("uniqueTitle", bookService.getTitle());
            model.addAttribute("uniqueSize", bookService.getSize());
            model.addAttribute("files", bookService.listOfFiles());
            return "book_shelf";
        } else {
            logger.info("NORMAL id to remove");
            bookService.removeBookById(bookIdToRemove.getId());
            return "redirect:/books/shelf";
        }

    }

    @PostMapping("/filterAndRemove")
    public String selectRemoveBook(@RequestParam(value = "RSelectAuthor", required = false, defaultValue = DEFAULT_VALUE) String RSelectAuthor,
                                   @RequestParam(value = "RSelectTitle", required = false, defaultValue = DEFAULT_VALUE) String RSelectTitle,
                                   @RequestParam(value = "RSelectSize", required = false) Integer RSelectSize,
                                   @RequestParam(value = "button") String button,
                                   Model model) {
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

            model.addAttribute("book", new org.example.web.dto.Book());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookList", bookService.filterBooks(RSelectAuthor, RSelectTitle, RSelectSize));
            model.addAttribute("uniqueAuthors", bookService.getAuthors());
            model.addAttribute("uniqueTitle", bookService.getTitle());
            model.addAttribute("uniqueSize", bookService.getSize());
            model.addAttribute("files", bookService.listOfFiles());
            return "book_shelf";
        }
        return "redirect:/books/shelf";
    }


    @PostMapping("/uploadFile")
    public String uploadFile(@Valid @RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return "errors/500";
        } else {

            String name = file.getOriginalFilename();
            byte[] bytes = file.getBytes();

            //create dir
            String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath + File.separator + "external_uploads");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            //create file
            File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            logger.info("new file saved at: " + serverFile.getAbsolutePath());

            return "redirect:/books/shelf";
        }
    }

    @RequestMapping("/downloadFile")
    @ResponseBody
    public void downloadFile(@RequestParam(value = "fileToDownload", required = false) String fileToDownload, HttpServletResponse response) {
        if (fileToDownload.indexOf(".doc") > 1) response.setContentType("application/msword");
        if (fileToDownload.indexOf(".docx") > 1) response.setContentType("application/msword");
        if (fileToDownload.indexOf(".xls") > 1) response.setContentType("application/vnd.ms-excel");
        if (fileToDownload.indexOf(".csv") > 1) response.setContentType("application/vnd.ms-excel");
        if (fileToDownload.indexOf(".ppt") > 1) response.setContentType("application/ppt");
        if (fileToDownload.indexOf(".pdf") > 1) response.setContentType("application/pdf");
        if (fileToDownload.indexOf(".zip") > 1) response.setContentType("application/zip");

        response.setHeader("Content-Disposition", "attachment; filename=" + fileToDownload);
        response.setHeader("Content-Transfer-Encoding", "binary");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream(folderPath + fileToDownload);
            int len;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) > 0) {
                bos.write(buf, 0, len);

            }
            bos.close();
            response.flushBuffer();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}





