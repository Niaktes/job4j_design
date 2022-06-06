/*
CREATE TABLE departments(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE employees(
    id serial primary key,
    name varchar(255),
	department_id int REFERENCES departments(id)
);

INSERT INTO departments (name) VALUES
('parks and recreations department'), 
('office department'), 
('clinic department'),
('friends department');

INSERT INTO employees (name, department_id) VALUES
('Leslie Knope', 1), ('Ron Swanson', 1), ('Andy Dwyer', 1), ('Ann Perkins', 1), ('April Ludgate', 1),
('Michael Scott', 2), ('Dwight Schrute', 2), ('Jim Halpert', 2), ('Pam Beesly', 2),
('Christopher Turk', 3), ('John Dorian', 3), ('Perry Cox', 3), ('Bob Kelso', 3), ('Elliot Reid', 3)
*/

SELECT d.name AS department, e.name FROM departments d JOIN employees e ON d.id = e.department_id;
SELECT d.name AS department, e.name FROM departments d LEFT JOIN employees e ON d.id = e.department_id;
SELECT d.name AS department, e.name FROM departments d RIGHT JOIN employees e ON d.id = e.department_id;
SELECT d.name AS department, e.name FROM departments d FULL JOIN employees e ON d.id = e.department_id;
SELECT d.name AS department, e.name FROM departments d CROSS JOIN employees e;

SELECT d.name AS department, e.name FROM departments d LEFT JOIN employees e ON d.id = e.department_id WHERE e.id IS null;

SELECT d.id, d.name, e.name FROM departments d LEFT JOIN employees e ON d.id = e.department_id;
SELECT d.id, d.name, e.name FROM employees e RIGHT JOIN departments d ON e.department_id = d.id;

/*
CREATE TABLE teens (
	id serial primary key,
	name varchar(255),
	sex varchar(1)
);

INSERT INTO teens (name, sex) VALUES
('Гена', 'm'),
('Лёха', 'm'),
('Эвелина', 'f'),
('Ипполит', 'm'),
('Регина', 'f'),
('Владимир', 'm'),
('Никифор', 'm'),
('Леся', 'f'),
('Бибигуль', 'f');
*/

SELECT t1.name AS male, t2.name AS female
FROM teens AS t1
CROSS JOIN teens AS t2
WHERE t1.sex NOT LIKE t2.sex AND t1.sex LIKE 'm'