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


