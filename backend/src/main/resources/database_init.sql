drop table if exists spendings;

drop table if exists income;

drop table if exists savings;

drop table if exists accounts;

drop table if exists users;

-- Create users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    uuid UUID NOT NULL,
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
    uuid UUID NOT NULL,
    name VARCHAR(255),
    description TEXT,
    currency VARCHAR(3),
    actual_balance DECIMAL(10, 2),
    savings_balance DECIMAL(10, 2)
);

-- Create spendings table
CREATE TABLE spendings (
    id SERIAL PRIMARY KEY,
    accountId INT,
    userId INT,
    uuid UUID NOT NULL,
    categoryName VARCHAR(255) NOT NULL,
    description TEXT,
    dateOfTransaction DATE,
    amount DECIMAL(10,2),
    isPlanned BOOLEAN,
    isRecurring BOOLEAN,
    FOREIGN KEY (accountId) REFERENCES accounts(id),
    FOREIGN KEY (userId) REFERENCES users(id)
);

-- Create income table
CREATE TABLE income (
    id SERIAL PRIMARY KEY,
    accountId INT,
    userId INT,
    uuid UUID NOT NULL,
    categoryName VARCHAR(255) NOT NULL,
    description TEXT,
    dateOfTransaction DATE,
    amount DECIMAL(10,2),
    isPlanned BOOLEAN,
    isRecurring BOOLEAN,
    FOREIGN KEY (accountId) REFERENCES accounts(id),
    FOREIGN KEY (userId) REFERENCES users(id)
);

-- Create savings table
CREATE TABLE savings (
    id SERIAL PRIMARY KEY,
    accountId INT,
    userId INT,
    uuid UUID NOT NULL,
    description TEXT,
    dateOfTransaction DATE,
    amount DECIMAL(10,2),
    isPlanned BOOLEAN,
    isRecurring BOOLEAN,
    FOREIGN KEY (accountId) REFERENCES accounts(id),
    FOREIGN KEY (userId) REFERENCES users(id)
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

-- Insert dummy data into spendings table
INSERT INTO spendings (accountId, userId, uuid, categoryName, description, dateOfTransaction, amount, isPlanned, isRecurring)
VALUES
    (1, 1, gen_random_uuid(), 'Groceries', 'Monthly grocery shopping', '2023-01-15', 15000.00, true, true),
    (2, 2, gen_random_uuid(), 'Dining out', 'Weekly dinner with friends', '2023-01-10', 5000.00, false, false);
--
-- Insert dummy data into income table
INSERT INTO income (accountId, userId, uuid, categoryName, description, dateOfTransaction, amount)
VALUES
    (1, 1, gen_random_uuid(), 'Salary', 'Monthly salary', '2023-01-01', 30000.00),
    (2, 2, gen_random_uuid(), 'Freelance', 'Project payment', '2023-01-05', 5000.00);
--
-- Insert dummy data into savings table
INSERT INTO savings (accountId, userId, uuid, description, dateOfTransaction, amount)
VALUES
    (1, 1, gen_random_uuid(), 'Emergency fund', '2023-01-20', 20000.00),
    (2, 2, gen_random_uuid(), 'Vacation fund', '2023-01-15', 30000.00);
