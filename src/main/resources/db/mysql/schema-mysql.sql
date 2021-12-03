USE
`pairdesk-db`;

CREATE TABLE IF NOT EXISTS Features(
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,

    task_id INTEGER(6),

    user_id INTEGER(6),

    featureName VARCHAR(100),

    priority VARCHAR(30),

    status VARCHAR(30),

    progress INTEGER,

    description VARCHAR(300),

    deadline DATE
) engine=InnoDB;
