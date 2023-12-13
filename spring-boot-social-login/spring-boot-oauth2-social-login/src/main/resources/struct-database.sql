CREATE TABLE "public"."user_oauth2_social_login" (
                                                     "id" int8 NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1),
                                                     "name" varchar(255) DEFAULT NULL,
                                                     "email" varchar(50) DEFAULT NULL,
                                                     "image_url" varchar(255) DEFAULT NULL,
                                                     "password" varchar(50) DEFAULT NULL,
                                                     "email_verified" bool,
                                                     "provider_id" varchar(50) DEFAULT NULL,
                                                     "provider" varchar(50) DEFAULT NULL,
                                                     "created_date" DATE,
                                                     "updated_date" DATE,
                                                     PRIMARY KEY ("id")
);