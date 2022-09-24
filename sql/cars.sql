create table car_bodies
(
    id   serial primary key,
    name varchar(50)
);

create table car_engines
(
    id   serial primary key,
    name varchar(50)
);

create table car_transmissions
(
    id   serial primary key,
    name varchar(50)
);

create table cars
(
    id              serial primary key,
    name            varchar(50),
    body_id         int references car_bodies (id),
    engine_id       int references car_engines (id),
    transmission_id int references car_transmissions (id)
);

insert into car_bodies (name)
values ('Седан'),
       ('Пикап'),
       ('Минивэн'),
       ('Универсал'),
       ('Хэтчбек');

insert into car_engines (name)
values ('Двигатель 1'),
       ('Двигатель 2'),
       ('Двигатель 3');

insert into car_transmissions (name)
values ('Робот'),
       ('Механика'),
       ('Вариатор'),
       ('Автоматика');

insert into cars (name, body_id, engine_id, transmission_id)
values ('Ford', 1, 1, 2),
       ('Nissan', 5, 3, 4),
       ('Mercedes', 3, 1, 3),
       ('Lada', 4, 1, 2),
       ('Volvo', 5, 3, 2),
       ('Honda', 1, null, null),
       ('WW', 5, null, 4);


select c.name car, cb.name car_body, ce.name engine, ct.name transmissoon
from cars c
         left join car_bodies cb on cb.id = c.body_id
         left join car_engines ce on ce.id = c.engine_id
         left join car_transmissions ct on ct.id = c.transmission_id;

select cb.name
from car_bodies cb
         left join cars c on cb.id = c.body_id
where c.id is null;

select ce.name
from car_engines ce
         left join cars c on ce.id = c.engine_id
where c.id is null;

select ct.name
from car_transmissions ct
         left join cars c on ct.id = c.transmission_id
where c.id is null;



