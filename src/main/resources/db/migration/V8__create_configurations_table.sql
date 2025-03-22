CREATE TABLE configurations
(
    id           VARCHAR(36) PRIMARY KEY,
    config_key   VARCHAR(255) NOT NULL UNIQUE,
    config_value VARCHAR(255) NOT NULL,
    version      INT          NOT NULL DEFAULT 0,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP
);