/*************************/
create or replace function discount_after()
    returns trigger as
$$
BEGIN
    update products
    set price = price + price * 0.15
    where id = (select id from inserted);
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';
/***********************/
create or replace function discount_before()
    returns trigger as
$$
BEGIN
    update products
    set price = price + price * 0.15
    where id = new.id;
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';
/**********************/
create or replace function history()
    returns trigger as
$$
BEGIN
    insert into history_of_price (name, price, date)
    values (new.name, new.price, current_date);
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';
/**********************/
create trigger tax_trigger_after /**/
    after insert
    on products
    referencing new table as inserted
    for each statement
execute procedure discount_after();
/*********************/
create trigger tax_trigger_before /**/
    before INSERT
    on products
    for each row
execute procedure discount_before();
/*********************/
create trigger history_trigger /**/
    after INSERT
    on products
    for each row
execute procedure history();
/***********/
