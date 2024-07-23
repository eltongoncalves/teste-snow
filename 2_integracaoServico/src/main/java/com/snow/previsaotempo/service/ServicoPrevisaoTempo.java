package com.snow.previsaotempo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.previsaotempo.model.client.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicoPrevisaoTempo {

    private final RestTemplate restTemplate;
    @Value("${weather.api.key}")
    private  String chaveApi;
    private final ObjectMapper objectMapper;

    public ServicoPrevisaoTempo(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }
    public WeatherResponse obterPrevisaoTempo(String localizacao) {
        try {
            String resposta = restTemplate.getForObject(buildUrl(localizacao), String.class);
            return objectMapper.readValue(resposta, WeatherResponse.class);
        } catch (Exception e) {
            throw new  RuntimeException("Não foi possível obter a previsão do tempo.", e);
        }
    }

    private String buildUrl(String localizacao) {
        return String.format("https://api.weatherapi.com/v1/forecast.json?key=%s&q=%s&days=7", chaveApi, localizacao);
    }
}
