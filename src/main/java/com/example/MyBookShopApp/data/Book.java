package com.example.MyBookShopApp.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String priceOld;

    @Column(nullable = false)
    private String price;

//    @Column(nullable = false)
//    private LocalDateTime pub_date;
//
//    @Column(nullable = false, name = "is_bestseller")
//    private boolean isBestseller;
//
//    @Column(nullable = false)
//    private String slug;
//
//    @Column(nullable = false)
//    private Integer discount;
//
//    @Column(nullable = false)
//    private String image;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriceOld(String priceOld) {
        this.priceOld = priceOld;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPriceOld() {
        return priceOld;
    }

    public String getPrice() {
        return price;
    }
}
