/* Create tables for the database */
/* Caution: This is also a reset database script */
/* Do not run unless the structure of the database is changed */
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `books`;
DROP TABLE IF EXISTS `copies`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `transactions`;
DROP TABLE IF EXISTS `fines`;

/* Create table for the users with user_id as primary key */
CREATE TABLE `users`
(
    user_id INTERGER PRIMARY KEY,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT,
    user_type TEXT CHECK (user_type IN ('admin', 'member'))
);

/* Create table for the products with product_id as primary key */
CREATE TABLE `books`
(
    book_id      INTERGER PRIMARY KEY,
    title        TEXT NOT NULL,
    author       TEXT NOT NULL,
    genre        TEXT,
    publish_date DATE,
    UNIQUE (title, author)
);

/* Create table for the copies of the books with copy_id as primary key */
CREATE TABLE `copies`
(
    copy_id      INTERGER PRIMARY KEY,
    book_id      INTERGER NOT NULL,
    available    BOOLEAN NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(book_id)
        ON DELETE CASCADE
);

/* Create table for the orders with order_id as primary key */
CREATE TABLE `orders`
(
    order_id     INTERGER PRIMARY KEY,
    user_id      INTERGER NOT NULL,
    copy_id      INTERGER NOT NULL,
    order_date   DATE NOT NULL,
    order_period INTEGER NOT NULL,
    order_status TEXT NOT NULL
        CHECK ( order_status IN ('pending', 'approved', 'rejected') ),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE,
    FOREIGN KEY (copy_id) REFERENCES copies(copy_id)
        ON DELETE CASCADE
);

/* Create table for the transactions with transaction_id as primary key */
CREATE TABLE `transactions`
(
    transaction_id INTERGER PRIMARY KEY,
    order_id       INTERGER NOT NULL,
    user_id        INTERGER NOT NULL,
    copy_id        INTERGER NOT NULL,
    checkout_date DATE NOT NULL,
    due_date      DATE NOT NULL,
    transaction_status TEXT NOT NULL
        CHECK ( transaction_status IN ('checked out', 'returned') ),
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
        ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE,
    FOREIGN KEY (copy_id) REFERENCES copies(copy_id)
        ON DELETE CASCADE
);

/* Create table for the fines with fine_id as primary key */
CREATE TABLE `fines`
(
    fine_id       INTERGER PRIMARY KEY,
    transaction_id INTERGER NOT NULL,
    fine_amount    INTERGER NOT NULL,
    fine_status    TEXT NOT NULL
        CHECK ( fine_status IN ('unpaid', 'paid') ),
    FOREIGN KEY (transaction_id) REFERENCES transactions(transaction_id)
        ON DELETE CASCADE
);

/* Add default admin user */
INSERT INTO users (user_id, username, password, first_name, last_name, email, user_type)
VALUES (1, 'default_admin', 'pap2023zz20', 'Super', 'Admin', 'libman@pap.com', 'admin');

INSERT INTO books (book_id, title, author, genre, publish_date) VALUES
(1, 'To Kill a Mockingbird', 'Harper Lee', 'Fiction', '1960-07-11'),
(2, '1984', 'George Orwell', 'Dystopian', '1949-06-08'),
(3, 'Pride and Prejudice', 'Jane Austen', 'Romance', '1813-01-28'),
(4, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', '1925-04-10'),
(5, 'One Hundred Years of Solitude', 'Gabriel García Márquez', 'Magical Realism', '1967-05-30'),
(6, 'Brave New World', 'Aldous Huxley', 'Dystopian', '1932-01-01'),
(7, 'The Catcher in the Rye', 'J.D. Salinger', 'Fiction', '1951-07-16'),
(8, 'The Hobbit', 'J.R.R. Tolkien', 'Fantasy', '1937-09-21'),
(9, 'Crime and Punishment', 'Fyodor Dostoevsky', 'Philosophical Fiction', '1866-11-11'),
(10, 'The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', '1954-07-29'),
(11, 'Moby-Dick', 'Herman Melville', 'Adventure', '1851-10-18'),
(12, 'Frankenstein', 'Mary Shelley', 'Gothic Fiction', '1818-01-01'),
(13, 'The Odyssey', 'Homer', 'Epic Poetry', '8th century BCE'),
(14, 'The Picture of Dorian Gray', 'Oscar Wilde', 'Gothic Fiction', '1890-07-01'),
(15, 'The Martian', 'Andy Weir', 'Science Fiction', '2011-09-27'),
(16, 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 'Non-Fiction', '2011-11-01'),
(17, 'The Alchemist', 'Paulo Coelho', 'Philosophical Fiction', '1988-01-01'),
(18, 'The Road', 'Cormac McCarthy', 'Post-Apocalyptic', '2006-09-26'),
(19, 'Jane Eyre', 'Charlotte Brontë', 'Gothic Fiction', '1847-10-16'),
(20, 'The Hunger Games', 'Suzanne Collins', 'Dystopian', '2008-09-14'),
(21, 'The Girl on the Train', 'Paula Hawkins', 'Thriller', '2015-01-13'),
(22, 'Educated', 'Tara Westover', 'Memoir', '2018-02-20'),
(23, 'Circe', 'Madeline Miller', 'Fantasy', '2018-04-10'),
(24, 'Where the Crawdads Sing', 'Delia Owens', 'Mystery', '2018-08-14'),
(25, 'The Silent Patient', 'Alex Michaelides', 'Psychological Thriller', '2019-02-05'),
(26, 'Normal People', 'Sally Rooney', 'Fiction', '2018-08-28'),
(27, 'Becoming', 'Michelle Obama', 'Memoir', '2018-11-13'),
(28, 'The Testaments', 'Margaret Atwood', 'Dystopian', '2019-09-10'),
(29, 'The Vanishing Half', 'Brit Bennett', 'Fiction', '2020-06-02'),
(30, 'The Midnight Library', 'Matt Haig', 'Fantasy', '2020-08-13'),
(31, 'Where the Forest Meets the Stars', 'Glendy Vanderah', 'Magical Realism', '2019-03-01'),
(32, 'The Giver of Stars', 'Jojo Moyes', 'Historical Fiction', '2019-10-08'),
(33, 'Such a Fun Age', 'Kiley Reid', 'Contemporary Fiction', '2019-12-31'),
(34, 'The Guest List', 'Lucy Foley', 'Mystery', '2020-02-20'),
(35, 'The Invisible Life of Addie LaRue', 'V.E. Schwab', 'Fantasy', '2020-10-06'),
(36, 'Project Hail Mary', 'Andy Weir', 'Science Fiction', '2021-05-04'),
(37, 'Klara and the Sun', 'Kazuo Ishiguro', 'Dystopian', '2021-03-02'),
(38, 'The Push', 'Ashley Audrain', 'Psychological Thriller', '2021-01-05'),
(39, 'The Four Winds', 'Kristin Hannah', 'Historical Fiction', '2021-02-02'),
(40, 'The Sanatorium', 'Sarah Pearse', 'Thriller', '2021-02-02');

INSERT INTO copies (copy_id, book_id, available) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 1),
(9, 9, 1),
(10, 10, 1),
(11, 11, 1),
(12, 12, 1),
(13, 13, 1),
(14, 14, 1),
(15, 15, 1),
(16, 16, 1),
(17, 17, 1),
(18, 18, 1),
(19, 19, 1),
(20, 20, 1),
(21, 21, 1),
(22, 22, 1),
(23, 23, 1),
(24, 24, 1),
(25, 25, 1),
(26, 26, 1),
(27, 27, 1),
(28, 28, 1),
(29, 29, 1),
(30, 30, 1),
(31, 31, 1),
(32, 32, 1),
(33, 33, 1),
(34, 34, 1),
(35, 35, 1),
(36, 36, 1),
(37, 37, 1),
(38, 38, 1),
(39, 39, 1),
(40, 40, 1),
(41, 1, 0),
(42, 2, 0),
(43, 3, 0),
(44, 4, 0),
(45, 5, 0);

