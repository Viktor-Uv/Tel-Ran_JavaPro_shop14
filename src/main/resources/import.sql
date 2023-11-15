-- import.sql

INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Coca-Cola 0.33 aluminium', 0.86, 'true');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Red Bull 0.5 aluminium', 2.05, 'true');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Nestle Kit-Kat white', 1.02, 'false');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Ravioli with cheese', 0.99, 'true');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Goat cheese', 1.4, 'true');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Safety Matches', 0.04, 'false');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Calamari burger', 0.55, 'true');

INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Salmon sandwich', 2.44, 'true');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Wasabi', 1.33, 'false');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Chicken Rice', 1.77, 'true');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Salami with pepper', 2.01, 'true');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Shrimp cocktail', 11.22, 'false');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Salmon rolls', 8.71, 'true');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Baguette with coriander', 2.31, 'true');
INSERT INTO products (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_IS_ACTIVE) values ('Pork sausages', 2.44, 'false');

INSERT INTO comments (content, product_id) values ('Its a first comment', 1);
INSERT INTO comments (content, product_id) values ('Its a second comment', 2);
INSERT INTO comments (content, product_id) values ('Its a third comment', 2);

insert into cards (NAME) values ('My christmas products'); -- 1
insert into product_card (product_id, card_id) values (1,1);
insert into product_card (product_id, card_id) values (2,1);
insert into product_card (product_id, card_id) values (3,1);

insert into cards (NAME) values ('My favorite products'); -- 2
insert into product_card (product_id, card_id) values (3,2);
insert into product_card (product_id, card_id) values (4,2);
insert into product_card (product_id, card_id) values (5,2);
