insert into payment(user_id, price, created_at, updated_at)
values (1, 10000, now(), now()),
       (1, 20000, now(), now()),
       (1, 30000, now(), now()),
       (1, 40000, now(), now()),
       (1, 50000, now(), now()),
       (1, 60000, now(), now()),
       (1, 70000, now(), now()),
       (1, 80000, now(), now()),
       (1, 90000, now(), now()),
       (1, 100000, now(), now());

insert into product(name, price, stock, created_at, updated_at)
values ('상품1', 1000, 999, now(), now()),
       ('상품2', 1000, 999, now(), now()),
       ('상품3', 1000, 999, now(), now()),
       ('상품4', 1000, 999, now(), now()),
       ('상품5', 1000, 999, now(), now()),
       ('상품6', 1000, 999, now(), now()),
       ('상품7', 1000, 999, now(), now());

insert into orders(user_id, payment_id, created_at, updated_at)
values (1, 1, now(), now()),
       (1, 2, now(), now()),
       (1, 3, now(), now()),
       (1, 4, now(), now()),
       (1, 5, now(), now()),
       (1, 6, now(), now()),
       (1, 7, now(), now()),
       (1, 8, now(), now()),
       (1, 9, now(), now()),
       (1, 10, now(), now());

insert into order_product(order_id, product_id, quantity, price, created_at, updated_at)
values (1, 1, 10, 10000, now(), now()),
       (1, 2, 20, 20000, now(), now()),
       (1, 3, 30, 30000, now(), now()),
       (1, 4, 40, 40000, now(), now()),
       (1, 5, 50, 50000, now(), now()),
       (1, 6, 60, 60000, now(), now()),
       (1, 7, 70, 70000, now(), now()),

       (2, 1, 10, 10000, now(), now()),
       (2, 2, 20, 20000, now(), now()),
       (2, 3, 30, 30000, now(), now()),
       (2, 4, 40, 40000, now(), now()),
       (2, 5, 50, 50000, now(), now()),
       (2, 6, 60, 60000, now(), now()),
       (2, 7, 70, 70000, now(), now()),

       (3, 1, 10, 10000, now(), now()),
       (3, 2, 20, 20000, now(), now()),
       (3, 3, 30, 30000, now(), now()),
       (3, 4, 40, 40000, now(), now()),
       (3, 5, 50, 50000, now(), now()),
       (3, 6, 60, 60000, now(), now()),
       (3, 7, 70, 70000, now(), now()),

       (4, 1, 10, 10000, now(), now()),
       (4, 2, 20, 20000, now(), now()),
       (4, 3, 30, 30000, now(), now()),
       (4, 4, 40, 40000, now(), now()),
       (4, 5, 50, 50000, now(), now()),
       (4, 6, 60, 60000, now(), now()),
       (4, 7, 70, 70000, now(), now()),

       (5, 1, 10, 10000, now(), now()),
       (5, 2, 20, 20000, now(), now()),
       (5, 3, 30, 30000, now(), now()),
       (5, 4, 40, 40000, now(), now()),
       (5, 5, 50, 50000, now(), now()),
       (5, 6, 60, 60000, now(), now()),
       (5, 7, 70, 70000, now(), now()),

       (6, 1, 10, 10000, now(), now()),
       (6, 2, 20, 20000, now(), now()),
       (6, 3, 30, 30000, now(), now()),
       (6, 4, 40, 40000, now(), now()),
       (6, 5, 50, 50000, now(), now()),
       (6, 6, 60, 60000, now(), now()),
       (6, 7, 70, 70000, now(), now()),

       (7, 1, 10, 10000, now(), now()),
       (7, 2, 20, 20000, now(), now()),
       (7, 3, 30, 30000, now(), now()),
       (7, 4, 40, 40000, now(), now()),
       (7, 5, 50, 50000, now(), now()),
       (7, 6, 60, 60000, now(), now()),
       (7, 7, 70, 70000, now(), now());


-- SELECT O.id, O.user_id, OP.quantity, OP.price, P.name
-- FROM ORDERS AS O
-- INNER JOIN ORDER_PRODUCT AS OP ON O.id = OP.order_id
-- INNER JOIN PRODUCT AS P ON OP.product_id = P.id;
