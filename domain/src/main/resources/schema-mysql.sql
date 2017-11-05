CREATE TABLE authentication (
    _id             BIGINT AUTO_INCREMENT,
    token           VARCHAR(40) NOT NULL,
    password        VARCHAR(40) NOT NULL,
    nick_name       VARCHAR(40) NOT NULL,
    type            VARCHAR(20) NOT NULL,
    register_date   DATETIME    NOT NULL,
    last_login_date DATETIME    NOT NULL,
    PRIMARY KEY (_id),
    CONSTRAINT UNIQUE (token),
    CONSTRAINT UNIQUE (nick_name)
);

CREATE TABLE role (
    _id  BIGINT AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    PRIMARY KEY (_id),
    CONSTRAINT UNIQUE (name)
);

CREATE TABLE authentication_role (
    authentication_id BIGINT,
    role_id           BIGINT,
    PRIMARY KEY (authentication_id, role_id),
    FOREIGN KEY (authentication_id) REFERENCES authentication (_id),
    FOREIGN KEY (role_id) REFERENCES role (_id)
);

CREATE TABLE image (
    _id     BIGINT AUTO_INCREMENT,
    content BINARY       NOT NULL,
    type    VARCHAR(20)  NOT NULL,
    md5     VARCHAR(128) NOT NULL,
    PRIMARY KEY (_id),
    CONSTRAINT UNIQUE (md5)
);

CREATE TABLE author (
    _id                  BIGINT AUTO_INCREMENT,
    description          VARCHAR(200),
    icon_image_id        BIGINT,
    default_anthology_id BIGINT,
    PRIMARY KEY (_id),
    FOREIGN KEY (icon_image_id) REFERENCES image (_id)
);

CREATE TABLE authentication_author (
    authentication_id BIGINT,
    author_id         BIGINT,
    PRIMARY KEY (authentication_id, author_id),
    FOREIGN KEY (authentication_id) REFERENCES authentication (_id),
    FOREIGN KEY (author_id) REFERENCES author (_id),
    CONSTRAINT UNIQUE (authentication_id)
);

CREATE TABLE anthology_additional_info (
    _id                 BIGINT AUTO_INCREMENT,
    followup_numbermber BIGINT DEFAULT 0,
    comment_number      BIGINT DEFAULT 0,
    praise_number       BIGINT DEFAULT 0,
    bookmark_number     BIGINT DEFAULT 0,
    PRIMARY KEY (_id)
);

CREATE TABLE anthology (
    _id                BIGINT AUTO_INCREMENT,
    title              VARCHAR(40)  NOT NULL,
    summary            VARCHAR(200) NOT NULL,
    author_id          BIGINT       NOT NULL,
    create_date        DATETIME     NOT NULL,
    publish_date       DATETIME,
    update_date        DATETIME,
    followup_number    BIGINT DEFAULT 0,
    cover_image_id     BIGINT,
    additional_info_id BIGINT       NOT NULL,
    PRIMARY KEY (_id),
    FOREIGN KEY (author_id) REFERENCES author (_id),
    FOREIGN KEY (additional_info_id) REFERENCES anthology_additional_info (_id)
);

ALTER TABLE author
    ADD FOREIGN KEY (default_anthology_id) REFERENCES anthology (_id);

CREATE TABLE article_additional_info (
    _id             BIGINT AUTO_INCREMENT,
    view_number     BIGINT DEFAULT 0,
    comment_number  BIGINT DEFAULT 0,
    praise_number   BIGINT DEFAULT 0,
    bookmark_number BIGINT DEFAULT 0,
    PRIMARY KEY (_id)
);

CREATE TABLE article (
    _id                BIGINT AUTO_INCREMENT,
    summary            VARCHAR(200) NOT NULL,
    anthology_id       BIGINT       NOT NULL,
    title              VARCHAR(200) NOT NULL,
    content            TEXT         NOT NULL,
    create_date        DATETIME     NOT NULL,
    update_date        DATETIME,
    publish_date       DATETIME,
    additional_info_id BIGINT       NOT NULL,
    PRIMARY KEY (_id),
    FOREIGN KEY (anthology_id) REFERENCES anthology (_id),
    FOREIGN KEY (additional_info_id) REFERENCES article_additional_info (_id)
);
