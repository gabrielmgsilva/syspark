CREATE TABLE tbl_garagem
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    dt_criacao            datetime       NOT NULL,
    dt_ultima_modificacao datetime NULL,
    setor                 VARCHAR(2)     NOT NULL,
    preco_base            DECIMAL(10, 2) NOT NULL,
    capacidade_maxima     INT            NOT NULL,
    hora_abertura         VARCHAR(5)     NOT NULL,
    hora_fechamento       VARCHAR(5)     NOT NULL,
    duracao_limite        INT            NOT NULL,
    CONSTRAINT pk_tbl_garagem PRIMARY KEY (id)
);

CREATE TABLE tbl_vaga
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    dt_criacao            datetime       NOT NULL,
    dt_ultima_modificacao datetime NULL,
    latitude              DECIMAL(10, 6) NOT NULL,
    longitude             DECIMAL(10, 6) NOT NULL,
    garagem_id            BIGINT         NOT NULL,
    CONSTRAINT pk_tbl_vaga PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idx_garagem_vaga ON tbl_garagem (id);

CREATE UNIQUE INDEX idx_localizacao_vaga ON tbl_vaga (latitude, longitude);

ALTER TABLE tbl_vaga
    ADD CONSTRAINT FK_TBL_VAGA_ON_GARAGEM FOREIGN KEY (garagem_id) REFERENCES tbl_garagem (id);