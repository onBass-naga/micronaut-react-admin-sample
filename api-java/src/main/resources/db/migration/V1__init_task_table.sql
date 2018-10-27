create table tasks (
  id integer not null,
  deadline timestamp not null,
  is_done boolean not null,
  overview varchar(255) not null,
  primary key (id)
);


INSERT INTO tasks (id, overview, deadline, is_done) VALUES (1, 'Lean Micronaut', '2018-11-27 03:25:15.008000000', false);
INSERT INTO tasks (id, overview, deadline, is_done) VALUES (2, 'Lean react-admin', '2018-12-27 03:25:15.008000000', false);
