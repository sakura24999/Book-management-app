package com.example.Book_management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

import com.example.Book_management.Service.BookService;

@Controller
public class BookController {
    // Serviceを注入
    @Autowired
    private BookService bookService;

    // Home画面
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
}
