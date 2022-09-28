create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create or replace procedure delete_procedure()
    language 'plpgsql'
as
$$
BEGIN
    delete from products where count = 0;
END;
$$;

call delete_procedure();


create or replace function delete_function()
    returns void
    language 'plpgsql'
as
$$
begin
    delete from products where count = 0;
end;
$$;

select delete_function();