package com.example.Book_management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.Book_management.Service.UserService;

@Controller
public class UserController {
    // Serviceを注入
    @Autowired
    private UserService userService;
}
