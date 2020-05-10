
CREATE DATABASE sysperson
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE sysperson
    IS 'myBD for researching';

//-------------------------------------------------------

create table customer(
	id int4 NOT NULL primary key,
	name varchar(50),
	address varchar(50)
) with(oids=false);

insert into customer(id, name, address)
values(1, 'thuongtx', 'HCM-VN');

//-------------------------------------------------------

CREATE TABLE public.articles (
	article_id serial PRIMARY KEY,
	title VARCHAR(200) NOT NULL,
	category VARCHAR(100) NOT NULL
)
WITH (
    OIDS = FALSE
)

INSERT INTO public.articles(article_id, title, category) VALUES
	(1, 'Java Concurrency', 'Java'),
	(2, 'Spring Boot Getting Started', 'Spring Boot'),
	(3, 'Lambda Expressions Java 8 Example', 'Java 8'),
	(4, 'Java Core', 'Java'),
	(5, 'Spring Boot security', 'Spring Boot'),
	(6, 'Stream', 'Java 8');

//-------------------------------------------------------

CREATE TABLE IF NOT EXISTS userInfo (
  username varchar(50) NOT NULL PRIMARY KEY,
  password varchar(100) NOT NULL,
  fullname varchar(100) NOT NULL,
  role varchar(50) NOT NULL,
  country varchar(100) NOT NULL,
  enabled smallint NOT NULL
)  with(oids=false);


INSERT INTO userInfo (username, password, fullname, role, country, enabled) VALUES
	('thuongtx', '$2a$10$eLPcsMMNu8pGa0rKYDZiMOeTQSNYaSqf4pwnBTLFojE7VrZ9yCVV2', 'thuongtrinh', 'ROLE_ADMIN', 'VN', 1),
	('tungtx', '$2a$10$bwBbZOQhHAIarbS1rkvq9OOAtypXozhZSRwGBLLNyb9BAnoAsXkPO', 'tungtrinh', 'ROLE_USER', 'VN', 1); 

--a123456: $2a$10$eLPcsMMNu8pGa0rKYDZiMOeTQSNYaSqf4pwnBTLFojE7VrZ9yCVV2
--b123456: $2a$10$bwBbZOQhHAIarbS1rkvq9OOAtypXozhZSRwGBLLNyb9BAnoAsXkPO



CREATE TABLE  public.student(
   ID SERIAL,  
   NAME varchar(100) NOT NULL,
   BRANCH varchar(255) NOT NULL,
   PERCENTAGE integer NOT NULL,  
   PHONE integer NOT NULL,
   EMAIL varchar(255) NOT NULL,
   CONSTRAINT groups_pkey PRIMARY KEY (ID)
);

