/* Create tables for the database */

/* Create table for the users with user_id as primary key */
CREATE TABLE IF NOT EXISTS `users`
(
    user_id INTERGER PRIMARY KEY,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    user_type TEXT
);

/* Create table for the products with product_id as primary key */
CREATE TABLE IF NOT EXISTS `books`
(
    book_id      INTERGER PRIMARY KEY,
    title        TEXT NOT NULL,
    author       TEXT NOT NULL,
    genre        TEXT NOT NULL,
    publish_date TEXT NOT NULL
);

/* Create table for the copies of the books with copy_id as primary key */
CREATE TABLE IF NOT EXISTS `copies`
(
    copy_id      INTERGER PRIMARY KEY,
    book_id      INTERGER NOT NULL,
    available    BOOLEAN NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

/* Create table for the orders with order_id as primary key */
CREATE TABLE IF NOT EXISTS `orders`
(
    order_id     INTERGER PRIMARY KEY,
    user_id      INTERGER NOT NULL,
    copy_id      INTERGER NOT NULL,
    order_date   TEXT NOT NULL,
    order_period TEXT NOT NULL,
    order_status TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (copy_id) REFERENCES copies(copy_id)
);

/* Create table for the transactions with transaction_id as primary key */
CREATE TABLE IF NOT EXISTS `transactions`
(
    transaction_id INTERGER PRIMARY KEY,
    order_id       INTERGER NOT NULL,
    user_id        INTERGER NOT NULL,
    copy_id        INTERGER NOT NULL,
    transaction_date TEXT NOT NULL,
    transaction_status TEXT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (copy_id) REFERENCES copies(copy_id)
);

/* Create table for the fines with fine_id as primary key */
CREATE TABLE IF NOT EXISTS `fines`
(
    fine_id       INTERGER PRIMARY KEY,
    transaction_id INTERGER NOT NULL,
    user_id        INTERGER NOT NULL,
    copy_id        INTERGER NOT NULL,
    fine_amount    INTERGER NOT NULL,
    fine_status    TEXT NOT NULL,
    FOREIGN KEY (transaction_id) REFERENCES transactions(transaction_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (copy_id) REFERENCES copies(copy_id)
);

