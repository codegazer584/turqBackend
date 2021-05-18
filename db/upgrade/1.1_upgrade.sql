CREATE TABLE IF NOT EXISTS contest_status (
  id SERIAL PRIMARY KEY                   NOT NULL,
  status TEXT                             NOT NULL
);

INSERT INTO contest_status (id, status) VALUES (0, 'Fund Raising');
INSERT INTO contest_status (id, status) VALUES (1, 'Drafting in Progress');
INSERT INTO contest_status (id, status) VALUES (2, 'Submission in Progress');
INSERT INTO contest_status (id, status) VALUES (3, 'Receiving Bill Number');
INSERT INTO contest_status (id, status) VALUES (4, '1st Committee');
INSERT INTO contest_status (id, status) VALUES (5, '1st Chamber');
INSERT INTO contest_status (id, status) VALUES (6, '2nd Committee');
INSERT INTO contest_status (id, status) VALUES (7, '2nd Chamber');
INSERT INTO contest_status (id, status) VALUES (8, 'Executive');

ALTER TABLE users ADD COLUMN admin BOOL DEFAULT 'false' NOT NULL;

ALTER TABLE contests ADD COLUMN status_id INT DEFAULT 0 REFERENCES contest_status (id) NOT NULL;
