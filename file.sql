create table news (id serial primary key, title varchar(50), news_text text, publication bool);
insert into news (title, news_text, publication) values ('first news', 'this is first news ', false );
insert into news (title, news_text, publication) values ('last news', 'this is last news ', false );
select * from news;
update news set title = 'old news', news_text = 'it''s not news anymore', publication = true  where id = 4;
select * from news;
delete from news where title = 'first news';
select * from news;

