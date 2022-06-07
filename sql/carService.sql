/*
CREATE TABLE car_body(
	id serial primary key,
	model varchar(255),
	condition varchar(255)
);

CREATE TABLE engine(
	id serial primary key,
	model varchar(255),
	condition varchar(255)
);

CREATE TABLE transmission(
	id serial primary key,
	model varchar(255),
	condition varchar(255)
);

CREATE TABLE car(
	id serial primary key,
	owner varchar(255),
	body_id int references car_body(id) unique,
	engine_id int references engine(id) unique,
	transmission_id int references transmission(id) unique
);

INSERT INTO car_body (model, condition) VALUES
('Skoda Rapid 2014', 'good'),
('Lada Vesta Sport', 'broken'),
('Renault Logan 2021', 'good'),
('Hyundai Creta 2015', 'totaly broken'),
('Chery Tiggo 4 2018', 'good');

INSERT INTO engine (model, condition) VALUES
('Volkswagen sedan 1.6', 'good'),
('ВАЗ-21179-77', 'under repair'),
('K4M', 'good'),
('Hyundai Creta 2015 SUV GS', 'under repair'),
('SQRE4G15B', 'good'),
('2.5-liter four cylinder', 'good');

INSERT INTO transmission (model, condition) VALUES
('5-speed manual', 'good'),
('6-speed robot', 'under repair'),
('5-speed variator', 'under repair'),
('6-speed manual', 'good'),
('7-speed variator', 'under repair'),
('5-speed robot', 'good');

INSERT INTO car (owner, body_id, engine_id, transmission_id) VALUES
('Сергей', 1, 1, 1),
('Зураб', 2, 2, 3),
('Михаил', 3, 5, 2),
('Тот рыжий', 4, 4, 6),
('Неизвестен', 5, 3, 5);
*/

SELECT c.id, c.owner, cb.model, e.model, t.model 
FROM car AS c 
LEFT JOIN car_body AS cb ON c.body_id = cb.id
LEFT JOIN engine AS e ON c.engine_id = e.id
LEFT JOIN transmission AS t ON c.transmission_id = t.id;

SELECT cb.id, cb.model, cb.condition 
FROM car_body AS cb
LEFT JOIN car AS c 
ON cb.id = c.body_id
WHERE c.body_id IS null;

SELECT e.id, e.model, e.condition 
FROM engine AS e
LEFT JOIN car AS c 
ON e.id = c.engine_id
WHERE c.engine_id IS null;

SELECT t.id, t.model, t.condition 
FROM transmission AS t
LEFT JOIN car AS c 
ON t.id = c.transmission_id
WHERE c.transmission_id IS null