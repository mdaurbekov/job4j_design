CREATE TABLE company
(
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id         integer NOT NULL,
    name       character varying,
    company_id integer references company (id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name)
VALUES (1, 'Компания 1'),
       (2, 'Компания 2'),
       (3, 'Компания 3'),
       (4, 'Компания 4'),
       (5, 'Компания 5');


insert into person (id, name, company_id)
VALUES (1, 'Человек 1', 1),
       (2, 'Человек 2', 1),
       (3, 'Человек 3', 4),
       (4, 'Человек 4', 5),
       (5, 'Человек 5', 5);

select p.name, c.name
from person p
         left join company c on c.id = p.company_id
where p.company_id != 5;

select c.name, count(p.company_id)
from person p
         join company c
              on c.id = p.company_id
group by c.name
having COUNT(p.company_id) =
       (select count(p.company_id) from person p group by p.company_id order by count(p.company_id) desc limit 1);
