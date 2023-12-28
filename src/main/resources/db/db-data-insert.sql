-- IMPORTANT: Set 'search_path' to the relevant schema
SET search_path TO backend;

-- 'user'
-- TODO: refactor 'users' to 'user'
insert into users (
    id,
    email,
    username,
    given_name,
    family_name,
    dob,
    password,
    role)
values (
	'2e53a105-09a4-4e9a-8862-f16558547cc7',
	'barry1@example.com',
	'barry1',
	'barry',
	'castle',
	'1903-02-01',
	'not-yet-hashed-password',
	'USER')
;

-- 'user_address'
insert into user_address (
    "id",
    city,
    line_1,
    postcode,
    user_id)
values (
    '28836c68-d464-4c11-ae35-32da5127ccff',
    'Tranquilton',
    '1 Barry Street',
    'ABC1D23',
    '2e53a105-09a4-4e9a-8862-f16558547cc7')
;

-- 'product'
insert into product (
    "id",
    description,
    image_filename,
    "name",
    price,
    stock_qty)
values (
    'e7cb0665-e3b8-4e8f-a57e-22bc55c04196',
    'A most useful descriptive description.',
    'example-product-1.jpg',
    'Example Product 1',
    0.99,
    100)
;

insert into product (
    "id",
    description,
    image_filename,
    "name",
    price,
    stock_qty)
values (
    'f6455eab-3f06-433d-838e-e563b357f9ed',
    'A most useful descriptive description.',
    'example-product-2.jpg',
    'Example Product 2',
    4.99,
    100)
;

insert into product (
    "id",
    description,
    image_filename,
    "name",
    price,
    stock_qty)
values (
    'fd819047-c637-4538-b65a-9fc02eb758e4',
    'A most useful descriptive description.',
    'example-product-3.jpg',
    'Example Product 3',
    9.99,
    100)
;
