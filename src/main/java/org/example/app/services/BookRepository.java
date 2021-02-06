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

    private final String author = "author";
    private final String size = "size";
    private final String book_title = "book_title";


    @Override
    public List<Book> retreivell() {
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
    public List<String> getUnicAuthors() {
        ArrayList<String> tempAuthors = new ArrayList<>();
        for (Book book : notFiltredRepo) {
            tempAuthors.add(book.getAuthor());
        }
        List<String> unicAuthors = new ArrayList<>();
        return unicAuthors = tempAuthors.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getUnicTitle() {
        ArrayList<String> tempTitle = new ArrayList<>();
        for (Book book : notFiltredRepo) {
            tempTitle.add(book.getTitle());
        }
        List<String> unicTitle = new ArrayList<>();
        return unicTitle = tempTitle.stream().distinct().collect(Collectors.toList());
    }


    @Override
    public List<Integer> getUnicSize() {
        ArrayList<Integer> tempSize = new ArrayList<>();
        for (Book book : notFiltredRepo) {
            tempSize.add(book.getSize());
        }
        List<Integer> unicSize = new ArrayList<>();
        return unicSize = tempSize.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void removeMethod1(String rSelectAuthor) {
        for (Book book : retreivell()) {
            if (book.getAuthor().equals(rSelectAuthor)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeMethod2(String rSelectAuthor, String rSelectTitle) {
        for (Book book : retreivell()) {
            if (book.getAuthor().equals(rSelectAuthor) && book.getTitle().equals(rSelectTitle)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeMethod3(String rSelectAuthor, String rSelectTitle, Integer rSelectSize) {
        for (Book book : retreivell()) {
            if (book.getAuthor().equals(rSelectAuthor) && book.getTitle().equals(rSelectTitle) && book.getSize().equals(rSelectSize)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeMethod4(String rSelectAuthor, Integer rSelectSize) {
        for (Book book : retreivell()) {
            if (book.getAuthor().equals(rSelectAuthor) && book.getSize().equals(rSelectSize)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeMethod5(Integer rSelectSize) {
        for (Book book : retreivell()) {
            if (book.getSize().equals(rSelectSize)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeMethod6(String rSelectTitle, Integer rSelectSize) {
        for (Book book : retreivell()) {
            if (book.getTitle().equals(rSelectTitle) && book.getSize().equals(rSelectSize)) {
                notFiltredRepo.remove(book);
                repo.remove(book);
            }
        }
    }

    @Override
    public void removeMethod7(String rSelectTitle) {
        for (Book book : retreivell()) {
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
    public void filterMethod1(String rSelectAuthor) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getAuthor().equals(rSelectAuthor)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterMethod2(String rSelectAuthor, String rSelectTitle) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getAuthor().equals(rSelectAuthor) & books.getTitle().equals(rSelectTitle)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterMethod3(String rSelectAuthor, String rSelectTitle, Integer rSelectSize) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getAuthor().equals(rSelectAuthor) & books.getTitle().equals(rSelectTitle) & books.getSize().equals(rSelectSize)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterMethod4(String rSelectAuthor, Integer rSelectSize) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getAuthor().equals(rSelectAuthor) & books.getSize().equals(rSelectSize)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterMethod5(Integer rSelectSize) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getSize().equals(rSelectSize)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterMethod6(String rSelectTitle, Integer rSelectSize) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getTitle().equals(rSelectTitle) & books.getSize().equals(rSelectSize)) {
                repo.add(books);
            }
        }
    }

    @Override
    public void filterMethod7(String rSelectTitle) {
        repo.clear();
        for (Book books : notFiltredRepo) {
            if (books.getTitle().equals(rSelectTitle)) {
                repo.add(books);
            }
        }
    }


}

