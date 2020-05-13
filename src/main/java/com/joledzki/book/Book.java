package com.joledzki.book;

import com.joledzki.user.User;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String description;

    @ManyToOne
    private User createdByUser;

    public Book(){}
    public Book(String title, String author, String description,User createdByUser){
        this.title = title;
        this.author = author;
        this.description = description;
        this.createdByUser = createdByUser;
    }

    public void setId(Long id) {this.id = id;}
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public User getCreatedByUser() {return createdByUser;}
    public void setCreatedByUser(User createdByUser) {this.createdByUser = createdByUser;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", createdByUser=" + createdByUser +
                '}';
    }
}
