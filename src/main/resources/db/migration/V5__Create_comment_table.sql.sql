CREATE TABLE comment
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parent_id BIGINT NOT NULL,
    type INT NOT NULL,
    commentator BIGINT NOT NULL,
    gmt_create BIGINT NOT NULL,
    gmt_modified BIGINT NOT NULL,
    comment_count int DEFAULT 0,
    content VARCHAR(1024) NULL,
    like_count BIGINT DEFAULT 0
)
