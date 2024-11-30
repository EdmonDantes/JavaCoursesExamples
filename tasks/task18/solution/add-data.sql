-- public.country
INSERT INTO public.country(name, iso_name)
values ('United States of America', 'USA'),
       ('Germany', 'DEU'),
       ('France', 'FRA'),
       ('Canada', 'CAN'),
       ('Sweden', 'SWE');

-- public.plane_type
INSERT INTO public.plane_type(manufacturer, name, max_altitude, max_speed, max_range, max_passengers)
values ('Boeing', '787-10 Dreamliner', 13100, 956, 11910, 259),
       ('Boeing', '777-200LR', 13100, 945, 15843, 368),
       ('Embraer', 'E195-E2', 12500, 870, 4917, 146),
       ('Airbus', 'A220 CS300ER', 12500, 870, 6112, 130),
       ('Bombardier', 'CRJ1000', 12497, 870, 2843, 104);

-- public.plane
INSERT INTO public.plane(plane_type_id, country_registration_id, local_number, international_number, count_flight,
                         create_date, last_date)
values (1, 2, null, 'RT-1234', 23, date '2021-04-20', date '2031-04-20'),
       (4, 4, '132234', 'WS-221', 178, date '2018-03-05', date '2033-03-05'),
       (4, 3, null, 'T-221-F', 234, date '2018-09-09', date '2028-09-09'),
       (3, 2, null, 'XNMRR', 89, date '2021-07-03', date '2040-01-03'),
       (2, 5, null, 'S-12', 467, date '2016-01-24', date '2028-01-24');

-- public.person
INSERT INTO public.person(birthday, sex)
VALUES (date '1976-05-03', 'M'),
       (date '1993-02-01', 'W'),
       (date '2003-11-23', 'M'),
       (date '1955-11-11', 'M'),
       (date '1983-07-09', 'W');

-- public.person_docs
INSERT INTO public.person_docs(person_id, full_name_in_document, document_type, document_country, document_number,
                               document_issue_date, document_issuer, document_expire_date)
VALUES (1, 'John Smit', 'PASSPORT', 1, '112234556', date '2017-03-03', 'USA', date '2027-03-03'),
       (2, 'Linda Spencer', 'DRIVER LICENSE', 4, 'T33546', date '2022-08-12', 'Canada Toronto', null),
       (3, 'Jeffrey Mendoza', 'DRIVER LICENSE', 3, '02978398238', date '2016-03-14', 'France', date '2031-03-14'),
       (4, 'Anthony Gonzales', 'INSURANCE NUMBER', 1, '113898995487874', null, 'USA', null),
       (5, 'Diana Cole', 'PASSPORT', 5, '678324', null, 'Sweden', date '2077-01-01');

-- public.flight
INSERT INTO public.flight(from_country, from_address, from_timestamp, to_country, to_address, to_timestamp, plane_id)
VALUES (2, 'Berlin', timestamp '2024-05-06 04:00:00 +2:00', 3, 'Paris', timestamp '2024-05-06 04:36:00 +2:00', 1),
       (2, 'Berlin', timestamp '2024-05-17 06:37:00 +2:00', 1, 'Wilmington', timestamp '2024-05-17 05:55:00 -4:00', 2),
       (5, 'Stockholm', timestamp '2024-08-08 15:22:00 +2:00', 3, 'Paris', timestamp '2024-08-08 17:22:00 +2:00', 3),
       (1, 'New York', timestamp '2024-06-01 16:00:00 -4:00', 4, 'Toronto', timestamp '2024-06-01 19:00:00 -4:00', 4),
       (1, 'California', timestamp '2024-07-07 10:08:00 -7:00', 2, 'Berlin', timestamp '2024-07-08 01:24:00 +2:00', 5);

-- public.crew_type
INSERT INTO public.crew_type(name)
VALUES ('PILOT'),
       ('SECOND PILOT'),
       ('MAIN STUART'),
       ('STUART'),
       ('JUNIOR STUART');

-- public.crew_country_available
INSERT INTO public.crew_country_available(person_id, country_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5);

-- public.passenger_type
INSERT INTO public.passenger_type(name)
values ('ECONOMIC'),
       ('ECONOMIC PLUS'),
       ('BUSINESS'),
       ('BUSINESS PLUS'),
       ('FIRST CLASS');

-- public.flight_passenger
INSERT INTO public.flight_passenger(flight_id, person_id, passenger_type, position)
VALUES (1, 1, 1, 10),
       (2, 2, 1, null),
       (3, 2, 3, null),
       (4, 3, 5, null),
       (5, 1, 1, 5);

-- public.flight_crew
INSERT INTO public.flight_crew(flight_id, person_id, crew_type)
VALUES (1, 2, 1),
       (1, 3, 2),
       (1, 4, 4),
       (1, 5, 4),
       (2, 5, 1);
