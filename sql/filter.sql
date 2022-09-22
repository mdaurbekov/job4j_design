create table type
(
    id   serial primary key,
    name varchar(50)
);
create table product
(
    id           serial primary key,
    name         varchar(50),
    type_id      int references type (id),
    expired_date date,
    price        int
);

insert into type (name)
values ('Сыр'),
       ('Йогурт'),
       ('Молоко'),
       ('Орехи');

insert into product (name, type_id, expired_date, price)
values ('Сыр моцарелла', 1, date'2022-01-05', 210),
       ('Сыр плавленный', 1, date'2022-10-05', 240),
       ('Сыр швейцарский', 1, date'2022-11-12', 391),
       ('Молоко парное', 3, date'2022-01-05', 110),
       ('Молоко Сгущенное', 3, date'2022-01-05', 115),
       ('Молоко обезжиренное', 3, date'2022-12-01', 94),
       ('Йогурт детский', 2, date'2022-03-03', 71),
       ('Орех грецкий', 4, date'2022-01-05', 142),
       ('Орех лесной', 4, date'2023-12-05', 200);

select p.name
from product p
         join public.type t on t.id = p.type_id
where t.name = 'Сыр';


select p.name
from product p
where p.name like '%Орех%';


select p.name
from product p
where current_date > p.expired_date;


select p.name
from product p
where p.price = (select max(p.price) from product p);


select t.name, count(p.name)
from type t
         join product p on t.id = p.type_id
group by t.name;


select p.name
from product p
         join public.type t on t.id = p.type_id
where (t.name = 'Сыр' or t.name = 'Молоко');


select t.name, count(p.name)
from type t
         join product p on t.id = p.type_id
group by t.name
having count(p.name) < 3;


select t.name, p.name
from product p
         join type t on t.id = p.type_id;

