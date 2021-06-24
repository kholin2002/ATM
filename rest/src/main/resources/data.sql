INSERT INTO clients (name)
VALUES
      ('IVANOV IVAN')
    , ('PETROV PETR')
    ;

INSERT INTO accounts (client_id, num, balance, currency)
VALUES
      (1, '12345678901234567890', 100, 'RUB')
    , (2, '10001000100010001000', 100000, 'RUB')
    , (2, '22222222222222222222', 50, 'USD')
    , (2, '00000000000000000003', 700, 'JPY')
    ;

INSERT INTO cards(account_id, num, expire, cvc2)
VALUES
      (2, '1234567800001234', '2022-12-01', '123')
    , (4, '0000000000004321', '2025-01-01', '777')
    ;