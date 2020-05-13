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
        System.out.println("Add book: "+book);
        bookRepository.save(book);
        return "createBook";
    }

}
