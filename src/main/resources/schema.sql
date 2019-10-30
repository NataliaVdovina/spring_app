CREATE TABLE "users" (
	"user_id" bigserial PRIMARY KEY,
	"email" varchar(50) NOT NULL UNIQUE,
	"password" varchar(50) NOT NULL,
	"first_name" varchar(50) NOT NULL,
	"last_name" varchar(50) NOT NULL,
	"subscription" varchar(10),
	"user_role" varchar(10) NOT NULL
    );

CREATE TABLE "tasks" (
	"task_id" bigserial PRIMARY KEY,
	"user_id" bigint NOT NULL,
	"task_name" varchar(50) NOT NULL UNIQUE,
	"status" varchar(20) NOT NULL,
	"taskPriority" varchar (10) NOT NULL
	FOREIGN KEY(user_id) REFERENCES users (user_id)
);