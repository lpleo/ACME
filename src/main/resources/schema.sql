CREATE TABLE CAMP (
    id int,
    name varchar(255),
    year int,
    active int,
    PRIMARY KEY(id)
);

CREATE TABLE PARENT (
    id int,
    fiscalCode varchar(255),
    name varchar(255),
    surname varchar(255),
    email varchar(255),
    telephoneNumber varchar(255),
    PRIMARY KEY(id)
)


CREATE TABLE CHILDREN (
    id int,
    parentId int,
    fiscalCode varchar(255),
    name varchar(255),
    surname varchar(255),
    birthDate date,
    weekNumber int,
    PRIMARY KEY(id),
    FOREIGN KEY(parentId) REFERENCES PARENT(id)
)

CREATE TABLE SUBSCRIPTION (
    id int,
    campId int,
    childrenId int,
    month int,
    firstDay int,
    lastDay int,
    weekNumber int,
    PRIMARY KEY(id),
    FOREIGN KEY(campId) REFERENCES CAMP(id),
    FOREIGN KEY(childrenId) REFERENCES CHILDREN(id)
)

CREATE TABLE ALLERGY(
    id int,
    childrenId int,
    name varchar(255),
    description varchar(255),
    type varchar(255),
    PRIMARY KEY(id),
    FOREIGN KEY(childrenId) REFERENCES CHILDREN(id)
)

