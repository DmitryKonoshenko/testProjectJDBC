CREATE TABLE PERSON
(
	ID         bigint auto_increment NOT NULL,
	FIRST_NAME varchar(255)          NOT NULL UNIQUE,
	LAST_NAME  varchar(255)          NOT NULL,
	BIRTHDAY   date,
	ADDRESS_ID bigint                NOT NULL,
	CONSTRAINT PERSON_PK PRIMARY KEY (ID)
);

CREATE TABLE ADDRESS
(
	ID        bigint auto_increment NOT NULL,
	COUNTRY   varchar(255),
	CITY      varchar(255),
	STREET    varchar(255),
	POST_CODE varchar(10),
	CONSTRAINT ID PRIMARY KEY (ID)
);

-- DROP TABLE IF EXISTS PERSON;
-- DROP TABLE IF EXISTS ADDRESS;