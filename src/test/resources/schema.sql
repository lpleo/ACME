CREATE TABLE CAMP (
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name varchar(255),
    year int,
    active int,
    PRIMARY KEY(id)
);

CREATE TABLE PARENT (
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    fiscalCode varchar(255),
    name varchar(255),
    surname varchar(255),
    email varchar(255),
    telephoneNumber varchar(255),
    PRIMARY KEY(id)
);


CREATE TABLE CHILDREN (
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    parentId MEDIUMINT NOT NULL,
    fiscalCode varchar(255),
    name varchar(255),
    surname varchar(255),
    birthDate date,
    weekNumber int,
    PRIMARY KEY(id),
    FOREIGN KEY(parentId) REFERENCES PARENT(id)
);

CREATE TABLE SUBSCRIPTION (
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    campId MEDIUMINT NOT NULL,
    childrenId MEDIUMINT NOT NULL,
    month int,
    firstDay int,
    lastDay int,
    weekNumber int,
    PRIMARY KEY(id),
    FOREIGN KEY(campId) REFERENCES CAMP(id),
    FOREIGN KEY(childrenId) REFERENCES CHILDREN(id)
);

CREATE TABLE ALLERGY(
    id MEDIUMINT NOT NULL AUTO_INCREMENT,
    childrenId MEDIUMINT NOT NULL,
    name varchar(255),
    description varchar(255),
    type varchar(255),
    PRIMARY KEY(id),
    FOREIGN KEY(childrenId) REFERENCES CHILDREN(id)
);

