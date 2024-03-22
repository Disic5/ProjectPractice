create table employee.roles(
     id serial primary key,
     name varchar(10) not null
);



create table employee.user_roles(
    id serial primary key ,
    user_id smallint not null,
    role_id smallint not null,
    foreign key (user_id) references employee(id),
    foreign key (role_id) references roles(id) on DELETE cascade
                                );