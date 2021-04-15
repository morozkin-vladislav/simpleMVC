package com.example.MyBookShopApp.data;

public class Author {
    private  Integer id;
    private String author;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", author='" + author + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }
}
