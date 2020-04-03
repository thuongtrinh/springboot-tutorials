
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
	article_id serial,
	title VARCHAR(200) NOT NULL,
	category VARCHAR(100) NOT NULL,
	PRIMARY KEY (article_id)
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
