package com.example.MyBookShopApp.data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
//
//    @Column(nullable = false)
//    private String slug;
//
//    @Column(nullable = false)
//    private String photo;
//
//    private String description;


    @OneToMany(mappedBy = "author")


    private List<Book> bookList = new ArrayList<>();

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirsName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
