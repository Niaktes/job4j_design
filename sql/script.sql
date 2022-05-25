create table shooter
(
    id serial primary key,
    name varchar(255) NOT Null,
    launch_date date,
    pass_percentage decimal(5,2)
);

insert into shooter(name, launch_date, pass_percentage) values ('Borderlands 3', '2022-05-14', 24.71);

UPDATE shooter SET pass_percentage = 31.20 WHERE id = 1;

DELETE FROM shooter WHERE name = 'Borderlands 3';