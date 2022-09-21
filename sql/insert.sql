insert into role(name)
values ('Менеджер'),
       ('Кассир'),
       ('Инженер');

insert into users(name, role_id)
values ('Иван', 1),
       ('Кирилл', 1),
       ('Василий',3);

insert into rules(name)
values ('Продажа'),
       ('Администрирование'),
       ('Ремонт');

insert into role_rules(role_id, rules_id)
values (1, 1),
       (1, 2),
       (3, 3);

insert into comments(title, word, users_id)
values ('коммент1', 'текст коммент1', 1),
       ('коммент2', 'текст коммент2', 1),
       ('коммент3', 'текст коммент3', 2);

insert into category(name)
values ('Категория1'),
       ('Категория2'),
       ('Категория3');

insert into state(name)
values ('Статус1'),
       ('Статус2'),
       ('Статус3');

insert into requests(number, users_id, state_id, category_id)
values (100, 1, 2, 1),
       (100, 1, 3, 2),
       (100, 1, 1, 3);

insert into files(file, requests_id)
values ('file1.txt', 1),
       ('file2.txt', 3),
       ('file3.txt', 3);
