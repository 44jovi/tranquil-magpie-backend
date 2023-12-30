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
	'$2a$10$6gUOXDNJq17yTAyHEwRp/uYO6201uAUChmw6k5F83XEdUzU5cTBC6',
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
    '634a1a41-d1a6-403f-9080-ff6ae8b7754a',
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
    '8a489f1c-d79b-4ae2-ac6c-5d3e8d47bb7e',
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
    '08ad597a-29a0-4c37-a47f-8db7201a6493',
    'A most useful descriptive description.',
    'example-product-3.jpg',
    'Example Product 3',
    9.99,
    100)
;

-- 'shop_order'
insert into shop_order (
    "id",
    order_date_time,
    order_status,
    order_total,
    payment_method,
    shipping_address,
    stripe_checkout_session_id,
    stripe_payment_intent_id,
    user_id)
values (
    '1a0e6074-d0b9-44b6-9a2d-3bbd1101fe9b',
    '2023-12-29T21:52:58.811545Z',
    'PAID_AWAITING_SHIPMENT',
    40.94,
    '[card]',
    '{"id":"28836c68-d464-4c11-ae35-32da5127ccff","userId":"2e53a105-09a4-4e9a-8862-f16558547cc7","line_1":"1 Barry Street","city":"Tranquilton","postcode":"ABC1D23"}',
    'placeholder_stripe_checkout_session_id',
    'placeholder_stripe_payment_intent_id',
    '2e53a105-09a4-4e9a-8862-f16558547cc7')
;

-- shop_order_item
insert into shop_order_item (
    product_id,
    shop_order_id,
    price_total,
    product_name,
    product_price,
    qty)
values (
    '634a1a41-d1a6-403f-9080-ff6ae8b7754a',
    '1a0e6074-d0b9-44b6-9a2d-3bbd1101fe9b',
    0.99,
    'Example Product 1',
    0.99,
    1)
;

insert into shop_order_item (
    product_id,
    shop_order_id,
    price_total,
    product_name,
    product_price,
    qty)
values (
    '8a489f1c-d79b-4ae2-ac6c-5d3e8d47bb7e',
    '1a0e6074-d0b9-44b6-9a2d-3bbd1101fe9b',
    9.98,
    'Example Product 2',
    4.99,
    2)
;

insert into shop_order_item (
    product_id,
    shop_order_id,
    price_total,
    product_name,
    product_price,
    qty)
values (
    '08ad597a-29a0-4c37-a47f-8db7201a6493',
    '1a0e6074-d0b9-44b6-9a2d-3bbd1101fe9b',
    29.97,
    'Example Product 3',
    9.99,
    3)
;
