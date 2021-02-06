package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.controllers.BookShelfController;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {
    private final ProjectRepository<Book> bookRepo;
    private final String nullString = "";
    private Logger logger = Logger.getLogger(BookShelfController.class);


    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreivell();
    }


    public List<String> getAuthors() {
        return bookRepo.getUnicAuthors();
    }

    public List<String> getTitle() {
        return bookRepo.getUnicTitle();
    }

    public List<Integer> getSize() {
        return bookRepo.getUnicSize();
    }


    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBookById(Integer bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }

    public void removeSelectedBook(String rSelectAuthor, String rSelectTitle, Integer rSelectSize) {
        if (rSelectAuthor != nullString & rSelectTitle == nullString & rSelectSize == null) {
            bookRepo.removeMethod1(rSelectAuthor);
        } else if (rSelectAuthor != nullString & rSelectTitle != nullString & rSelectSize == null) {
            bookRepo.removeMethod2(rSelectAuthor, rSelectTitle);
        } else if (rSelectAuthor != nullString & rSelectTitle != nullString & rSelectSize != null) {
            bookRepo.removeMethod3(rSelectAuthor, rSelectTitle, rSelectSize);
        } else if (rSelectAuthor != nullString & rSelectTitle == nullString & rSelectSize != null) {
            bookRepo.removeMethod4(rSelectAuthor, rSelectSize);
        } else if (rSelectAuthor == nullString & rSelectTitle == nullString & rSelectSize != null) {
            bookRepo.removeMethod5(rSelectSize);
        } else if (rSelectAuthor == nullString & rSelectTitle != nullString & rSelectSize != null) {
            bookRepo.removeMethod6(rSelectTitle, rSelectSize);
        } else if (rSelectAuthor == nullString & rSelectTitle != nullString & rSelectSize == null) {
            bookRepo.removeMethod7(rSelectTitle);
        }
    }

    public void filterBooks(String rSelectAuthor, String rSelectTitle, Integer rSelectSize) {
        if (rSelectAuthor != nullString & rSelectTitle == nullString & rSelectSize == null) {
            bookRepo.filterMethod1(rSelectAuthor);
        } else if (rSelectAuthor != nullString & rSelectTitle != nullString & rSelectSize == null) {
            bookRepo.filterMethod2(rSelectAuthor, rSelectTitle);
        } else if (rSelectAuthor != nullString & rSelectTitle != nullString & rSelectSize != null) {
            bookRepo.filterMethod3(rSelectAuthor, rSelectTitle, rSelectSize);
        } else if (rSelectAuthor != nullString & rSelectTitle == nullString & rSelectSize != null) {
            bookRepo.filterMethod4(rSelectAuthor, rSelectSize);
        } else if (rSelectAuthor == nullString & rSelectTitle == nullString & rSelectSize != null) {
            bookRepo.filterMethod5(rSelectSize);
        } else if (rSelectAuthor == nullString & rSelectTitle != nullString & rSelectSize != null) {
            bookRepo.filterMethod6(rSelectTitle, rSelectSize);
        } else if (rSelectAuthor == nullString & rSelectTitle != nullString & rSelectSize == null) {
            bookRepo.filterMethod7(rSelectTitle);
        }
    }

    public void resetFilter() {
        bookRepo.resetFilter();
    }
}
