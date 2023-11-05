create table if not exists users(
  username varchar(64) not null primary key,
  password varchar(64) not null,
  email varchar(128),
  firstName varchar(128) not null,
  lastName varchar(128) not null,
  birthDate DATE not null );

delete from users;
insert into users(username,password,email, firstName, lastName,birthDate) values('user1','changeme', 'thuongtx@example.com', 'thuong', 'trinh', to_date('1992-01-01','yyyy-MM-dd'));
insert into users(username,password,email, firstName, lastName,birthDate) values('user2','changeme', 'tungtx@example.com', 'tung', 'trinh', to_date('1993-01-01','yyyy-MM-dd'));
insert into users(username,password,email, firstName, lastName,birthDate) values('user3','changeme', 'minhtx@example.com', 'minh', 'trinh', to_date('1991-01-01','yyyy-MM-dd'));
