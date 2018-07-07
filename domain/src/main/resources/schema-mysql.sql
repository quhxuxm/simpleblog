CREATE TABLE resource (
    id       BIGINT AUTO_INCREMENT,
    content   LONGBLOB            NOT NULL,
    mime_type VARCHAR(20)         NOT NULL,
    md5       VARCHAR(128) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE author_additional_info (
    id                        BIGINT AUTO_INCREMENT,
    publish_articles_number    BIGINT DEFAULT 0,
    publish_comments_number    BIGINT DEFAULT 0,
    publish_anthologies_number BIGINT DEFAULT 0,
    followedby_number          BIGINT DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE author (
    id                  BIGINT AUTO_INCREMENT,
    description          VARCHAR(200),
    icon_image_id        BIGINT,
    additional_info_id   BIGINT UNIQUE  NOT NULL,
    nick_name            VARCHAR(40)    NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (icon_image_id) REFERENCES resource (id),
    FOREIGN KEY (additional_info_id) REFERENCES author_additional_info (id)
);

CREATE TABLE role (
    id  BIGINT AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UNIQUE (name)
);

CREATE TABLE author_role (
    author_id BIGINT,
    role_id           BIGINT,
    PRIMARY KEY (author_id, role_id),
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE authentication (
    id             BIGINT AUTO_INCREMENT,
    token           VARCHAR(40) NOT NULL,
    password        VARCHAR(40) NOT NULL,
    type            VARCHAR(20) NOT NULL,
    register_date   DATETIME    NOT NULL,
    last_login_date DATETIME    NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES author (id),
    CONSTRAINT UNIQUE (token),
    CONSTRAINT UNIQUE (type, author_id)
);

CREATE TABLE anthology_additional_info (
    id             BIGINT AUTO_INCREMENT,
    view_number     BIGINT DEFAULT 0,
    followup_number BIGINT DEFAULT 0,
    comment_number  BIGINT DEFAULT 0,
    praise_number   BIGINT DEFAULT 0,
    bookmark_number BIGINT DEFAULT 0,
    article_number  BIGINT DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE anthology (
    id                BIGINT AUTO_INCREMENT,
    title              VARCHAR(40)        NOT NULL,
    summary            VARCHAR(200)       NOT NULL,
    author_id          BIGINT             NOT NULL,
    create_date        DATETIME           NOT NULL,
    publish_date       DATETIME,
    update_date        DATETIME,
    cover_image_id     BIGINT,
    additional_info_id BIGINT UNIQUE      NOT NULL,
    is_published       BOOL               NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (additional_info_id) REFERENCES anthology_additional_info (id)
);

CREATE TABLE author_default_anthology(
    author_id BIGINT NOT NULL,
    anthology_id BIGINT NOT NULL,
    PRIMARY KEY(author_id),
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (anthology_id) REFERENCES anthology (id)
);

CREATE TABLE article_additional_info (
    id             BIGINT AUTO_INCREMENT,
    view_number     BIGINT DEFAULT 0,
    comment_number  BIGINT DEFAULT 0,
    praise_number   BIGINT DEFAULT 0,
    bookmark_number BIGINT DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE article (
    id                BIGINT AUTO_INCREMENT,
    summary            VARCHAR(200)      NOT NULL,
    anthology_id       BIGINT            NOT NULL,
    title              VARCHAR(200)      NOT NULL,
    content            TEXT              NOT NULL,
    create_date        DATETIME          NOT NULL,
    update_date        DATETIME,
    publish_date       DATETIME,
    additional_info_id BIGINT UNIQUE     NOT NULL,
    cover_image_id     BIGINT,
    is_published       BOOL              NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (anthology_id) REFERENCES anthology (id),
    FOREIGN KEY (additional_info_id) REFERENCES article_additional_info (id),
    FOREIGN KEY (cover_image_id) REFERENCES resource (id)
);

CREATE TABLE tag (
    id  BIGINT AUTO_INCREMENT,
    text VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE author_tag (
    author_id BIGINT NOT NULL,
    tag_id    BIGINT NOT NULL,
    selected  BOOL   NOT NULL,
    weight    DOUBLE NOT NULL,
    PRIMARY KEY (author_id, tag_id),
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (tag_id) REFERENCES tag (id)
);

CREATE TABLE article_tag (
    article_id BIGINT NOT NULL,
    tag_id     BIGINT NOT NULL,
    selected   BOOL   NOT NULL,
    weight     DOUBLE NOT NULL,
    PRIMARY KEY (article_id, tag_id),
    FOREIGN KEY (article_id) REFERENCES article (id),
    FOREIGN KEY (tag_id) REFERENCES tag (id)
);

CREATE TABLE anthology_tag (
    anthology_id BIGINT NOT NULL,
    tag_id       BIGINT NOT NULL,
    selected     BOOL   NOT NULL,
    weight       DOUBLE NOT NULL,
    PRIMARY KEY (anthology_id, tag_id),
    FOREIGN KEY (anthology_id) REFERENCES anthology (id),
    FOREIGN KEY (tag_id) REFERENCES tag (id)
);

CREATE TABLE article_comment(
    id BIGINT AUTO_INCREMENT,
    author_id BIGINT NOT NULL,
    create_date        DATETIME          NOT NULL,
    update_date        DATETIME,
    content            TEXT              NOT NULL,
    parent_id BIGINT NOT NULL,
    article_id       BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (parent_id) REFERENCES article_comment (id),
    FOREIGN KEY (article_id) REFERENCES article (id)
);

CREATE TABLE anthology_comment(
    id BIGINT AUTO_INCREMENT,
    author_id BIGINT NOT NULL,
    create_date        DATETIME          NOT NULL,
    update_date        DATETIME,
    content            TEXT              NOT NULL,
    parent_id BIGINT NOT NULL,
    anthology_id       BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (parent_id) REFERENCES anthology_comment (id),
    FOREIGN KEY (anthology_id) REFERENCES anthology (id)
);

CREATE TABLE author_follower(
    author_id BIGINT NOT NULL,
    follower_id BIGINT NOT NULL,
    follow_date DATETIME          NOT NULL,
    PRIMARY KEY  (author_id, follower_id),
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (follower_id) REFERENCES author (id)
);

CREATE TABLE  anthology_participant(
    author_id BIGINT NOT NULL,
    anthology_id BIGINT NOT NULL,
    participate_date DATETIME          NOT NULL,
    PRIMARY KEY  (author_id, anthology_id),
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (anthology_id) REFERENCES anthology (id)
);