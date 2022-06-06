/*
CREATE TABLE type (
	id serial primary key,
	name varchar(255)
);

CREATE TABLE product (
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

INSERT INTO type (name) VALUES ('Сыр'), ('Колбасы'), ('Молоко'), ('Мясо'), ('Сухофрукты');

INSERT INTO product (name, type_id, expired_date, price) VALUES 
('Гауда', 1, date '2022-07-01', 350),
('Эмменталь', 1, date '2022-06-01', 289),
('ДорБлю', 1, date '2001-02-21', 999),
('Докторская', 2, date '2022-06-12', 300),
('Варено-копчёная', 2, date '2022-06-26', 390),
('Домик в деревне', 3, date '2022-06-21', 150),
('Простоквашино', 3, date '2022-06-01', 173),
('Свининка', 4, date '2022-06-11', 350),
('Говядинка', 4, date '2022-06-12', 420),
('Фу, баранина', 4, date '2022-06-13', 400),
('Курага', 5, date '2023-06-03', 300),
('Финики', 5, date '2023-06-03', 450);
*/

-- 1. Все продукты с типом "СЫР"
SELECT p.name AS cheeeese 
FROM product AS p 
WHERE type_id = (SELECT id FROM type WHERE name = 'Сыр');

-- 2. Все продукты, у кого в имени есть слово "мороженое"
SELECT p.name AS ice_cream 
FROM product AS p 
WHERE name LIKE '%мороженое%';

-- 3. Все продукты, срок годности которых уже истек
SELECT p.name AS date_expired 
FROM product AS p 
WHERE expired_date < CURRENT_DATE;

-- 4. Самый дорогой продукт
SELECT p.name AS most_expensive 
FROM product AS p 
WHERE p.price = (SELECT max(price) FROM product);

-- 5. Количество продуктов каждого типа
SELECT t.name as type, count(*) AS number 
FROM type AS t
JOIN product AS p
ON t.id = p.type_id
GROUP BY t.name;

-- 6. Все продукты с типом "СЫР" и "МОЛОКО"
SELECT p.name AS cheese_and_milk, t.name AS type
FROM product AS p
JOIN type AS t
ON p.type_id = t.id
WHERE t.name = 'Сыр' OR t.name = 'Молоко';

-- 7. Типы продуктов, которых меньше 3
SELECT t.name as type 
FROM type AS t
WHERE (SELECT count(*) FROM product WHERE type_id = t.id) <3;

-- 8. Все продукты и их тип
SELECT p.name AS product_name, type.name AS type_name 
FROM product AS p
JOIN type 
ON p.type_id = type.id;
