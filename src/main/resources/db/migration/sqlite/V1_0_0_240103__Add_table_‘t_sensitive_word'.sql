drop table if exists t_sensitive_word;

create table if not exists t_sensitive_word -- 敏感词表
(
    id      integer not null primary key,
    word    text    not null unique,   -- 词
    use_for text    null,              -- 用于
    enable  integer not null default 1 -- 启用
);
