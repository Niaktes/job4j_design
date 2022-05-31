CREATE TABLE roles (
	id serial primary key,
	name varchar(255)
);

CREATE TABLE rules (
	id serial primary key,
	name varchar(255)
);

CREATE TABLE users (
	id serial primary key,
	name varchar(255),
	role_id int references roles(id)
);

CREATE TABLE categories (
	id serial primary key,
	name varchar(255)
);

CREATE TABLE states (
	id serial primary key,
	name varchar(255)
);

CREATE TABLE items (
	id serial primary key,
	name varchar(255),
	user_id int references users(id),
	category_id int references categories(id),
	state_id int references states(id)
);

CREATE TABLE comments (
	id serial primary key,
	content text,
	item_id int references items(id)
);

CREATE TABLE attachments (
	id serial primary key,
	item_id int references items(id)
);

CREATE TABLE roles_rules (
	id serial primary key,
	role_id int references roles(id),
	rule_id int references rules(id)
);
