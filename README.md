# Pismo Transactions API

## How to Run

1. **Download source code with git clone from GitHub:**
    ```bash
    git clone https://github.com/marcofilho/pismo-transactions.git
    ```

2. **Go to the directory you cloned:**
    ```bash
    cd /pismo-transactions
    ```

3. **Start docker compose:**
    ```bash
    docker compose up --build
    ```

## API Specifications

- **BaseURL:**
    ```
    http://localhost:8080
    ```

## Endpoints

### Create an Account

- **Endpoint:**
    ```
    POST /accounts
    ```

- **Request Body:**
    ```json
    {
        "document_number": "12345678900"
    }
    ```

- **Response:**
    ```json
    {
        "account_id": 1,
        "document_number": "12345678900"
    }
    ```

### Get Account Information

- **Endpoint:**
    ```
    GET /accounts/:account_id
    ```

- **Response Body:**
    ```json
    {
        "account_id": 1,
        "document_number": "12345678900"
    }
    ```

### Create a Transaction

- **Endpoint:**
    ```
    POST /transactions
    ```

- **Request Body:**
    ```json
    {
        "account_id": 1,
        "operation_type_id": 1,
        "amount": 400.00
    }
    ```

- **Response Body:**
    ```json
    {
        "transaction_id": 1,
        "account_id": 1,
        "operation_type_id": 1,
        "amount": 400.00,
        "event_date": "2023-06-25T12:00:00Z"
    }
    ```
