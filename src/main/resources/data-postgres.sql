INSERT INTO authority(id, name) VALUES (1, 'ROLE_ADMINISTRATOR');
INSERT INTO authority(id, name) VALUES (2, 'ROLE_AGENT');

INSERT INTO privilege(id, name) VALUES (1, 'PRODUCT_CRUD_PRIVILEGE');

INSERT INTO roles_privileges(role_id, privilege_id) VALUES (2, 1);

INSERT INTO agent_user(user_type, id, email, enabled, last_password_reset_date, name, password, password_reset_code,
password_reset_failed, surname, mail_activation_code, geneder, registration_sent_date)
VALUES ('AGENT_USER', 1, 'pharmacyisa6+agent@gmail.com', true, '2017-10-01 21:58:58.508-07', 'Pera',
'$2a$10$/b6nrwDAIMHQv/wAeD004u91l/k.973ksiTVbL1yJCKw3TuVaHMf6', null, 0, 'Peric', null, 0, '2021-05-21 15:39:47.739');

INSERT INTO user_authority(user_id, authority_id) VALUES (1, 2);

INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (1, 'Converse', 'product1.jpg', 50, 25, 1);
INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (2, 'Bratz doll', 'product2.jpg', 15, 5, 1);
INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (3, 'Metallica t-shirt', 'product3.jpg', 45, 50, 1);
INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (4, 'Elegant dress', 'product4.jpg', 150, 20, 1);
INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (5, 'Baby tiger', 'product5.jpg', 500, 4, 1);
INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (6, 'Hamster wheel', 'product6.jpeg', 5, 11, 1);
INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (7, 'Pride and prejudice by Jane Austen', 'product7.jpg', 10, 80, 1);
INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (8, 'Vans limited edition', 'product8.jpg', 55, 2, 1);
INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (9, 'Strawberry dress', 'product9.jpeg', 57, 2, 1);
INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (10, 'Detroit for PS4', 'product10.jpg', 20, 2, 1);
INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (11, 'Cats musical costume', 'product11.jpg', 666, 2, 1);
INSERT INTO product(id, name, picture, price, quantity, user_id) VALUES (12, 'Basic black t-shirt', 'product12.jpg', 12, 2, 1);