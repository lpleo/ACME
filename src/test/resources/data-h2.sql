INSERT INTO CAMP(name,`year`,active) VALUES ('TESTCAMPACTIVE',2008,1);
INSERT INTO CAMP(name,`year`,active) VALUES ('TESTCAMP_NO_ACTIVE',2005,0);

INSERT INTO CHILD(fiscalCode, name, surname, birthDate)
VALUES ('AAABBB55G50W548A','CIAO','PIPPO', NOW());

INSERT INTO PARENT(fiscalCode, name, surname, email, telephoneNumber)
VALUES ('AAABBB55G50W548A','LOLLO','LOLLI', 'abc@ciao.it','123456789');

INSERT INTO ALLERGY(childId,name,description,type)
VALUES (1,'BREAD','Chocolate flavor bread', 'FOOD');
INSERT INTO ALLERGY(childId,name,description,type)
VALUES (1,'PASTA','Chocolate flavor pasta', 'FOOD');
