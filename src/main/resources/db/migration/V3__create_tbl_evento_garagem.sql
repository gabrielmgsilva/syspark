CREATE TABLE tbl_evento_garagem
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    dt_criacao            datetime              NOT NULL,
    dt_ultima_modificacao datetime              NULL,
    placa                 VARCHAR(8)            NOT NULL,
    tipo_evento           VARCHAR(20)           NOT NULL,
    vaga_id               BIGINT                NULL,
    CONSTRAINT pk_tbl_evento_garagem PRIMARY KEY (id)
);

ALTER TABLE tbl_evento_garagem
    ADD CONSTRAINT FK_TBL_EVENTO_GARAGEM_ON_VAGA FOREIGN KEY (vaga_id) REFERENCES tbl_vaga (id);