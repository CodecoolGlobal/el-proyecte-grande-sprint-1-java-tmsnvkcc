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
    category_name VARCHAR(255) NOT NULL,
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

-- Insert dummy data into users table
INSERT INTO users (uuid, user_name, email, hashed_password, account_id, is_admin)
VALUES
    (gen_random_uuid(), 'Teszt Elek', 'user1@example.com', 'niceHashedPwBro', 1, true),
    (gen_random_uuid(), 'Pen Island', 'user2@example.com', 'niceHashedPwBro', 2, false);

-- Insert dummy data into accounts table
INSERT INTO accounts (user_id, uuid, name, description, currency, actual_balance, savings_balance)
VALUES
    (1, gen_random_uuid(), 'Account 1', 'Main account', 'USD', 1000.00, 500.00),
    (2, gen_random_uuid(), 'Account 2', 'Savings account', 'EUR', 2000.00, 1000.00);

INSERT INTO external_transactions(account_id, amount, id, category_name, dateOfTransaction, is_planned, is_recurring,
                                  user_id)
VALUES (1, 100.0, 1, TO_DATE('01/11/2023', 'DD/MM/YYYY'), false, false, 1);
