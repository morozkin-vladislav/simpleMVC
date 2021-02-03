package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private List<Book> repo = new ArrayList<>();
    private final List<Book> notFiltredRepo = new ArrayList<>();

    private final String author = "author";
    private final String size = "size";
    private final String book_title = "book_title";


    @Override
    public List<Book> retreivell() {
        return new ArrayList<>(repo);
    }

    @Override
    public List<String> getRemoveList() {
        final List<String> RemoveList = new ArrayList<>();
        RemoveList.add(author);
        RemoveList.add(book_title);
        RemoveList.add(size);
        logger.info("getRemoveList" + RemoveList);
        return new ArrayList<>(RemoveList);

    }

    @Override
    public void resetF() {
        repo = new ArrayList<>(notFiltredRepo);
    }


    @Override
    public void store(Book book) {
        if (book.getAuthor().length() != 0 || book.getTitle().length() != 0 || book.getSize() != null) {
            book.setId(book.hashCode());
            logger.info("store new book: " + book);
            repo.add(book);
            notFiltredRepo.add(book);
        }

    }


    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreivell()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                notFiltredRepo.remove(book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeForAuthor(final String valueToRemove) {
        notFiltredRepo.removeIf(n -> (n.getAuthor().equals(valueToRemove)));
        return repo.removeIf(n -> (n.getAuthor().equals(valueToRemove)));
    }

    @Override
    public boolean removeToSize(String valueToRemove) {
        Integer i = new Integer(valueToRemove);
        notFiltredRepo.removeIf(n -> (n.getSize().equals(i)));
        return repo.removeIf(n -> (n.getSize().equals(i)));
    }

    @Override
    public boolean removeToTitle(String valueToRemove) {
        notFiltredRepo.removeIf(n -> (n.getTitle().equals(valueToRemove)));
        return repo.removeIf(n -> (n.getTitle().equals(valueToRemove)));
    }

    @Override
    public boolean filterForAuthor(String valueToFilter) {
        return repo.removeIf(n -> (n.getAuthor().equals(valueToFilter)));
    }

    @Override
    public boolean filterToSize(String valueToFilter) {
        Integer i = new Integer(valueToFilter);
        return repo.removeIf(n -> (n.getSize().equals(i)));
    }

    @Override
    public boolean filterToTitle(String valueToFilter) {
        return repo.removeIf(n -> (n.getTitle().equals(valueToFilter)));
    }


}

