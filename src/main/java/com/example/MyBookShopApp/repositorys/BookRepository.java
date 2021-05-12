package com.example.MyBookShopApp.repositorys;

import com.example.MyBookShopApp.data.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.sql.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthor_FirstName(String name);

    @Query("from Book")
    List<Book> customFindAllBooks();

    //NEW BOOK REST REPOSITORY COMMANDS

    List<Book> findBooksByAuthorFirstNameContaining(String authorFirstName);

    List<Book> findBooksByTitleContaining(String bookTitle);

    List<Book> findBooksByPriceOldBetween(Integer min, Integer max);

    List<Book> findBooksByPriceOldIs(Integer price);

    @Query("from Book where isBestseller=1")
    List<Book> getBestsellers();

    @Query(value = "SELECT * from books where pub_date > (CURRENT_DATE - 240)", nativeQuery = true)
    List<Book> findRecentBooks(Pageable nextPage);

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    @Query(value = "SELECT * FROM books LEFT OUTER JOIN books_rating ON (books.id = books_rating.book_id) WHERE books_rating.rating = 5", nativeQuery = true)
    List<Book> findPopularBooks(Pageable nextPage);

    List<Book> findBookByGenreId(Integer id,Pageable nextPage );

    List<Book> findBookByTagId(Integer id,Pageable nextPage );

    List<Book> findBookByAuthorId(Integer id,Pageable nextPage );

    List<Book> findBookByPubDateBetween(Date dateFrom, Date dateTo, Pageable nextPage);


    Page<Book> findBookByTitleContaining(String bookTitle, Pageable nextPage);
}
