create table products (
                          id serial primary key,
                          name varchar(50),
                          producer varchar(50),
                          count integer default 0,
                          price integer
);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
select * from products;

begin transaction;

savepoint first_point;
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);

select * from products;

savepoint second_point;
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 41, 74);
rollback to second_point;
select * from products;

rollback to first_point;
select * from products;
commit;

