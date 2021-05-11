INSERT INTO users (username, first_name, last_name, email, password) VALUES ('mbconlon', 'Michael', 'Conlon', 'michael@conlon.io', 'testPass');

INSERT INTO users (username, first_name, last_name, email, password, admin) VALUES ('Admin', 'Michael', 'Conlon', 'admin@admin.com', 'admin', true);

INSERT INTO contests (id, title, end_date, rules, criteria, description, approved, author_id, status_id) values (1, 'test contest', '2021-05-11 19:50:11.000000', 'dd', '', '', 'true', 1, 0);
