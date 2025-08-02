package com.example.Book_management.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class BookModel {
    // 主キー定義フィールド
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // プライベートフィールド
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private Boolean available;

    // デフォルトコンストラクタ(JPA用)
    public BookModel(){}

    // コンストラクタ
    public BookModel(String title, String author, String isbn, String genre, Boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.available = true;
    }

    // ゲッター
    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getisbn() {
        return this.isbn;
    }

    public String getGenre() {
        return this.genre;
    }

    public Boolean getAvailable() {
        return this.available;
    }

    // セッター
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
