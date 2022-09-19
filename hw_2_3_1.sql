
/* one-to-many */
create table faculty(
                    id serial primary key,
                    name varchar(30)
);

create table students(
                    id serial primary key,
                    name varchar(50),
                    id_faculty int references faculty(id)
);
/************************************************************/


/*  one-to-one   */
create table agreements(
                         id serial primary key,
                         number int
);

create table employees(
                       id serial primary key,
                       name varchar(50),
                       passport_id int references agreements(id) unique
);
/***********************************************************/


/* many-to-many */
create table products(
                         id serial primary key,
                         name varchar(50)
);

create table customers(
                       id serial primary key,
                       name varchar(50)
);

create table orders(
                                id serial primary key,
                                products_id int references products(id),
                                customers_id int references customers(id)
);