INSERT INTO roles (name) VALUES ('admin'), ('user');

INSERT INTO rules (name) VALUES 
('create new item'), 
('delete item'), 
('update item'), 
('create category'), 
('delete category'), 
('update category'), 
('create state'), 
('delete state'),
('update state'),
('add comment'),
('add attachment'),
('delete attachment');

INSERT INTO roles_rules(role_id, rule_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12),
(2, 1), (2, 3), (2, 10), (2, 11), (2, 12);

INSERT INTO users (name, role_id) VALUES
('adam', 1),
('tester', 2);

INSERT INTO categories (name) VALUES 
('simple_request'), 
('test_category');

INSERT INTO states (name) VALUES
('new'),
('in process'),
('done'),
('failed');

INSERT INTO items (name, user_id, category_id, state_id) VALUES
('test_request', 2, 2, 1);

INSERT INTO comments (content, item_id) VALUES
('some test comment', 1);

INSERT INTO attachments (item_id) VALUES
(1);