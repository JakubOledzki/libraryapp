package com.joledzki.user;

import com.joledzki.authorities.Authorities;
import com.joledzki.book.Book;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=4, max=32, message = "Username must be between 4 and 32 characters")
    private String username;
    private String password;

    @NotNull
    @Size(min=3, max=32, message = "Firstname must be between 4 and 32 characters")
    private String firstname;

    @NotNull
    @Size(min=3, max=32, message = "Lastname must be between 4 and 32 characters")
    private String lastname;

    @OneToMany(mappedBy = "createdByUser", fetch = FetchType.EAGER)
    private Set<Book> books;

    @OneToMany(mappedBy = "rentedByUser")
    private Set<Book> rentedBooks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_authorities",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id")
    )
    private List<Authorities> authorities;

    public User(){}
    public User(String username, String password, String firstname, String lastname, List<Authorities> authorities){
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password){
        this.password = password;
    }
    @Override
    public String getPassword() {
        return this.password;
    }


    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username){
        this.username = username;
    }
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<Book> getBooks() {
        return books;
    }
    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<Book> getRentedBooks() {
        return rentedBooks;
    }
    public void setRentedBooks(Set<Book> rentedBooks) {
        this.rentedBooks = rentedBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
