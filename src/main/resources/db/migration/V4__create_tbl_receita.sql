CREATE TABLE tbl_receita
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    valor                 DECIMAL(10, 2) NOT NULL,
    dt_criacao            datetime       NOT NULL,
    dt_ultima_modificacao datetime NULL,
    garagem_id            BIGINT         NOT NULL,
    CONSTRAINT pk_tbl_receita PRIMARY KEY (id)
);

ALTER TABLE tbl_receita
    ADD CONSTRAINT FK_TBL_RECEITA_ON_GARAGEM FOREIGN KEY (garagem_id) REFERENCES tbl_garagem (id);