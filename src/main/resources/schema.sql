-- Booksテーブル --
CREATE TABLE IF NOT EXISTS books (
    book_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    genre VARCHAR(100),
    available BOOLEAN DEFAULT TRUE
);

-- Loan Historyテーブル --
CREATE TABLE IF NOT EXISTS loan_history (
    history_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT NOT NULL,
    lend_date DATETIME NOT NULL,
    return_date DATETIME,
    FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
);

-- インデックス(検索速度向上のため)
CREATE INDEX idx_books_isbn ON books(isbn);
CREATE INDEX idx_books_available ON books(available);
CREATE INDEX idx_loan_history_book_id ON loan_history(book_id);
CREATE INDEX idx_loan_history_return_date ON loan_history(return_date);