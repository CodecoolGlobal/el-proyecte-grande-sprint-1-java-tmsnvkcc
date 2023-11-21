drop table if exists spendings;

drop table if exists income;

drop table if exists savings;

drop table if exists accounts;

drop table if exists users;

-- Create users table
CREATE TABLE users
(
    id              SERIAL PRIMARY KEY,
    uuid            UUID         NOT NULL,
    user_name       VARCHAR(255) NOT NULL,
    email           VARCHAR(255) UNIQUE,
    hashed_password VARCHAR(255) NOT NULL,
    account_id      INT,
    is_admin        BOOLEAN
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
    uuid VARCHAR(36) NOT NULL,
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
    uuid VARCHAR(36) NOT NULL,
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
    uuid VARCHAR(36) NOT NULL,
    description TEXT,
    dateOfTransaction DATE,
    amount DECIMAL(10,2),
    isPlanned BOOLEAN,
    isRecurring BOOLEAN,
    FOREIGN KEY (accountId) REFERENCES accounts(id),
    FOREIGN KEY (userId) REFERENCES users(id)
);

-- Insert dummy data into users table
-- INSERT INTO users (uuid, user_name, email, account_id, is_admin)
-- VALUES
--     ('abc123', 'user1', 'user1@example.com', 1, true),
--     ('def456', 'user2', 'user2@example.com', 2, false);

-- Insert dummy data into accounts table
-- INSERT INTO accounts (user_id, uuid, name, description, currency, actual_balance, savings_balance)
-- VALUES
--     (1, 'acc123', 'Account 1', 'Main account', 'USD', 1000.00, 500.00),
--     (2, 'acc456', 'Account 2', 'Savings account', 'EUR', 2000.00, 1000.00);

-- Insert dummy data into spendings table
-- INSERT INTO spendings (accountId, userId, uuid, categoryName, description, dateOfTransaction, amount, isPlanned, isRecurring)
-- VALUES
--     (1, 1, 'spend123', 'Groceries', 'Monthly grocery shopping', '2023-01-15', 15000.00, true, true),
--     (2, 2, 'spend456', 'Dining out', 'Weekly dinner with friends', '2023-01-10', 5000.00, false, false);

-- Insert dummy data into income table
-- INSERT INTO income (accountId, userId, uuid, categoryName, description, dateOfTransaction, amount)
-- VALUES
--     (1, 1, 'income123', 'Salary', 'Monthly salary', '2023-01-01', 30000.00),
--     (2, 2, 'income456', 'Freelance', 'Project payment', '2023-01-05', 5000.00);

-- Insert dummy data into savings table
-- INSERT INTO savings (accountId, userId, uuid, description, dateOfTransaction, amount)
-- VALUES
--     (1, 1, 'saving123', 'Emergency fund', '2023-01-20', 20000.00),
--     (2, 2, 'saving456', 'Vacation fund', '2023-01-15', 30000.00);
