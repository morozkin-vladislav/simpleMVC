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
    private static final String DEFAULT_VALUE = "defaultValue";
    private Logger logger = Logger.getLogger(BookShelfController.class);

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreiveAll();
    }


    public List<String> getAuthors() {
        return bookRepo.getUniqueAuthors();
    }

    public List<String> getTitle() {
        return bookRepo.getUniqueTitle();
    }

    public List<Integer> getSize() {
        return bookRepo.getUniqueSize();
    }


    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBookById(Integer bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }

    enum serviceValue {
        DEFAULT,
        BY_AUTHOR,
        BY_AUTHOR_TITLE,
        BY_AUTHOR_TITLE_SIZE,
        BY_AUTHOR_SIZE,
        BY_SIZE,
        BY_TITLE_SIZE,
        BY_TITLE
    }

    public serviceValue determineValues(String rSelectAuthor, String rSelectTitle, Integer rSelectSize) {
        serviceValue value = serviceValue.DEFAULT;
        if (!rSelectAuthor.equals(DEFAULT_VALUE) & rSelectTitle.equals(DEFAULT_VALUE) & rSelectSize == null) {
            value = serviceValue.BY_AUTHOR;
        } else if (!rSelectAuthor.equals(DEFAULT_VALUE) & !rSelectTitle.equals(DEFAULT_VALUE) & rSelectSize == null) {
            value = serviceValue.BY_AUTHOR_TITLE;
        } else if (!rSelectAuthor.equals(DEFAULT_VALUE) & !rSelectTitle.equals(DEFAULT_VALUE) & rSelectSize != null) {
            value = serviceValue.BY_AUTHOR_TITLE_SIZE;
        } else if (!rSelectAuthor.equals(DEFAULT_VALUE) & rSelectTitle.equals(DEFAULT_VALUE) & rSelectSize != null) {
            value = serviceValue.BY_AUTHOR_SIZE;
        } else if (rSelectAuthor.equals(DEFAULT_VALUE) & rSelectTitle.equals(DEFAULT_VALUE) & rSelectSize != null) {
            value = serviceValue.BY_SIZE;
        } else if (rSelectAuthor.equals(DEFAULT_VALUE) & !rSelectTitle.equals(DEFAULT_VALUE) & rSelectSize != null) {
            value = serviceValue.BY_TITLE_SIZE;
        } else if (rSelectAuthor.equals(DEFAULT_VALUE) & !rSelectTitle.equals(DEFAULT_VALUE) & rSelectSize == null) {
            value = serviceValue.BY_TITLE;
        }
        return value;
    }

    public void removeSelectedBook(String rSelectAuthor, String rSelectTitle, Integer rSelectSize) {
        switch (determineValues(rSelectAuthor, rSelectTitle, rSelectSize)) {
            case BY_AUTHOR:
                bookRepo.removeBookByAuthor(rSelectAuthor);
                break;
            case BY_SIZE:
                bookRepo.removeBookBySize(rSelectSize);
                break;
            case BY_TITLE:
                bookRepo.removeBookByTitle(rSelectTitle);
                break;
            case BY_AUTHOR_TITLE:
                bookRepo.removeBookByAuthorTitle(rSelectAuthor, rSelectTitle);
                break;
            case BY_AUTHOR_SIZE:
                bookRepo.removeBookByAuthorSize(rSelectAuthor, rSelectSize);
                break;
            case BY_TITLE_SIZE:
                bookRepo.removeBookByTitleSize(rSelectTitle, rSelectSize);
                break;
            case BY_AUTHOR_TITLE_SIZE:
                bookRepo.removeBookByAuthorTitleSize(rSelectAuthor, rSelectTitle, rSelectSize);
                break;
        }
    }

    public void filterBooks(String rSelectAuthor, String rSelectTitle, Integer rSelectSize) {
        switch (determineValues(rSelectAuthor, rSelectTitle, rSelectSize)) {
            case BY_AUTHOR:
                bookRepo.filterBookByAuthor(rSelectAuthor);
                break;
            case BY_SIZE:
                bookRepo.filterBookBySize(rSelectSize);
                break;
            case BY_TITLE:
                bookRepo.filterBookByTitle(rSelectTitle);
                break;
            case BY_AUTHOR_TITLE:
                bookRepo.filterBookByAuthorTitle(rSelectAuthor, rSelectTitle);
                break;
            case BY_AUTHOR_SIZE:
                bookRepo.filterBookByAuthorSize(rSelectAuthor, rSelectSize);
                break;
            case BY_TITLE_SIZE:
                bookRepo.filterBookByTitleSize(rSelectTitle, rSelectSize);
                break;
            case BY_AUTHOR_TITLE_SIZE:
                bookRepo.filterBookByAuthorTitleSize(rSelectAuthor, rSelectTitle, rSelectSize);
                break;
        }
    }

    public void resetFilter() {
        bookRepo.resetFilter();
    }
}
