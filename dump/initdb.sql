CREATE DATABASE PismoFinanceDB;

USE PismoFinanceDB;

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
    `account_id` BIGINT NOT NULL AUTO_INCREMENT,
    `document_number` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`account_id`)
);

DROP TABLE IF EXISTS `operationsTypes`;
CREATE TABLE `operationsTypes` (
    `operationType_id` BIGINT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(100) NOT NULL,
    PRIMARY KEY(`operationType_id`)
);

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
    `transaction_id` BIGINT NOT NULL AUTO_INCREMENT,
    `account_id` BIGINT NOT NULL,
    `operationType_id` BIGINT NOT NULL,
    `amount` DECIMAL(10, 2) NOT NULL,
    `eventDate` DATETIME NOT NULL,
    PRIMARY KEY(`transaction_id`),
    FOREIGN KEY (`account_id`) REFERENCES accounts(`account_id`),
    FOREIGN KEY (`operationType_id`) REFERENCES operationsTypes(`operationType_id`)
);

COMMIT;