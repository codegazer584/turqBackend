CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY                  NOT NULL,
  username text                        ,
  first_name text                        ,
  last_name text                         ,
  email text UNIQUE                      NOT NULL,
  password text                          NOT NULL
);

CREATE TABLE IF NOT EXISTS contests (
  id SERIAL PRIMARY KEY                  NOT NULL,
  title TEXT                             NOT NULL,
  end_date TIMESTAMP                     ,
  rules TEXT                             ,
  criteria TEXT                          ,
  description TEXT                       NOT NULL,
  approved BOOL DEFAULT 'false'          NOT NULL,
  author_id INT REFERENCES users (id)    NOT NULL
);

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
  contest_id INT REFERENCES contests (id) NOT NULL,
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
