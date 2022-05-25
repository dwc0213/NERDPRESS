CREATE TABLE member(
    id          NUMBER(4)	PRIMARY KEY,
    userId      VARCHAR2(10)NOT NULL UNIQUE,
    name        VARCHAR2(10)NOT NULL,
    PW          VARCHAR2(20)NOT NULL,
    email       VARCHAR2(50)NOT NULL,
    birthDate   NUMBER(8)   NOT NULL,
    grade       NUMBER(1)   NOT NULL,
    male        NUMBER(1)   NOT NULL
);
CREATE SEQUENCE auto_mem_id START WITH 1 INCREMENT BY 1 MAXVALUE 9999 CYCLE NOCACHE;

CREATE TABLE novel(
    id          NUMBER(4)	    PRIMARY KEY,
    mem_id      NUMBER(4)       NOT NULL,
    name        VARCHAR2(10)    NOT NULL,
    w_name      VARCHAR2(10)    NOT NULL, --새로추가(필명)
    s_cycle     NUMBER(5)       ,         --새로추가(작품 주기, 단위:일)
    genre       VARCHAR2(10)    NOT NULL,
    recoms       NUMBER(10)     NOT NULL, --추천수
    dateNTIME  NUMBER(12)       NOT NULL,
    explanation VARCHAR2(100)   ,
    CONSTRAINT fk_mem_id_novel FOREIGN KEY(mem_id) REFERENCES member(id));
CREATE SEQUENCE auto_novel_id START WITH 1 INCREMENT BY 1 MAXVALUE 9999 CYCLE NOCACHE;

CREATE TABLE episodes(
    id          NUMBER(8)	PRIMARY KEY,
    novel_id    NUMBER(4)   NOT NULL,
    subtitle    VARCHAR2(20),
    genre       VARCHAR2(10)NOT NULL,
    chapter     NUMBER(3)   NOT NULL,
    dateNTime   NUMBER(12)  NOT NULL,
    Content     VARCHAR(1000) NOT NULL,
    recoms      NUMBER(5)   NOT NULL, --추천수
    disrecoms   NUMBER(5)   NOT NULL, --비추천수
    isReportedNWhy NUMBER(8) NOT NULL,
    --순서대로 음란물, 성의없음, 욕설 및 분란조장, 기타사유로 구분됨.
    --음란물으로 2번, 성의없음으로 3번 신고되면 02030000으로 저장.
    CONSTRAINT fk_novel_id_episodes FOREIGN KEY(novel_id) REFERENCES NOVEL(id)
);
CREATE SEQUENCE auto_episode_id START WITH 1 INCREMENT BY 1 MAXVALUE 999999 CYCLE NOCACHE;

CREATE TABLE m_detail(
    id          NUMBER(4)	    PRIMARY KEY,
    mem_id      NUMBER(4)       NOT NULL,
    point       NUMBER(10)      NOT NULL, --포인트 양
    CONSTRAINT fk_mem_id_detail FOREIGN KEY(mem_id) REFERENCES member(id)
);
CREATE SEQUENCE auto_detail_id START WITH 1 INCREMENT BY 1 MAXVALUE 9999 CYCLE NOCACHE;

CREATE TABLE bookmarks(
    id          number(8)       PRIMARY KEY,
    mem_id      number(4)       NOT NULL,
    episode_id  number(8)       NOT NULL,
    CONSTRAINT fk_mem_id_bookmarks FOREIGN KEY(mem_id) REFERENCES member(id),
    CONSTRAINT fk_episode_id_bookmarks FOREIGN KEY(episode_id) REFERENCES episodes(id)
);
CREATE SEQUENCE auto_bookmark_id START WITH 1 INCREMENT BY 1 MAXVALUE 999999 CYCLE NOCACHE;

CREATE TABLE comments(
    id          number(12)      PRIMARY KEY,
    episode_id  number(8)       NOT NULL,
    mem_id      number(4)       NOT NULL,
    content     varchar2(150)   NOT NULL,
    CONSTRAINT fk_mem_id_comments FOREIGN KEY(mem_id) REFERENCES member(id),
    CONSTRAINT fk_episode_id_comments FOREIGN KEY(episode_id) REFERENCES episodes(id)
);
CREATE SEQUENCE auto_comment_id START WITH 1 INCREMENT BY 1 MAXVALUE 99999999 CYCLE NOCACHE;

CREATE TABLE license(
    License_id number(4)        PRIMARY KEY,  -- 자격증 iD
    License_type varchar2(1000) NOT NULL                    -- 자격증 종류
);
CREATE SEQUENCE auto_license_id START WITH 1 INCREMENT BY 1 MAXVALUE 9999 CYCLE NOCACHE;

CREATE TABLE quest(
    Quest_id                    number(4) PRIMARY KEY, -- 문제 ID
    License_id                  number(4) NOT NULL,                                     -- 자격증 종류 (FK)
    Quest_title                 varchar2(300) NOT NULL,                                 -- 문제 설명
    Q_one                       varchar2(100) NOT NULL,                                 -- 정답 보기 1번
    Q_two                       varchar2(100) NOT NULL,                                 -- 정답 보기 2번
    Q_three                     varchar2(100) NOT NULL,                                 -- 정답 보기 3번
    Q_four                      varchar2(100) NOT NULL,                                 -- 정답 보기 4번
    Quest_answer                number(4) NOT NULL,                                     -- 정답 번호
    CONSTRAINT fk_license_id_quest FOREIGN KEY(License_id) REFERENCES license(License_id)
);
CREATE SEQUENCE auto_quest_id START WITH 1 INCREMENT BY 1 MAXVALUE 9999 CYCLE NOCACHE;