
CREATE TABLE "public"."user_location" (
                                          "id" int4 NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1),
                                          "country" varchar(50) NOT NULL,
                                          "username" varchar(50) NOT NULL,
                                          "enabled" bool,
                                          PRIMARY KEY ("id")
);


ALTER TABLE "public"."users" ADD COLUMN email_verified bool DEFAULT false;

ALTER TABLE users RENAME COLUMN email_verified TO emailVerified;

------------------------------------------------------------

CREATE TABLE "public"."verification_token" (
                                               "id" int8 NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1),
                                               "username" varchar(255) DEFAULT NULL,
                                               "token" varchar(255) DEFAULT NULL,
                                               "expirydate" DATE,
                                               "status" varchar(50) DEFAULT NULL,
                                               CONSTRAINT "FK_VERIFY_USER" FOREIGN KEY (username) REFERENCES users(username),
                                               PRIMARY KEY ("id")
);


ALTER TABLE "public"."verification_token" ADD COLUMN status varchar(50)

------------------------------------------------------------

CREATE TABLE "public"."password_reset_token" (
                                                 "id" int8 NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1),
                                                 "username" varchar(255) DEFAULT NULL,
                                                 "token" varchar(255) DEFAULT NULL,
                                                 "expirydate" DATE,
                                                 CONSTRAINT "FK_PWD_RESET_USER" FOREIGN KEY (username) REFERENCES users(username),
                                                 PRIMARY KEY ("id")
);

------------------------------------------------------------

CREATE TABLE "public"."schedule_job_config" (
                                                "jobtype" varchar(255) NOT NULL,
                                                "cron_expression" varchar(255) DEFAULT NULL,
                                                "timezone" varchar(255) DEFAULT NULL,
                                                "enabled" bool,
                                                PRIMARY KEY ("jobtype")
);

insert into schedule_job_config(jobtype, cronExpression, timezone, enabled) values ('DELETE_TOKEN_EXPIRED', '0 0 5 * * * ?', 'Asia/Ho_Chi_Minh', true);

------------------------------------------------------------

