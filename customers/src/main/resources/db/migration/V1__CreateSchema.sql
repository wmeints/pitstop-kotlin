CREATE TABLE customer(
  id serial primary key,
  name varchar(100) not null,
  address varchar(500) not null,
  zipCode varchar(10) not null,
  city varchar(100) not null,
  telephoneNumber varchar(100) not null,
  emailAddress varchar(500)
)