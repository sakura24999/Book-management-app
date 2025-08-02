package com.example.Book_management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Book_management.Model.BookModel;
import com.example.Book_management.Repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // 基本CRUD操作(必須)
    public BookModel create(BookModel id) {
        
    }
}
