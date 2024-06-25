### How to run?

- Download source code with git clone from GitHub

        git clone https://github.com/marcofilho/pismo-transactions.git

- Go to directory you cloned.

    	cd /pismo-transactions

- Start docker compose.

    	docker compose up --build


### API Specifications

- BaseURL

        http://localhost:8080

- Create an account

  POST `/accounts`

  Request Body:

  	{
  		"document_number": "123456789"
  	}

  Response Body:

  	{
        "account_id": 1
        "document_number": "123456789"
    }


- Get account information

  GET `/accounts/:account_id`

  Response Body:

  	{
        "account_id": 1,
        "document_number": "12345678900"
  	} 


- Create a transaction

  POST `/transactions`

  Request Body:

  	{
        "account_id": 1,
        "operation_type_id": 1,
        "amount": 400.00
  	}

  Response:

  	{
        "transaction_id": 1,
        "account_id": 1,
        "operation_type_id": 1,
        "amount": 400.00,
        "event_date": 400.00,
  	}
