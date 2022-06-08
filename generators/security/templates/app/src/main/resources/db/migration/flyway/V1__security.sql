create table <%= tableName %> (
    id bigint not null auto_increment,
    text varchar(1024) not null,
    email varchar(1024) not null,
    username varchar(1024) not null,
    password varchar(1024) not null,
    primary key (id)
);

insert into <%= tableName %> (email, username, password ) values ( 'email', 'username',
'$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6');