CREATE DATABASE PismoFinanceDB;

USE PismoFinanceDB;

CREATE TABLE IF NOT EXISTS Accounts (
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    document_number VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS OperationsTypes (
    operationType_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Transactions (
    transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    operationType_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    eventDate DATETIME NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Accounts(account_id),
    FOREIGN KEY (operationType_id) REFERENCES OperationsTypes(operationType_id)
);

COMMIT;