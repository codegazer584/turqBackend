CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY                  NOT NULL,
  username text                          ,
  first_name text                         ,
  last_name text                         ,
  email text UNIQUE                      NOT NULL,
  password text                          NOT NULL,
  admin BOOL DEFAULT 'false'             NOT NULL
);

CREATE TABLE IF NOT EXISTS contest_status (
  id SERIAL PRIMARY KEY                   NOT NULL,
  status TEXT                             NOT NULL
);

CREATE TABLE IF NOT EXISTS contests (
  id SERIAL PRIMARY KEY                  NOT NULL,
  title TEXT                             NOT NULL,
  end_date TIMESTAMP                     ,
  rules TEXT                             ,
  criteria TEXT                          ,
  description TEXT                       NOT NULL,
  approved BOOL DEFAULT 'false'          NOT NULL,
  author_id INT REFERENCES users (id)    NOT NULL,
  status_id INT DEFAULT 0 REFERENCES contest_status (id) NOT NULL
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

CREATE TABLE IF NOT EXISTS payment_status (
  id SERIAL PRIMARY KEY                  NOT NULL,
  status TEXT                            NOT NULL
);

INSERT INTO payment_status (id, status) VALUES (0, 'Intent Created');
INSERT INTO payment_status (id, status) VALUES (1, 'Processed');

CREATE TABLE IF NOT EXISTS payments (
  id SERIAL PRIMARY KEY                  NOT NULL,
  amount INT                             NOT NULL,
  author_id INT REFERENCES users (id)    NOT NULL,
  contest_id INT REFERENCES contests (id) ,
  status INT REFERENCES payment_status (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS legislation (
  id SERIAL PRIMARY KEY                  NOT NULL,
  title TEXT                             NOT NULL,
  chapter TEXT                           NOT NULL,
  section TEXT                           NOT NULL,
  accomplishes TEXT                      NOT NULL,
  terms TEXT                             NOT NULL,
  purpose TEXT                           NOT NULL,
  provisions TEXT                        NOT NULL,
  exceptions TEXT                              ,
  other TEXT                             ,
  contest_id INT REFERENCES contests (id) NOT NULL,
  author_id INT REFERENCES users (id)    NOT NULL
);
