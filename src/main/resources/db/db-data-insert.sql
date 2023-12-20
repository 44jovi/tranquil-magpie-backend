-- IMPORTANT: Set 'search_path' to the relevant schema
SET search_path TO backend;

-- TODO: refactor 'users' to 'user'
insert into users (
    id,	email, username, given_name, family_name, dob, password, role
    )
	values (
	'2e53a105-09a4-4e9a-8862-f16558547cc7',
	'barry1@example.com',
	'barry1',
	'barry',
	'castle',
	'1903-02-01',
	'not-yet-hashed-password',
	'USER'
	);
