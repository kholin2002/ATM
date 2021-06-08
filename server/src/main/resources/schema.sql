CREATE TABLE clients (
    id IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE accounts (
    id IDENTITY PRIMARY KEY,
    num CHAR(20) NOT NULL,
    balance DECIMAL(30, 4) DEFAULT 0 NOT NULL,
    currency CHAR(3) NOT NULL,
    client_id BIGINT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE cards (
    id IDENTITY PRIMARY KEY,
    num CHAR(16),
    expire DATE,
    cvc2 CHAR(3),
    account_id BIGINT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES accounts(id)
);