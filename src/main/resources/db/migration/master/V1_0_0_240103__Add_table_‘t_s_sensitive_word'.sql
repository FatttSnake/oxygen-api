drop table if exists t_s_sensitive_word;

create table if not exists t_s_sensitive_word
(
    id       bigint       not null primary key,
    word     varchar(400) not null comment '词',
    use_for varchar(50)  null comment '用于',
    enable   int          not null default 1 comment '启用',
    deleted  bigint       not null default 0,
    version  int          not null default 0,
    constraint t_s_sensitive_word_unique_word unique (word, deleted)
) comment '系统-敏感词表';