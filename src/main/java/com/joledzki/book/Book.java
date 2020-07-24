package com.joledzki.book;

import com.joledzki.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String description;

    @ManyToOne
    private User createdByUser;

    @ManyToOne
    private User rentedByUser;

    public Book(String title, String author, String description,User createdByUser){
        this.title = title;
        this.author = author;
        this.description = description;
        this.createdByUser = createdByUser;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", createdByUser=" + createdByUser.getUsername() +
                ", rentedByUser=" + rentedByUser.getUsername() +
                '}';
    }
}
