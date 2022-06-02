SELECT g.full_name AS name, c.title AS clan_name
FROM gamers AS g
JOIN clans AS c
ON g.clan_id = c.id;

SELECT g.title, s.name AS server_name
FROM games AS g
JOIN servers AS s
ON g.server_id = s.id;

SELECT g.full_name AS name, c.title AS clan_name
FROM gamers AS g
JOIN clans AS c
ON c.id = (SELECT g.clan_id WHERE clan_id <= 1);