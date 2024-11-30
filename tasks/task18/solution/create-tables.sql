CREATE TABLE public.country
(
    id       serial primary key,
    name     varchar(200)   not null,
    iso_name char(3) unique not null
);

CREATE TABLE public.plane_type
(
    id             serial primary key,
    manufacturer   varchar(200) not null,
    name           varchar(200) not null,
    max_altitude   decimal      not null,
    max_speed      decimal      not null,
    max_range      decimal      not null,
    max_passengers int          not null
);

CREATE TABLE public.plane
(
    id                      serial primary key,
    plane_type_id           int references public.plane_type (id) not null,
    country_registration_id int references public.country (id)    not null,
    local_number            varchar(20) unique,
    international_number    varchar(20) unique,
    count_flight            int                                   not null default 0,
    create_date             date                                  not null,
    last_date               date                                  not null
);

CREATE TABLE public.person
(
    id       serial primary key,
    birthday date    not null,
    sex      char(1) not null
);

CREATE TABLE public.person_docs
(
    person_id             int references public.person (id) not null,
    full_name_in_document varchar(200)                      not null,
    document_type         varchar(200)                      not null,
    document_country      int references public.country (id),
    document_number       varchar(200)                      not null,
    document_issue_date   date,
    document_issuer       varchar(200)                      not null,
    document_expire_date  date
);

CREATE TABLE public.flight
(
    id             serial primary key,
    from_country   int references public.country (id) not null,
    from_address   varchar(200)                       not null,
    from_timestamp timestamp with time zone           not null,
    to_country     int references public.country (id) not null,
    to_address     varchar(200)                       not null,
    to_timestamp   timestamp with time zone           not null,
    plane_id       int references public.plane (id)   not null
);

CREATE TABLE public.crew_type
(
    id   serial primary key,
    name varchar(20) unique not null
);

CREATE TABLE public.crew_country_available
(
    person_id  int references public.person (id)  not null,
    country_id int references public.country (id) not null
);

CREATE TABLE public.passenger_type
(
    id   serial primary key,
    name varchar(20) unique not null
);

CREATE TABLE public.flight_passenger
(
    flight_id      int references public.flight (id),
    person_id      int references public.person (id),
    passenger_type int references public.passenger_type (id),
    registered     boolean not null default false,
    position       int
);

CREATE TABLE public.flight_crew
(
    flight_id int references public.flight (id),
    person_id int references public.person (id),
    crew_type int references public.crew_type (id),
    changed   boolean not null default false
)