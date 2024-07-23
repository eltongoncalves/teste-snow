package com.snow.previsaotempo.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnvioPrevisaoResponse {

    private List<String> destinatarios =  new ArrayList<>();
    private String conteudo;

    @Override
    public String toString() {
        return "EmailResponse{" +
                "destinatarios=" + destinatarios +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }
}
