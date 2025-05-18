package com.example.demo.domain.enums;

public enum SituacaoVaga {

    LIVRE("LIVRE"),
    OCUPADA("OCUPADA");

    private String situacao;

    SituacaoVaga(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public static SituacaoVaga fromDescricao(String situacaoVaga) {
        for (SituacaoVaga situacao : SituacaoVaga.values()) {
            if (situacao.getSituacao().equalsIgnoreCase(situacaoVaga)) {
                return situacao;
            }
        }
        throw new IllegalArgumentException("Situação inválida: " + situacaoVaga);
    }
}
