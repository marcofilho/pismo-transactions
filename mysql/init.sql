CREATE DATABASE IF NOT EXISTS PismoFinanceDB;

USE PismoFinanceDB;

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
                            `account_id` BIGINT NOT NULL AUTO_INCREMENT,
                            `document_number` VARCHAR(50) NOT NULL,
                            PRIMARY KEY (`account_id`)
);

DROP TABLE IF EXISTS `operations_types`;
CREATE TABLE `operations_types` (
                                    `operation_type_id` BIGINT NOT NULL,
                                    `description` VARCHAR(100) NOT NULL,
                                    PRIMARY KEY (`operation_type_id`)
);

INSERT INTO `operations_types` (`operation_type_id`, `description`)
VALUES
    (1, 'COMPRA A VISTA'),
    (2, 'COMPRA PARCELADA'),
    (3, 'SAQUE'),
    (4, 'PAGAMENTO');

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
                                `transaction_id` BIGINT NOT NULL AUTO_INCREMENT,
                                `account_id` BIGINT NOT NULL,
                                `operation_type_id` BIGINT NOT NULL,
                                `amount` DECIMAL(10, 2) NOT NULL,
                                `event_date` DATETIME NOT NULL,
                                PRIMARY KEY (`transaction_id`),
                                FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`),
                                FOREIGN KEY (`operation_type_id`) REFERENCES `operations_types` (`operation_type_id`)
);

COMMIT;
