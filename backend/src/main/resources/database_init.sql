drop table if exists spendings;

drop table if exists income;

drop table if exists savings;

drop table if exists accounts;

drop table if exists users;

-- Create users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    registered_at TIMESTAMP DEFAULT NOW(),
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    hashed_password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN,
    account_id INT
);

-- Create accounts table
CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    name VARCHAR(255),
    description TEXT,
    currency VARCHAR(3),
    actual_balance DECIMAL(10, 2),
    savings_balance DECIMAL(10, 2)
);

-- Create external trans table
CREATE TABLE external_transactions (
    id SERIAL PRIMARY KEY,
    account_id INT,
    user_id INT,
    category_name INT REFERENCES transaction_categories(id),
    description TEXT,
    date_of_transaction DATE,
    amount DECIMAL(10,2),
    is_planned BOOLEAN,
    is_recurring BOOLEAN,
    FOREIGN KEY (account_id) REFERENCES accounts(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create external trans table
CREATE TABLE local_transactions (
    id SERIAL PRIMARY KEY,
    account_id INT,
    user_id INT,
    description TEXT,
    date_of_transaction DATE,
    amount DECIMAL(10,2),
    is_planned BOOLEAN,
    is_recurring BOOLEAN,
    FOREIGN KEY (account_id) REFERENCES accounts(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE categories_users_join(
    category_id INT,
    user_id INT,
    PRIMARY KEY (category_id, user_id),
    FOREIGN KEY (category_id) REFERENCES transaction_categories(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
);

CREATE TABLE currencies(
    id SERIAL PRIMARY KEY,
    name VARCHAR(3)
);

CREATE TABLE roles(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

CREATE TABLE users_roles_join(
    role_id INT,
    user_id INT,
    PRIMARY KEY (role_id, user_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
);

INSERT INTO roles (name) VALUES('ROLE_USER');
INSERT INTO roles (name) VALUES('ROLE_ADMIN');

INSERT INTO transaction_categories (name) VALUES ('bills');
INSERT INTO transaction_categories (name) VALUES ('grocery shopping');
INSERT INTO transaction_categories (name) VALUES ('eating out');
INSERT INTO transaction_categories (name) VALUES ('dentist');

INSERT INTO currencies (name) VALUES ('huf');

INSERT INTO users_roles_join(role_id, user_id) VALUES (1, 1);


-- Insert dummy data into users table
-- INSERT INTO users (uuid, user_name, email, hashed_password, account_id, is_admin)
-- VALUES
--     (gen_random_uuid(), 'Teszt Elek', 'user1@example.com', 'niceHashedPwBro', 1, true),
--     (gen_random_uuid(), 'Pen Island', 'user2@example.com', 'niceHashedPwBro', 2, false);
--
-- -- Insert dummy data into accounts table
-- INSERT INTO accounts (user_id, uuid, name, description, currency, actual_balance, savings_balance)
-- VALUES
--     (1, gen_random_uuid(), 'Account 1', 'Main account', 'USD', 1000.00, 500.00),
--     (2, gen_random_uuid(), 'Account 2', 'Savings account', 'EUR', 2000.00, 1000.00);
--
-- INSERT INTO external_transactions(account_id, amount, id, category_name, dateOfTransaction, is_planned, is_recurring,
--                                   user_id)
-- VALUES (1, 100.0, 1, TO_DATE('01/11/2023', 'DD/MM/YYYY'), false, false, 1);
--
-- INSERT INTO transaction_categories(id, user_id, name)
-- VALUES (1, 1, '');
--
-- INSERT INTO categories_users_join(category_id, user_id)
-- VALUES(1, 1);
