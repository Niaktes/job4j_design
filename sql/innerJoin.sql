/*
CREATE TABLE clans(
	id serial primary key,
	title varchar(255),
	at_war boolean
);

CREATE TABLE gamers(
	id serial primary key,
	full_name varchar(255),
	level integer,
	clan_id int references clans(id) 
);

INSERT INTO clans (title, at_war) values ('Maradeurs', false);
INSERT INTO clans (title, at_war) values ('Raiders', false);
INSERT INTO clans (title, at_war) values ('Peasants', true);

INSERT INTO gamers (full_name, level, clan_id) values ('Don Joe', 3, 1);
INSERT INTO gamers (full_name, level, clan_id) values ('Jabba The Hutt', 11, 2);
INSERT INTO gamers (full_name, level, clan_id) values ('Rwauraur', 55, 1);
INSERT INTO gamers (full_name, level, clan_id) values ('Boba Fett', 1, 2);
INSERT INTO gamers (full_name, level, clan_id) values ('Mandalorian', 30, 2);
INSERT INTO gamers (full_name, level, clan_id) values ('John', 79, 3);
INSERT INTO gamers (full_name, level, clan_id) values ('Alise', 54, 3);
INSERT INTO gamers (full_name, level, clan_id) values ('Margareth', 11, 3);
INSERT INTO gamers (full_name, level, clan_id) values ('Azzazzel', 53, 3);
INSERT INTO gamers (full_name, level, clan_id) values ('Hulgrin the Barbarian', 43, 3);
INSERT INTO gamers (full_name, level, clan_id) values ('Peter', 99, 1);
*/

SELECT g.full_name AS name, c.title AS clan_name
FROM gamers AS g
JOIN clans AS c
ON g.clan_id = c.id;

SELECT g.full_name AS name, g.level AS lvl, c.title
FROM gamers AS g
JOIN clans AS c
ON g.clan_id = c.id;

SELECT g.full_name AS name, c.at_war
FROM gamers AS g
JOIN clans AS c
ON g.clan_id = c.id