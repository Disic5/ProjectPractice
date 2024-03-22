create table employee.employee
(
    id       serial primary key,
    name     varchar(50)         not null,
    lastname varchar(100)        not null,
    email    varchar(255) unique not null
);
