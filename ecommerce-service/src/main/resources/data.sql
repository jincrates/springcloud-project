insert into member(name, email, password, role, status, created_at, created_by)
values ('진크라테스', 'user@email.com', '$2a$10$4mRiXr9xF6xdi6pjXDdyb.OtN17Qa39ui66iWR7sBZ2Y61kwmFw4u', 'USER',
        'ACTIVE', now(), 'anonymousUser');

insert into product(product_name, price, product_detail, status, created_at, created_by)
values ('소크라테스의 변명', 10000, '《소크라테스의 변명》은 인류 역사상 가장 위대한 철학자인 소크라테스의 영혼의 책이다.', 'SELLING', now(), 'anonymousUser');

insert into stock(product_id, quantity, created_at, created_by)
values (1, 100, now(), 'anonymousUser');