SELECT 1;

insert into members(name, email, password, bio, image_url, role, status, created_at)
values ('사용자', 'user@email.com', '$2a$10$4mRiXr9xF6xdi6pjXDdyb.OtN17Qa39ui66iWR7sBZ2Y61kwmFw4u', '한줄 소개입니다.', null,
        'USER', 'ACTIVE', now());
--
-- insert into product(name, price, description, status, created_at, created_by)
-- values ('소크라테스의 변명', 10000, '《소크라테스의 변명》은 인류 역사상 가장 위대한 철학자인 소크라테스의 영혼의 책이다.', 'SELLING', now(), 'anonymousUser');
--
-- insert into stock(product_id, quantity, created_at, created_by, version)
-- values (1, 100, now(), 'anonymousUser', 0);