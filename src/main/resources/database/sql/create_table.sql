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