-- PRE-REQUISITES
-- Install PostgreSQL on your local system.

-- OTHER NOTES
-- DROP commands should be executed separately to avoid accidental deletions.

-- IMPORTANT: Execution of these 3 CREATE statements may first need to be done via command line ('psql') or other SQL DB tool.
CREATE DATABASE tranquil_magpie;
-- Main (production) schema
CREATE SCHEMA backend;
-- Dev / test schema
CREATE SCHEMA backend_dev;

-- IMPORTANT: Set 'search_path' to the relevant schema listed above.
SET search_path TO backend;

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
