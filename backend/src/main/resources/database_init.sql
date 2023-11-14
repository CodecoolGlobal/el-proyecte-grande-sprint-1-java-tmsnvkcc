DROP TABLE IF EXISTS savings;
DROP TABLE IF EXISTS income;
DROP TABLE IF EXISTS planned_spendings;
DROP TABLE IF EXISTS spendings;
DROP TABLE IF EXISTS users;


-- Create user table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    uuid UUID NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    isAdmin BOOLEAN NOT NULL
);

-- Create spendings table
CREATE TABLE IF NOT EXISTS spendings (
    id SERIAL PRIMARY KEY,
    userId INTEGER REFERENCES users(id),
    category VARCHAR(60),
    description VARCHAR(255),
    uuid UUID NOT NULL,
    transaction_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL
);

-- Create planned_spendings table
CREATE TABLE IF NOT EXISTS planned_spendings (
    id SERIAL PRIMARY KEY,
    userId INTEGER REFERENCES users(id),
    category VARCHAR(60),
    description VARCHAR(255),
    uuid UUID NOT NULL,
    transaction_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL
);

-- Create income table
CREATE TABLE IF NOT EXISTS income (
    id SERIAL PRIMARY KEY,
    userId INTEGER REFERENCES users(id),
    uuid UUID NOT NULL,
    category VARCHAR(60),
    description VARCHAR(255),
    transaction_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL
);

-- Create savings table
CREATE TABLE IF NOT EXISTS savings (
    id SERIAL PRIMARY KEY,
    userId INTEGER REFERENCES users(id),
    uuid UUID NOT NULL,
    transaction_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL
);

-- Dummy data for user table
INSERT INTO users (uuid, username, email, isAdmin)
VALUES
    ('123e4567-e89b-12d3-a456-426614174001', 'user1', 'user1@example.com', false),
    ('223e4567-e89b-12d3-a456-426614174002', 'user2', 'user2@example.com', true),
    ('323e4567-e89b-12d3-a456-426614174003', 'user3', 'user3@example.com', false);
-- Dummy data for spendings table
INSERT INTO spendings (userId, category, description, uuid, transaction_date, amount)
VALUES
    (1, 'Groceries', 'Weekly grocery shopping', '111e4567-e89b-12d3-a456-426614174001', '2023-11-01', 25000.00),
    (1, 'Entertainment', 'Movie night', '111e4567-e89b-12d3-a456-426614174002', '2023-11-05', 20000.00),
    (1, 'Clothing', 'Shopping spree', '111e4567-e89b-12d3-a456-426614174003', '2023-11-10', 10000.00),
    (1, 'Dining', 'Dinner with friends', '111e4567-e89b-12d3-a456-426614174004', '2023-11-15', 30000.00),
    (1, 'Electronics', 'Gadget purchase', '111e4567-e89b-12d3-a456-426614174005', '2023-11-20', 80000.00),
    (2, 'Groceries', 'Monthly shopping', '222e4567-e89b-12d3-a456-426614174001', '2023-11-02', 70000.00),
    (2, 'Dining', 'Lunch at a cafe', '222e4567-e89b-12d3-a456-426614174002', '2023-11-05', 6500.00),
    (2, 'Shopping', 'Online shopping', '222e4567-e89b-12d3-a456-426614174003', '2023-11-10', 18000.00),
    (2, 'Utilities', 'Monthly bills', '222e4567-e89b-12d3-a456-426614174004', '2023-11-15', 45000.00),
    (2, 'Entertainment', 'Concert tickets', '222e4567-e89b-12d3-a456-426614174005', '2023-11-20', 16000.00),
    (3, 'Dining', 'Dinner with family', '333e4567-e89b-12d3-a456-426614174001', '2023-11-02', 40000.00),
    (3, 'Travel', 'Weekend getaway', '333e4567-e89b-12d3-a456-426614174002', '2023-11-05', 200000.00),
    (3, 'Health', 'Gym membership', '333e4567-e89b-12d3-a456-426614174003', '2023-11-10', 18000.00),
    (3, 'Clothing', 'Seasonal wardrobe update', '333e4567-e89b-12d3-a456-426614174004', '2023-11-15', 65000.00),
    (3, 'Utilities', 'Internet bill', '333e4567-e89b-12d3-a456-426614174005', '2023-11-20', 8000.00);

-- Dummy data for planned_spendings table
INSERT INTO planned_spendings (userId, category, description, uuid, transaction_date, amount)
VALUES
    (1, 'Shopping', 'Clothing sale', '111e4567-e89b-12d3-a456-426614174006', '2023-11-01', 10000.00),
    (1, 'Travel', 'Vacation expenses', '111e4567-e89b-12d3-a456-426614174007', '2023-11-05', 50000.00),
    (1, 'Electronics', 'Gadget pre-order', '111e4567-e89b-12d3-a456-426614174008', '2023-11-10', 30000.00),
    (1, 'Education', 'Online course subscription', '111e4567-e89b-12d3-a456-426614174009', '2023-11-15', 5000.00),
    (1, 'Health', 'Fitness equipment', '111e4567-e89b-12d3-a456-426614174010', '2023-11-20', 8000.00),
    (2, 'Utilities', 'Electricity bill', '222e4567-e89b-12d3-a456-426614174006', '2023-11-01', 26500.00),
    (2, 'Travel', 'Weekend road trip', '222e4567-e89b-12d3-a456-426614174007', '2023-11-05', 38000.00),
    (2, 'Health', 'Dental checkup', '222e4567-e89b-12d3-a456-426614174008', '2023-11-10', 28000.00),
    (2, 'Shopping', 'Furniture purchase', '222e4567-e89b-12d3-a456-426614174009', '2023-11-15', 35000.00),
    (2, 'Entertainment', 'Concert tickets', '222e4567-e89b-12d3-a456-426614174010', '2023-11-20', 25000.00),
    (3, 'Travel', 'Family vacation fund', '333e4567-e89b-12d3-a456-426614174006', '2023-11-01', 30000.00),
    (3, 'Education', 'Language learning course', '333e4567-e89b-12d3-a456-426614174007', '2023-11-05', 10000.00),
    (3, 'Shopping', 'Electronics sale', '333e4567-e89b-12d3-a456-426614174008', '2023-11-10', 12000.00),
    (3, 'Health', 'Fitness class subscription', '333e4567-e89b-12d3-a456-426614174009', '2023-11-15', 19000.00),
    (3, 'Utilities', 'Water bill', '333e4567-e89b-12d3-a456-426614174010', '2023-11-20', 7000.00);

-- Dummy data for income table
INSERT INTO income (userId, uuid, category, description, transaction_date, amount)
VALUES
    (1, '111e4567-e89b-12d3-a456-426614174011', 'Salary', 'Monthly salary', '2023-11-01', 300000.00),
    (1, '111e4567-e89b-12d3-a456-426614174012', 'Bonus', 'Year-end bonus', '2023-11-05', 10000.00),
    (1, '111e4567-e89b-12d3-a456-426614174013', 'Investment', 'Stock dividends', '2023-11-10', 20000.00),
    (1, '111e4567-e89b-12d3-a456-426614174014', 'Freelance', 'Side project payment', '2023-11-15', 50000.00),
    (1, '111e4567-e89b-12d3-a456-426614174015', 'Rent', 'Rental income', '2023-11-20', 12000.00),
    (2, '222e4567-e89b-12d3-a456-426614174011', 'Salary', 'Monthly salary', '2023-11-01', 350000.00),
    (2, '222e4567-e89b-12d3-a456-426614174012', 'Bonus', 'Performance bonus', '2023-11-05', 15000.00),
    (2, '222e4567-e89b-12d3-a456-426614174013', 'Investment', 'Dividend income', '2023-11-10', 25000.00),
    (2, '222e4567-e89b-12d3-a456-426614174014', 'Freelance', 'Consulting fee', '2023-11-15', 8000.00),
    (2, '222e4567-e89b-12d3-a456-426614174015', 'Rent', 'Property rent', '2023-11-20', 18000.00),
    (3, '333e4567-e89b-12d3-a456-426614174011', 'Salary', 'Monthly salary', '2023-11-01', 250000.00),
    (3, '333e4567-e89b-12d3-a456-426614174012', 'Bonus', 'Quarterly bonus', '2023-11-05', 8000.00),
    (3, '333e4567-e89b-12d3-a456-426614174013', 'Investment', 'Interest income', '2023-11-10', 10000.00),
    (3, '333e4567-e89b-12d3-a456-426614174014', 'Freelance', 'Project payment', '2023-11-15', 40000.00),
    (3, '333e4567-e89b-12d3-a456-426614174015', 'Rent', 'Apartment rental', '2023-11-20', 15000.00);

-- Dummy data for savings table
INSERT INTO savings (userId, uuid, transaction_date, amount)
VALUES
    (1, '111e4567-e89b-12d3-a456-426614174016', '2023-11-01', 5000.00),
    (1, '111e4567-e89b-12d3-a456-426614174017', '2023-11-05', 10000.00),
    (1, '111e4567-e89b-12d3-a456-426614174018', '2023-11-10', 2000.00),
    (1, '111e4567-e89b-12d3-a456-426614174019', '2023-11-15', 8000.00),
    (1, '111e4567-e89b-12d3-a456-426614174020', '2023-11-20', 3000.00),
    (2, '222e4567-e89b-12d3-a456-426614174016', '2023-11-01', 7000.00),
    (2, '222e4567-e89b-12d3-a456-426614174017', '2023-11-05', 12000.00),
    (2, '222e4567-e89b-12d3-a456-426614174018', '2023-11-10', 3000.00),
    (2, '222e4567-e89b-12d3-a456-426614174019', '2023-11-15', 4000.00),
    (2, '222e4567-e89b-12d3-a456-426614174020', '2023-11-20', 6000.00),
    (3, '333e4567-e89b-12d3-a456-426614174016', '2023-11-01', 4000.00),
    (3, '333e4567-e89b-12d3-a456-426614174017', '2023-11-05', 8000.00),
    (3, '333e4567-e89b-12d3-a456-426614174018', '2023-11-10', 2000.00),
    (3, '333e4567-e89b-12d3-a456-426614174019', '2023-11-15', 3000.00),
    (3, '333e4567-e89b-12d3-a456-426614174020', '2023-11-20', 5000.00);

