package com.bean;

import javax.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int authorId;

    private String authorName;
    private String authorCity;

    @ManyToOne
    private Book book;

    public Author(int authorId, String authorName, String authorCity, Book book) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorCity = authorCity;
        this.book = book;
    }

    public Author() {

    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorCity() {
        return authorCity;
    }

    public void setAuthorCity(String authorCity) {
        this.authorCity = authorCity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorCity='" + authorCity + '\'' +
                ", book=" + book +
                '}';
    }
}
