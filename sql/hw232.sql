create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices (name, price)
values ('Ноутбук', 350.4),
       ('Телевизор', 250.1),
       ('ПК', 142.7),
       ('Телефон', 87.9),
       ('Часы', 310.5);

insert into people (name)
values ('Иван'),
       ('Василий'),
       ('Олег');
insert into devices_people (device_id, people_id)
values (1, 1),
       (5, 1),
       (4, 2),
       (2, 3);

select avg(d.price)
from devices d;

select p.name, avg(d.price)
from devices_people dp
         join devices d on d.id = dp.device_id
         join people p on p.id = dp.people_id
group by p.name;


select p.name, avg(d.price)
from devices_people dp
         join devices d on d.id = dp.device_id
         join people p on p.id = dp.people_id
group by p.name
having avg(d.price) > 90;





