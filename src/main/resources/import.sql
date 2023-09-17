insert into user( FIRST_NAME, LAST_NAME, PASSWORD, EMAIL, BIRTHDATE, SOLDE) values ('Rich','Guy','$2a$12$jGKAC3h8riuul.nJEU723.TnDYjYePO3c1kfCaXj8nnQwrqD85GUq','rich@guy.fr','03/03/1993',100);
insert into user( FIRST_NAME, LAST_NAME, PASSWORD, EMAIL, BIRTHDATE, SOLDE) values ('Jean','Michel','$2a$12$jGKAC3h8riuul.nJEU723.TnDYjYePO3c1kfCaXj8nnQwrqD85GUq','jean@michel.fr','01/01/1991',0);
insert into user( FIRST_NAME, LAST_NAME, PASSWORD, EMAIL, BIRTHDATE, SOLDE) values ('Marc','Sivel','$2a$12$jGKAC3h8riuul.nJEU723.TnDYjYePO3c1kfCaXj8nnQwrqD85GUq','marc@sivel.fr','02/02/1992',0);

insert into transaction( AMOUNT, TRANSACTION_TYPE, SENDER_ID, RECEIVER_ID, DATE) values ( 50, 'TRANSFERT', (SELECT id from user where email = 'rich@guy.fr'), (SELECT id from user where email = 'jean@michel.fr'), '2023-06-23');
insert into transaction( AMOUNT, TRANSACTION_TYPE, SENDER_ID, RECEIVER_ID, DATE) values ( 50, 'TRANSFERT', (SELECT id from user where email = 'jean@michel.fr'), (SELECT id from user where email = 'rich@guy.fr'), '2023-06-23');

insert into account(USER_ID, IBAN, BIC, AMOUNT) values (1, "FR4312739000302353653786L28","AXAFAKE",5000);
insert into account(USER_ID, IBAN, BIC, AMOUNT) values (2, "FR3314508000401791339773H87","MMAFAKE",2000);
insert into account(USER_ID, IBAN, BIC, AMOUNT) values (3, "FR5814508000408196166348K61","POSFAKE",100.50);

insert into user_contacts(USER_ID, CONTACTS_ID) VALUES (1, (SELECT id from user where email = 'jean@michel.fr'));
