-- PRE-REQUISITES
-- Install PostgreSQL on your local system.

-- OTHER NOTES
-- DROP commands should be executed separately to avoid accidental deletions.

-- NOTE: These CREATE statements may first need to be executed via command line ('psql') or other SQL DB tool.
CREATE DATABASE tranquil_magpie;
-- Main (production) schema
CREATE SCHEMA backend;
-- Dev / test schema
CREATE SCHEMA backend_dev;

-- IMPORTANT: Set 'search_path' to the relevant schema listed above.
SET search_path TO backend;

-- TODO: refactor 'users' to 'user'
CREATE TABLE IF NOT EXISTS users
(
    id uuid NOT NULL,
    email character varying(254) COLLATE pg_catalog."default" NOT NULL,
    username character varying(20) COLLATE pg_catalog."default" NOT NULL,
    given_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    family_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    dob date NOT NULL,
    password character(60) COLLATE pg_catalog."C" NOT NULL,
    role character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT email_unique UNIQUE (email),
    CONSTRAINT username_unique UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS user_address
(
    id uuid NOT NULL,
    user_id uuid,
    line_1 character varying(100) COLLATE pg_catalog."default",
    city character varying(50) COLLATE pg_catalog."default",
    postcode character varying(7) COLLATE pg_catalog."default",
    CONSTRAINT user_address_pkey PRIMARY KEY (id),
    CONSTRAINT users_id_fkey FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE IF NOT EXISTS shop_order
(
    id uuid NOT NULL,
    user_id uuid,
    order_date_time timestamp with time zone,
    order_total numeric,
    order_status character varying(100) COLLATE pg_catalog."default",
    payment_method character varying(50) COLLATE pg_catalog."default",
    shipping_address character varying(300) COLLATE pg_catalog."default",
    stripe_checkout_session_id character varying(200) COLLATE pg_catalog."default",
    stripe_payment_intent_id character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT shop_order_pkey PRIMARY KEY (id),
    CONSTRAINT users_id_fkey FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
