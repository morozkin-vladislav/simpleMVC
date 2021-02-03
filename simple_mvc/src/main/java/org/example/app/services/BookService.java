package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {
    private final ProjectRepository<Book> bookRepo;


    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreivell();
    }

    public List<String> getRemoveList() {
        return bookRepo.getRemoveList();
    }


    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBookById(Integer bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }

    public boolean selectRemove(String whatToRemove, String valueToRemove) {
        boolean didRemove = false;
        switch (whatToRemove) {
            case "author":
                didRemove = bookRepo.removeForAuthor(valueToRemove);
                break;
            case "size":
                didRemove = bookRepo.removeToSize(valueToRemove);
                break;
            case "book_title":
                didRemove = bookRepo.removeToTitle(valueToRemove);
                break;
        }
        return didRemove;
    }

    public void resetFilter() {
        bookRepo.resetF();
    }


    public boolean filterBooks(String whatToFilter, String valueToFilter) {
        boolean didFilter = false;
        switch (whatToFilter) {
            case "author":
                didFilter = bookRepo.filterForAuthor(valueToFilter);
                break;
            case "size":
                didFilter = bookRepo.filterToSize(valueToFilter);
                break;
            case "book_title":
                didFilter = bookRepo.filterToTitle(valueToFilter);
                break;
        }
        return didFilter;
    }
}
