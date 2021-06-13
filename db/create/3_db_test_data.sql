INSERT INTO users (username, first_name, last_name, email, password) VALUES ('mbconlon', 'Michael', 'Conlon', 'michael@conlon.io', 'testPass');
INSERT INTO users (username, first_name, last_name, email, password) VALUES ('admin', 'admin', 'turq', 'admin@turq.io', '$2a$10$Xl1EV9MgK.2Ufk/OtCixFebqVElptJaC1VUBAcpypSFQGdyr7AkvS');

INSERT INTO contests (id, title, end_date, rules, criteria, description, approved, author_id, status_id) values (1, 'test contest', '2021-05-11 19:50:11.000000', 'dd', '', '', 'true', 1, 0);
INSERT INTO contests (id, title, end_date, rules, criteria, description, approved, author_id, status_id) values (2, 'test contest', '2021-05-11 19:50:11.000000', 'dd', '', '', 'true', 2, 0);

INSERT INTO payments (id, amount, author_id, contest_id, status) values (1, 10000, 1, 2, 0);
INSERT INTO payments (id, amount, author_id, contest_id, status) values (2, 40000, 2, 2, 0);
