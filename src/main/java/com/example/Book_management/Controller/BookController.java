package com.example.Book_management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Book_management.Service.BookService;

@Controller
public class BookController {
    // Serviceを注入
    @Autowired
    private BookService bookService;

    // Home画面(登録)
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    // ログイン画面
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 登録フォーム
    @PostMapping("/register")
    public String register(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam String confirmPassword,
        RedirectAttributes redirectAttributes) {
            // パスワード確認
            if (!password.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error", "パスワードが一致しません");
                return "redirect:/";
            }

            // ここにユーザー登録処理を実装
            

            // 登録完了画面へリダイレクト
            return "redirect:/register/complete";
        }

        // 登録完了画面
        @GetMapping("/register/complete")
        public String registerComplete() {
            return "completion";
        }
}
