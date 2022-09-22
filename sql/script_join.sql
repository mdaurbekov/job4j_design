create table person
(
    id     serial primary key,
    name   varchar(30),
    age    int
);


create table car
(
    id   serial primary key,
    name varchar(30),
    year date,
    person_id int references person(id)
);

insert into person(name, age)
values ('Ivan', 35),
       ('Maksim', 38),
       ('Oleg', 34),
       ('Kirill', 30),
       ('Stepan', 46);

insert into car(name, year, person_id)
values ('Ford', date '1997-01-01', 1),
       ('Nissan', date '1995-01-01', 1),
       ('BMW', date '1990-01-01', 4),
       ('Mazda', date '2000-01-01', 3),
       ('Mercedes', date '1999-01-01', 2);




select p.name, c.name, to_char(c.year:: date, 'YYYY')
from person as p
         join car c on c.person_id = p.id;

select p.name
from person as p
         join car c on c.person_id = p.id
where c.name = 'Ford';

select c.name
from person as p
         inner join car c on c.person_id = p.id
where p.age = 34;
