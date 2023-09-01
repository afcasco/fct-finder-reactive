CREATE TABLE IF NOT EXISTS company
(
    id       bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cif      varchar(255) DEFAULT NULL,
    zip_code integer,
    city     varchar(255) DEFAULT NULL,
    address  varchar(255) DEFAULT NULL,
    name     varchar(255) DEFAULT NULL,
    phone    varchar(255) DEFAULT NULL,
    status   varchar(255),
    created_date timestamp,
    last_modified_date timestamp
);