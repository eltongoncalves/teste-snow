package com.snow.previsaotempo.model.client;

import java.util.List;

public class EnvioPrevisaoResponse {

    private List<String> destinatarios;
    private String conteudo;

    public EnvioPrevisaoResponse(List<String> destinatarios, String conteudo) {
        this.destinatarios = destinatarios;
        this.conteudo = conteudo;
    }

    public List<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "EmailResponse{" +
                "destinatarios=" + destinatarios +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }
}
