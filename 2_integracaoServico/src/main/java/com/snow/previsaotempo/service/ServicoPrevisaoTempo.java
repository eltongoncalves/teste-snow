package com.snow.previsaotempo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.previsaotempo.model.client.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicoPrevisaoTempo {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String chaveApi = "d04c1a7db73948b396b51427242207";

    public String obterPrevisaoTempo(String localizacao) {
        try {
            String urlApi = "https://api.weatherapi.com/v1/forecast.json?key=" + chaveApi + "&q="+localizacao+"&days=2";
            String resposta = restTemplate.getForObject(urlApi, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            WeatherResponse weatherResponse = objectMapper.readValue(resposta, WeatherResponse.class);

            WeatherResponse.Forecast.ForecastDay forecastDay = weatherResponse.getForecast().getForecastday().get(1); // Pegue o segundo dia
            WeatherResponse.Forecast.ForecastDay.Day day = forecastDay.getDay();

            return "Previsão para " + forecastDay.getDate() + ": " + day.getCondition().getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Não foi possível obter a previsão do tempo.";
        }
    }
}
