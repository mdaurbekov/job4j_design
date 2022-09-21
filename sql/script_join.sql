create table car
(
    id   serial primary key,
    name varchar(30),
    year date
);

create table person
(
    id     serial primary key,
    name   varchar(30),
    age    int,
    car_id int references car (id)
);

insert into car(name, year)
values ('Ford', date '1997-01-01'),
       ('Nissan', date '1995-01-01'),
       ('BMW', date '1990-01-01'),
       ('Mazda', date '2000-01-01'),
       ('Mercedes', date '1999-01-01');


insert into person(name, age, car_id)
values ('Ivan', 35, 1),
       ('Maksim', 38, 1),
       ('Oleg', 34, 3),
       ('Kirill', 30, 1),
       ('Stepan', 46, 2);

select p.name, c.name, to_char(c.year:: date, 'YYYY')
from person as p
         join car c on c.id = p.car_id;

select p.name
from person as p
         join car c on c.id = p.car_id
where c.name = 'Ford';

select c.name
from person as p
         inner join car c on c.id = p.car_id
where p.age = 34;
