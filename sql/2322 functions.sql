/*
create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT INTO devices (name, price) VALUES
('Samsung Galaxy A23', 29490),
('Honor 50', 34990),
('Apple iPhone 13 Pro Max', 179990),
('Xiaomi Redmi 9C', 12990),
('Realme 8i', 18990),
('F+ F197', 890),
('teXet TM-404', 2590),
('Philips Xenium E255', 3290);

INSERT INTO people (name) VALUES
('Саша'),
('Ходор'),
('Аполлинария'),
('Ксюша'),
('Роман'),
('Фредерика'),
('Тамара'),
('Филип');

INSERT INTO devices_people (people_id, device_id) VALUES
(1, 1),
(2, 2), (2, 4),
(3, 3), (3, 8),
(4, 4), (4, 7), (4, 8),
(5, 5), (5, 4),
(6, 6), 
(7, 7), (7, 1),
(8, 8), (8, 7), (8, 6);
*/

SELECT avg(d.price) FROM devices AS d;

SELECT p.name, avg(d.price)
FROM people AS p 
JOIN devices_people AS dp ON p.id = dp.people_id
JOIN devices AS d ON dp.device_id = d.id
GROUP BY p.name;

SELECT p.name, avg(d.price)
FROM people AS p 
JOIN devices_people AS dp ON p.id = dp.people_id
JOIN devices AS d ON dp.device_id = d.id
GROUP BY p.name
HAVING avg(d.price) > 5000;