begin transaction isolation level serializable;
select sum(count) from products;
update products set count = 10 where name = 'product_3';
commit