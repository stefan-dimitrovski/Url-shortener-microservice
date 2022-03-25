CREATE TABLE short_urls
(
    short_url    BIGSERIAL PRIMARY KEY NOT NULL,
    original_url text                  NOT NULL
);