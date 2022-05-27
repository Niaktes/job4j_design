CREATE TABLE servers(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE games(
	id serial primary key,
	title varchar(255),
	server_id int references servers(id) unique
);

CREATE TABLE clans(
	id serial primary key,
	title varchar(255)
);

CREATE TABLE gamers(
	id serial primary key,
	full_name varchar(255),
	clan_id int references clans(id) 
);

CREATE TABLE games_gamers(
	id serial primary key,
	game_id int references games(id),
	gamer_id int references gamers(id)
);

INSERT INTO servers (name) values ('L2Derby');
INSERT INTO servers (name) values ('BorderLand');
INSERT INTO servers (name) values ('CSCommunity');

INSERT INTO games (title, server_id) values ('Lineage2', 1);
INSERT INTO games (title, server_id) values ('CS:GO', 3);
INSERT INTO games (title, server_id) values ('Bprderlands 3', 2);

INSERT INTO clans (title) values ('Maradeurs');
INSERT INTO clans (title) values ('Raiders');

INSERT INTO gamers (full_name, clan_id) values ('Don Joe', 1);
INSERT INTO gamers (full_name, clan_id) values ('Jabba The Hutt', 2);
INSERT INTO gamers (full_name, clan_id) values ('Rwauraur', 1);
INSERT INTO gamers (full_name, clan_id) values ('Boba Fett', 2);
INSERT INTO gamers (full_name, clan_id) values ('Mandalorian', 2);

INSERT INTO games_gamers (game_id, gamer_id) values (1, 1);
INSERT INTO games_gamers (game_id, gamer_id) values (2, 1);
INSERT INTO games_gamers (game_id, gamer_id) values (3, 1);
INSERT INTO games_gamers (game_id, gamer_id) values (2, 2);
INSERT INTO games_gamers (game_id, gamer_id) values (3, 2);
INSERT INTO games_gamers (game_id, gamer_id) values (2, 3);
INSERT INTO games_gamers (game_id, gamer_id) values (3, 3);
INSERT INTO games_gamers (game_id, gamer_id) values (1, 3);
INSERT INTO games_gamers (game_id, gamer_id) values (3, 4);
INSERT INTO games_gamers (game_id, gamer_id) values (2, 4);
INSERT INTO games_gamers (game_id, gamer_id) values (1, 4);
INSERT INTO games_gamers (game_id, gamer_id) values (3, 5);