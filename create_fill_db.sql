create sequence client_seq_id;
create sequence buy_book_seq_id;

create table book(
                     book_id int DEFAULT nextval('book_seq_id') primary key,
                     title varchar(50) not null,
                     author_name varchar(50) not null,
                     genre varchar(50) not null,
                     price double precision not null
);

create table client(
                       client_id int default nextval('client_seq_id') primary key,
                       client_name varchar(50) not null,
                       city_name varchar(50) not null,
                       client_email varchar(50) not null
);

create table buy_book(
                         buy_book_id int default nextval('buy_book_seq_id') primary key,
                         book_id int references book(book_id),
                         client_id int references client(client_id),
                         amount int not null
);


insert into book (title, author_name, genre, price) VALUES ('Naruto', 'Japan man', 'shounen', 90);
insert into book (title, author_name, genre, price) VALUES ('Bible', 'God', 'religion', 1000);
insert into book (title, author_name, genre, price) VALUES ('War and Peace', 'Lev Tolstoy', 'novel', 560);
insert into book (title, author_name, genre, price) VALUES ('Hotel', 'Arthur Hailey', 'novel', 300);

insert into client (client_name, city_name, client_email) VALUES ('Dima', 'Buzuluk', 'dima@gmail.com');
insert into client (client_name, city_name, client_email) VALUES ('Andrey', 'Samara', 'andrey@gmail.com');
insert into client (client_name, city_name, client_email) VALUES ('Vasya', 'Moscow', 'vasya@gmail.com');

insert into buy_book (book_id, client_id, amount) VALUES (1, 1, 14);
insert into buy_book (book_id, client_id, amount) VALUES (1, 2, 4);
insert into buy_book (book_id, client_id, amount) VALUES (3, 2, 5);