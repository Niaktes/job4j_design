-- INSERT INTO fauna (name, avg_age, discovery_date) VALUES
-- ('Felis silvestris catus', 14, date '1758-01-01'),
-- ('Platypus anatinus', 10, date '1799-01-01'),
-- ('Caerostris darwini', 3, date '2009-10-24'),
-- ('Balaenoptera musculus', 80, date '1758-01-01'),
-- ('Brookesia nana', 5, date '2021-01-01');

SELECT * FROM fauna WHERE name LIKE '%fish%';
SELECT * FROM fauna WHERE avg_age > 10000 AND avg_age < 21000;
SELECT * FROM fauna WHERE discovery_date IS null;
SELECT * FROM fauna WHERE discovery_date < '1950-01-01';