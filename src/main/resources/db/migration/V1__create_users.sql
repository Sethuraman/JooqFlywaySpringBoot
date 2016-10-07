create table users (
    emailid varchar(250) not null primary key,
    password_hash varchar(500) not null,
    password_validated boolean default false,
    name varchar(500),
    address json,
    practice_name varchar(500),
    primary_user boolean
);
create table orders (
    order_id VARCHAR(36) not null PRIMARY KEY,
    emailid VARCHAR(250),
    .....
)