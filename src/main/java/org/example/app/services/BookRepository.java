package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private List<Book> repo = new ArrayList<>();
    private final List<Book> notFiltredRepo = new ArrayList<>();


    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
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
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                notFiltredRepo.remove(book);
                return repo.remove(book);
            }
        }
        return false;
    }


    @Override
    public List<String> getUniqueAuthors() {
        ArrayList<String> tempAuthors = new ArrayList<>();
        for (Book book : notFiltredRepo) {
            tempAuthors.add(book.getAuthor());
        }

        return tempAuthors.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getUniqueTitle() {
        ArrayList<String> tempTitle = new ArrayList<>();
        for (Book book : notFiltredRepo) {
            tempTitle.add(book.getTitle());
        }
        return tempTitle.stream().distinct().collect(Collectors.toList());
    }


    @Override
    public List<Integer> getUniqueSize() {
        ArrayList<Integer> tempSize = new ArrayList<>();
        for (Book book : notFiltredRepo) {
            tempSize.add(book.getSize());
        }

        return tempSize.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void removeBookByAuthor(String rSelectAuthor) {
        for (Book book : retreiveAll()) {
            if (book.getAuthor().equals(rSelectAuthor)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeBookByAuthorTitle(String rSelectAuthor, String rSelectTitle) {
        for (Book book : retreiveAll()) {
            if (book.getAuthor().equals(rSelectAuthor) && book.getTitle().equals(rSelectTitle)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeBookByAuthorTitleSize(String rSelectAuthor, String rSelectTitle, Integer rSelectSize) {
        for (Book book : retreiveAll()) {
            if (book.getAuthor().equals(rSelectAuthor) && book.getTitle().equals(rSelectTitle) && book.getSize().equals(rSelectSize)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeBookByAuthorSize(String rSelectAuthor, Integer rSelectSize) {
        for (Book book : retreiveAll()) {
            if (book.getAuthor().equals(rSelectAuthor) && book.getSize().equals(rSelectSize)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeBookBySize(Integer rSelectSize) {
        for (Book book : retreiveAll()) {
            if (book.getSize().equals(rSelectSize)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeBookByTitleSize(String rSelectTitle, Integer rSelectSize) {
        for (Book book : retreiveAll()) {
            if (book.getTitle().equals(rSelectTitle) && book.getSize().equals(rSelectSize)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeBookByTitle(String rSelectTitle) {
        for (Book book : retreiveAll()) {
            if (book.getTitle().equals(rSelectTitle)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void resetFilter() {
        repo.clear();
        repo.addAll(notFiltredRepo);
    }

    @Override
    public void filterBookByAuthor(String rSelectAuthor) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getAuthor().equals(rSelectAuthor)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterBookByAuthorTitle(String rSelectAuthor, String rSelectTitle) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getAuthor().equals(rSelectAuthor) & books.getTitle().equals(rSelectTitle)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterBookByAuthorTitleSize(String rSelectAuthor, String rSelectTitle, Integer rSelectSize) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getAuthor().equals(rSelectAuthor) & books.getTitle().equals(rSelectTitle) & books.getSize().equals(rSelectSize)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterBookByAuthorSize(String rSelectAuthor, Integer rSelectSize) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getAuthor().equals(rSelectAuthor) & books.getSize().equals(rSelectSize)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterBookBySize(Integer rSelectSize) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getSize().equals(rSelectSize)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterBookByTitleSize(String rSelectTitle, Integer rSelectSize) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getTitle().equals(rSelectTitle) & books.getSize().equals(rSelectSize)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterBookByTitle(String rSelectTitle) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getTitle().equals(rSelectTitle)) {
                repo.add(books);
            }
        }
    }


}

