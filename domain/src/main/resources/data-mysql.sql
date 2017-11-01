INSERT INTO role VALUES (1, 'ROLE_AUTHOR');
INSERT INTO role VALUES (2, 'ROLE_ADMIN');

INSERT INTO authentication (_id, token, password, nick_name, register_date, last_login_date, type)
VALUES (1, '1@1.com', 'password', '用户1', CURDATE(), CURDATE(), 'EMAIL');
INSERT INTO authentication (_id, token, password, nick_name, register_date, last_login_date, type)
VALUES (2, '2@1.com', 'password', '用户2', CURDATE(), CURDATE(), 'EMAIL');
INSERT INTO authentication (_id, token, password, nick_name, register_date, last_login_date, type)
VALUES (3, '3@1.com', 'password', '用户3', CURDATE(), CURDATE(), 'EMAIL');

INSERT INTO authentication_role VALUES (1, 1);
INSERT INTO authentication_role VALUES (1, 2);
INSERT INTO authentication_role VALUES (2, 1);
INSERT INTO authentication_role VALUES (3, 2);


INSERT INTO author
(_id, description)
VALUES
    (1, '用户1描述');

INSERT INTO author
(_id, description)
VALUES
    (2, '用户2描述');

INSERT INTO author
(_id, description)
VALUES
    (3, '用户3描述');

INSERT INTO authentication_author (authentication_id, author_id) VALUES (1, 1);
INSERT INTO authentication_author (authentication_id, author_id) VALUES (2, 2);
INSERT INTO authentication_author (authentication_id, author_id) VALUES (3, 3);