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

------------------------------
ALTER TABLE "public"."sysperson" ADD COLUMN enabled bool DEFAULT false;
------------------------------
CREATE TABLE "public"."app_role" (
                                     "id" int8 NOT NULL,
                                     "description" varchar(255) DEFAULT NULL,
                                     "role_name" varchar(255) DEFAULT NULL,
                                     PRIMARY KEY ("id")
);

------------------------------

CREATE TABLE "public"."group_members" (
                                          "id" int4 NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1),
                                          "username" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                          "group_id" int4 NOT NULL,
                                          PRIMARY KEY ("id"),
                                          FOREIGN KEY ("group_id") REFERENCES "public"."groups" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION
);


insert into group_members(username, group_id) values ('user1', '1');

------------------------------

CREATE TABLE "public"."groups" (
                                   "id" int4 NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1),
                                   "group_name" varchar(50) NOT NULL,
                                   PRIMARY KEY ("id")
);



insert into groups(group_name) values ('SYS-ADMIN');
insert into groups(group_name) values ('SYS-USER');

------------------------------