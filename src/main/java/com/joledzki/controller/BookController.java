package com.joledzki.controller;

import com.joledzki.authorities.Authorities;
import com.joledzki.authorities.AuthoritiesRepository;
import com.joledzki.authorities.AuthoritiesService;
import com.joledzki.book.Book;
import com.joledzki.book.BookRepository;
import com.joledzki.user.User;
import com.joledzki.user.UserRepository;
import com.joledzki.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthoritiesService authoritiesService;



    @GetMapping("/book/create")
    public String bookForm(Model model){
        User user = userService.getUserDetails();
        model.addAttribute("user",user);
        model.addAttribute("authorities", authoritiesService.getAuthoritiesNameForUser(user.getAuthorities()));
        model.addAttribute("book",new Book());
        return "createBook";
    }

    @PostMapping("/addBook")
    public String addBook(Book book){

        book.setCreatedByUser(userService.getUserDetails());
        bookRepository.save(book);
        return "createBook";
    }

    //All of books
    @GetMapping("/books")
    public String getListBook(Model model){
        User user = userService.getUserDetails();
        model.addAttribute("user",user);
        model.addAttribute("authorities", authoritiesService.getAuthoritiesNameForUser(user.getAuthorities()));
        model.addAttribute("books", bookRepository.findAll());
        return "listBook";
    }

    //List user's books
    @GetMapping("/books/{id}")
    public String getMyBooks(@PathVariable Long id, Model model){
        User user = userService.getUserDetails();
        model.addAttribute("user",user);
        model.addAttribute("authorities", authoritiesService.getAuthoritiesNameForUser(user.getAuthorities()));
        model.addAttribute("books2",bookRepository.findAllByRentedByUser(userRepository.findById(id).get()));
        return "myBooks";
    }

    @GetMapping("/book/rent/{id}")
    public String getRentBook(@PathVariable Long id){

        Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent()){
            if(book.get().getRentedByUser()!=null){
                System.out.println("THIS BOOK IS ALREADY RENTED");
                return "redirect:/books";
            }
            else {
                bookRepository.setUserRent(id, userService.getUserDetails());
                System.out.println("USER: " + userService.getUserDetails().getUsername() + " RENTED BOOK");
            }
        }
        else{
            System.out.println("BOOK DOESN'T EXISTS");
        }
        return "redirect:/books";
    }

    @GetMapping("/book/back/{id}")
    public String giveBookBack(@PathVariable Long id){
        Optional<Book> book = bookRepository.findByIdAndRentedByUser(id, userService.getUserDetails());
        if(book != null){
            bookRepository.setUserRent(id,null);
            System.out.println("USER GIVE THE BOOK BACK");
        }
        else {
            System.out.println("ITS NOT YOUR BOOK");
        }
        return "redirect:/books";
    }

    @GetMapping("/book/edit/{id}")
    public String editBook(@PathVariable Long id, Model model){
        User user = userService.getUserDetails();
        model.addAttribute("user",user);
        model.addAttribute("authorities", authoritiesService.getAuthoritiesNameForUser(user.getAuthorities()));
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent()){
            System.out.println("Book doesn't exist");
        }
        else {
            //BOOK EXISTS
            model.addAttribute("book",book.get());
        }
        return "editBook";
    }

    @PostMapping("/initEditBook")
    public String initEditBook(Book book){
        System.out.println(book.getId());
        bookRepository.updateBook(book.getId(),book.getAuthor(), book.getTitle(), book.getDescription());
        return "redirect:/books";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookRepository.deleteBookById(id);
        System.out.println("DELETE BOOK ID: "+ id);
        return "redirect:/books";
    }

}
