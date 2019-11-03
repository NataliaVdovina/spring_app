insert into users (email, password, first_name, last_name, user_role)
    values ('testEmail345', 'testPassword1', 'testFirstName1', 'testLastName1', 'USER');
insert into users (email, password, first_name, last_name, user_role)
    values ('testEmail345', 'testPassword2', 'testFirstName2', 'testLastName2', 'ADMIN');

insert into tasks (user_id, task_name, status, taskPriority)
    values (2, 'taskName47', 'OPEN', 'LOW');