create sequence role_sequence start 1 increment 1;
create sequence student_sequence start 1 increment 1;
create sequence user_sequence start 1 increment 1;
create table role
(
    id        int8 not null,
    role_enum varchar(255),
    primary key (id)
);
create table student
(
    id    int8         not null,
    email varchar(255) not null,
    name  varchar(255) not null,
    primary key (id)
);
create table user_roles
(
    user_id int8 not null,
    role_id int8 not null,
    primary key (user_id, role_id)
);
create table users
(
    id       int8 not null,
    email    varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);
alter table if exists users
    add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table if exists users
    add constraint UKr43af9ap4edm43mmtq01oddj6 unique (username);
alter table if exists user_roles
    add constraint FKrhfovtciq1l558cw6udg0h0d3 foreign key (role_id) references role;
alter table if exists user_roles
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users;
