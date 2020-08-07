package com.joledzki.controller;

import com.joledzki.authorities.Authorities;
import com.joledzki.authorities.AuthoritiesRepository;
import com.joledzki.authorities.AuthoritiesService;
import com.joledzki.book.Book;
import com.joledzki.book.BookRepository;
import com.joledzki.book.BookService;
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
    private UserServiceImpl userService;
    @Autowired
    private AuthoritiesService authoritiesService;
    @Autowired
    private BookService bookService;



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

        if(bookService.initBook(book, userService.getUserDetails())){
            return "redirect:/";
        } else{new Exception("CREATE BOOK ERROR");}
        return "createBook";

    }

    //All of books
    @GetMapping("/books")
    public String getListBook(Model model){
        User user = userService.getUserDetails();
        model.addAttribute("user",user);
        model.addAttribute("authorities", authoritiesService.getAuthoritiesNameForUser(user.getAuthorities()));
        model.addAttribute("books", bookService.getAllBooks());
        return "listBook";
    }

    //List user's books
    @GetMapping("/books/{id}")
    public String getMyBooks(@PathVariable Long id, Model model){
        User user = userService.getUserDetails();
        model.addAttribute("user",user);
        model.addAttribute("authorities", authoritiesService.getAuthoritiesNameForUser(user.getAuthorities()));
        model.addAttribute("books2",bookService.getAllBooksByUser(user));
        return "myBooks";
    }

    @GetMapping("/book/rent/{id}")
    public String getRentBook(@PathVariable Long id){

        bookService.rentBookById(id, userService.getUserDetails());

        return "redirect:/books";
    }

    @GetMapping("/book/back/{id}")
    public String giveBookBack(@PathVariable Long id){
        if(bookService.giveBookBackByUser(id, userService.getUserDetails())){
            return "redirect:/books";
        }
        return "index";
    }

    @GetMapping("/book/edit/{id}")
    public String editBook(@PathVariable Long id, Model model){
        User user = userService.getUserDetails();
        model.addAttribute("user",user);
        model.addAttribute("authorities", authoritiesService.getAuthoritiesNameForUser(user.getAuthorities()));
        Optional<Book> book = bookService.getBookById(id);
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
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        System.out.println("DELETE BOOK ID: "+ id);
        return "redirect:/books";
    }

}
