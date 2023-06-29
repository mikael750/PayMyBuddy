insert into user( FIRST_NAME, LAST_NAME, PASSWORD, EMAIL, BIRTHDATE, SOLDE) values ('Rich','Guy','$2a$12$3hvSar88a7RGmy8u7XLxZeEFi5PkDp2xsLWQ2YPNUDsXXcOeYgp96','rich@guy.fr','03/03/1993',100);
insert into user( FIRST_NAME, LAST_NAME, PASSWORD, EMAIL, BIRTHDATE, SOLDE) values ('Jean','Michel','$2a$12$3hvSar88a7RGmy8u7XLxZeEFi5PkDp2xsLWQ2YPNUDsXXcOeYgp96','jean@michel.fr','01/01/1991',0);
insert into user( FIRST_NAME, LAST_NAME, PASSWORD, EMAIL, BIRTHDATE, SOLDE) values ('Marc','Sivel','$2a$12$3hvSar88a7RGmy8u7XLxZeEFi5PkDp2xsLWQ2YPNUDsXXcOeYgp96','marc@sivel.fr','02/02/1992',0);

insert into transaction( AMOUNT, TRANSACTION_TYPE, SENDER_ID, RECIEVER_ID, DATE) values ( 50, 'test', (SELECT id from user where email = 'rich@guy.fr'), (SELECT id from user where email = 'jean@michel.fr'), '2023-06-23');
insert into transaction( AMOUNT, TRANSACTION_TYPE, SENDER_ID, RECIEVER_ID, DATE ) values ( 50, 'test', (SELECT id from user where email = 'jean@michel.fr'), (SELECT id from user where email = 'rich@guy.fr'), '2023-06-23');

insert into account( IBAN, BIC) values ("FR4312739000302353653786L28","AXAFAKE");
insert into account( IBAN, BIC) values ("FR3314508000401791339773H87","MMAFAKE");
insert into account( IBAN, BIC) values ("FR5814508000408196166348K61","POSFAKE");

