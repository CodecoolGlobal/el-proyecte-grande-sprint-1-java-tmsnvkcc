drop table if exists accounts cascade;
drop table if exists categories_users_join cascade;
drop table if exists currencies cascade;
drop table if exists external_transactions cascade;
drop table if exists local_transactions cascade;
drop table if exists roles cascade;
drop table if exists transaction_categories cascade;
drop table if exists users cascade;
drop table if exists users_roles_join cascade;

CREATE TABLE roles(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE transaction_categories(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE currencies(
    id SERIAL PRIMARY KEY,
    name VARCHAR(3)
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    registered_at TIMESTAMP DEFAULT NOW(),
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    hashed_password VARCHAR(255) NOT NULL
);

CREATE TABLE accounts(
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    name VARCHAR(255),
    description TEXT,
    currency_id INT REFERENCES currencies(id),
    actual_balance DECIMAL(10, 2),
    savings_balance DECIMAL(10, 2)
);

ALTER TABLE users
ADD COLUMN account_id INT REFERENCES accounts(id);

CREATE TABLE categories_users_join(
    category_id INT,
    user_id INT,
    PRIMARY KEY (category_id, user_id),
    FOREIGN KEY (category_id) REFERENCES transaction_categories(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE external_transactions(
    id SERIAL PRIMARY KEY,
    account_id INT REFERENCES accounts(id),
    user_id INT REFERENCES users(id),
    category_id INT REFERENCES transaction_categories(id),
    description TEXT,
    date_of_transaction DATE,
    amount DECIMAL(10, 2),
    is_planned BOOLEAN,
    is_recurring BOOLEAN
);

CREATE TABLE local_transactions(
    id SERIAL PRIMARY KEY,
    account_id INT REFERENCES accounts(id),
    user_id INT REFERENCES users(id),
    description TEXT,
    date_of_transaction DATE,
    amount DECIMAL(10, 2),
    is_planned BOOLEAN,
    is_recurring BOOLEAN
);

CREATE TABLE users_roles_join(
    role_id INT,
    user_id INT,
    PRIMARY KEY (role_id, user_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);


INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO transaction_categories (name) VALUES ('bills');
INSERT INTO transaction_categories (name) VALUES ('grocery shopping');
INSERT INTO transaction_categories (name) VALUES ('eating out');
INSERT INTO transaction_categories (name) VALUES ('dentist');

INSERT INTO currencies (name) VALUES ('huf');
INSERT INTO currencies (name) VALUES ('usd');
INSERT INTO currencies (name) VALUES ('eur');

INSERT INTO users(id, registered_at, user_name, email, hashed_password) VALUES (1, '10-01-2024', 'Test User', 'test@test.net', '$2a$10$c65vGIenkSrWDT/7WShHH.RMMTs2oxvlxT.f2IUYP2l6n4QOpe/jC');
INSERT INTO accounts(id, currency_id, savings_balance, actual_balance, description, name) VALUES (1, 1, 0, 0, 'Test account description', 'Test account');
UPDATE users SET account_id = 1 WHERE id = 1;
UPDATE accounts SET user_id = 1 WHERE id = 1;

INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(1, 1, 1, 1, 'test', '09-01-2024', 1500, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(2, 1, 1, 1, 'test', '09-01-2024', 2500, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(3, 1, 1, 1, 'test', '10-01-2024', 3500, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(4, 1, 1, 1, 'test', '10-01-2024', 100, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(5, 1, 1, 1, 'test', '10-01-2024', 500, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(6, 1, 1, 1, 'test', '11-01-2024', 11000, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(7, 1, 1, 1, 'test', '11-01-2024', 15000, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(8, 1, 1, 1, 'test', '11-01-2024', 12000, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(9, 1, 1, 1, 'test', '11-01-2024', 500, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(10, 1, 1, 1, 'test', '12-01-2024', 5000, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(11, 1, 1, 1, 'test', '12-01-2024', 4000, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(12, 1, 1, 1, 'test', '12-01-2024', 3500, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(13, 1, 1, 1, 'test', '12-01-2024', 100000, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(14, 1, 1, 1, 'test', '12-01-2024', 2340, false, false);
INSERT INTO external_transactions(id, account_id, user_id, category_id, description, date_of_transaction, amount, is_planned, is_recurring) VALUES(15, 1, 1, 1, 'test', '12-01-2024', 7800, false,  false);
