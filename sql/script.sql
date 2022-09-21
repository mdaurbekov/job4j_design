insert into fauna (name, avg_age, discovery_date)
values ('Черепаха', 40000, date '1917-09-15'),
       ('Медведь', 15000, date '1964-09-15'),
       ('Кенгуру', 20000, date '1970-09-15'),
       ('Кит', 25000, null),
       ('Осьминог', 22000, date '1955-09-15'),
       ('Тигр', 10000, date '1818-09-15'),
       ('Стриж', 8000, date '1917-09-15'),
       ('Кошка', 12000, null),
       ('Голубь', 5000, date '1917-09-15'),
       ('Гепард', 7000, date '1500-09-15'),
       ('Лось', 6000, date '1050-09-15'),
       ('Рыба', 31000, date '2020-09-15'),
       ('Акула', 27000, date '1949-09-15');


select * from fauna
where name like '%Кенг%';

select * from fauna
where avg_age > 10000
  and avg_age < 21000;

select * from fauna
where discovery_date is null;

select * from fauna
where discovery_date > date '1950-01-01';

