create table users (
                       id      serial primary key,
                       name    varchar(50),
                       role_id int references role (id)
);

create table role (
                      id   serial primary key,
                      name varchar(30)
);

create table rules (
                       id   serial primary key,
                       name varchar(30)
);

create table role_rules (
                            id       serial primary key,
                            role_id  int references role (id),
                            rules_id int references rules (id)
);

create table requests (
                          id          serial primary key,
                          number      int,
                          users_id    int references users (id),
                          state_id    int references state (id),
                          category_id int references category (id)
);

create table comments (
                          id       serial primary key,
                          title    varchar(255),
                          word     text,
                          users_id int references users (id)
);

create table files (
                       id          serial primary key,
                       file        varchar(255),
                       requests_id int references requests (id)
);

create table category (
                          id   serial primary key,
                          name varchar(100)
);

create table state (
                       id   serial primary key,
                       name varchar(100)
);