package com.example.Book_management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Book_management.Model.BookModel;
import com.example.Book_management.Repository.BookRepository;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // 基本CRUD操作
    public BookModel create(BookModel book) {
        return bookRepository.save(book);
    }

    public BookModel findById(Long bookId) {
        return bookRepository.findById(bookId)
        .orElseThrow(() -> new RuntimeException("本が見つかりません: " + bookId));
    }

    public List<BookModel> findAll() {
        return bookRepository.findAll();
    }

    public BookModel update(BookModel book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    // ビジネスロジック
    // 本の登録
    public BookModel registerBook(BookModel book) {
        // ビジネスルール
        // ISBN重複チェック
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new RuntimeException("このISBNは既に登録されています");
        }

        // 新規登録時は貸出し可能
        book.setAvailable(true);

        return bookRepository.save(book);
    }

    // 本の貸出し
    public BookModel lendbook(BookModel book) {
        // 貸出しルール
        if (!book.getAvailable()) {
            throw new RuntimeException("この本は貸出し中です");
        }
        // 貸出し処理
        book.setAvailable(false);
        return bookRepository.save(book);
    }
}
