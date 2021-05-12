package com.example.MyBookShopApp.data;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@ApiModel(description = "entity representing a book")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id generated by db automaticaly")
    private Integer id;

    @Column(name = "pub_date")
    @ApiModelProperty("date of book publication")
    private Date pubDate;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @JsonIgnore
    private Author author;

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    @JsonIgnore
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    @JsonIgnore
    private Genre genre;



    @Column(name = "is_bestseller")
    @ApiModelProperty("if isBestseller = 1 so the book is considered to be bestseller and  if 0 the book is not a " +
            "bestseller")
    private Integer isBestseller;

    @ApiModelProperty("mnemonical identity sequence of characters")
    private String slug;
    @ApiModelProperty("book title")
    private String title;
    @ApiModelProperty("image url")
    private String image;

    @Column(columnDefinition = "TEXT")
    @ApiModelProperty("book description text")
    private String description;

    @Column(name = "price")
    @JsonProperty("price")
    @ApiModelProperty("book price without discount")
    private Integer priceOld;

    @Column(name = "discount")
    @JsonProperty("discount")
    @ApiModelProperty("discount value for book")
    private Double price;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(Integer isBestseller) {
        this.isBestseller = isBestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Integer priceOld) {
        this.priceOld = priceOld;
    }

    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", priceOld='" + priceOld + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
