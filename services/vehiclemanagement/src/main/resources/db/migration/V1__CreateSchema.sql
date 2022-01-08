CREATE TABLE vehicles(
    id uuid primary key,
    license_number varchar(50) not null,
    brand varchar(100) not null,
    type varchar(100) not null,
    owner_id varchar(50) not null
);