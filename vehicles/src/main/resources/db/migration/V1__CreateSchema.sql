create table vehicle(
    id serial primary key,
    licenseNumber varchar(10) not null,
    brand varchar(50) not null,
    type varchar(50) not null,
    ownerId int not null
);