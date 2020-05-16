package com.joledzki.controller;

import com.joledzki.book.Book;
import com.joledzki.book.BookRepository;
import com.joledzki.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BookController {

    private BookRepository bookRepository;
    private UserServiceImpl userService;

    @Autowired
    public BookController(BookRepository bookRepository, UserServiceImpl userService){
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    @GetMapping("/create-book")
    public String bookForm(Model model){
        model.addAttribute("book",new Book());
        return "createBook";
    }

    @PostMapping("/addBook")
    public String addBook(Book book){

        book.setCreatedByUser(userService.getUserDetails());
        bookRepository.save(book);
        return "createBook";
    }

    @GetMapping("/list-books")
    public String getListBook(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "listBook";
    }

    @GetMapping("/rentBook")
    public String getRentBook(@RequestParam Long id){

        Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent()){
            if(book.get().getRentedByUser()!=null){
                System.out.println("THIS BOOK IS ALREADY RENTED");
                return "listBook";
            }
            else {
                bookRepository.setUserRent(id, userService.getUserDetails());
                System.out.println("USER: " + userService.getUserDetails().getUsername() + " RENTED BOOK");
            }
        }
        else{
            System.out.println("BOOK DOESNT EXISTS");
        }
        return "listBook";
    }

}
