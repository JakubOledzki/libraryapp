package com.joledzki.book;

import com.joledzki.user.User;
import com.joledzki.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;


    public boolean initBook(Book book, User creator ){

       book.setCreatedByUser(creator);
       bookRepository.save(book);
        return true;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> getAllBooksByUser(User user){
        return bookRepository.findAllByRentedByUser(user);
    }

    public void rentBookById(Long bookId, User user){

        Optional<Book> book = bookRepository.findById(bookId);

        if(book.isPresent()){
            if(book.get().getRentedByUser()!=null){
                System.out.println("THIS BOOK IS ALREADY RENTED");
            }
            else {
                bookRepository.setUserRent(bookId, user);
                System.out.println("USER: " + user.getUsername() + " RENTED BOOK");
            }

        }
        else{
            System.out.println("BOOK DOESN'T EXISTS");
        }

    }

    public boolean giveBookBackByUser(Long idBook, User user){

        Optional<Book> book = bookRepository.findById(idBook);

        if(book.isPresent()){
            if(book.get().getRentedByUser() == null || book.get().getRentedByUser().getId() == user.getId()){
                System.out.println("this book isn't rented or its not your book");
                return false;
            }
            else{
                bookRepository.setUserRent(idBook, null);
                System.out.println("user give book back");
                return true;
            }
        }
        else{
            System.out.println("[ERROR] giveBookBackByUser: book doesn't exist in database");
            return false;
        }

    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public void updateBook(Book book){
        bookRepository.updateBook(book.getId(),book.getAuthor(), book.getTitle(), book.getDescription());
    }

    public void deleteBook(Long id){
        bookRepository.deleteBookById(id);
    }


}
