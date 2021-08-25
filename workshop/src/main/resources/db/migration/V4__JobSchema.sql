create table job(
    id serial primary key,
    vehicleid bigint not null,
    customerid bigint not null,
    description text not null,
    actualstartdate date null,
    actualenddate date null,
    plannedstartdate date not null,
    plannedenddate date not null
)