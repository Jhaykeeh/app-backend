CREATE TABLE IF NOT EXISTS users (
    ID        BIGINT       NOT NULL AUTO_INCREMENT,
    SchoolID  VARCHAR(20)  NOT NULL,
    firstname VARCHAR(50),
    lastname  VARCHAR(50),
    email     VARCHAR(100) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(255) NOT NULL DEFAULT 'USER',
    PRIMARY KEY (ID),
    UNIQUE KEY (SchoolID),
    UNIQUE KEY (email)
);
