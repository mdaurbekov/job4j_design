create table departments
(
    id   serial primary key,
    name varchar(255)
);

create table employees
(
    id             serial primary key,
    name           varchar(255),
    departments_id int references departments (id)
);


create table teens
(
    id     serial primary key,
    name   varchar(50),
    gender int
);

insert into departments(name)
values ('Экономический'),
       ('Юридический'),
       ('Технический'),
       ('Отчетности'),
       ('Связи'),
       ('Склад');



insert into employees(name, departments_id)
values ('Boris', 1),
       ('Ivan', 6),
       ('Kiril', 1),
       ('Marina', 2),
       ('Pers', 3);

insert into teens(name, gender)
values ('Boris', 1),
       ('Ivan', 1),
       ('Kiril', 1),
       ('Marina', 2),
       ('Tanya', 2),
       ('Olga', 2);


select d.name
from departments d
         left join employees e on d.id = e.departments_id
where e.id is null;

select d.name
from employees e
         right join departments d on e.departments_id = d.id
where e.id is null;

select m.name as m, w.name as w
from teens m
         cross join teens w
where m.gender != w.gender and m.gender != 1;


