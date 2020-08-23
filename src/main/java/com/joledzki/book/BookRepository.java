package com.joledzki.book;

import com.joledzki.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(Long id);

    List<Book> findAllByRentedByUser(User user);
//    Optional<Book> findByIdAndRentedByUser(Long id, User user);

    @Transactional
    void deleteBookById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.rentedByUser=:user WHERE b.id=:id")
    void setUserRent(@Param("id") Long id, @Param("user") User user);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.author=:author, b.title=:title, b.description=:desc WHERE b.id=:id")
    void updateBook(@Param("id") Long id, @Param("author") String author, @Param("title") String title, @Param("desc") String desc);

}
