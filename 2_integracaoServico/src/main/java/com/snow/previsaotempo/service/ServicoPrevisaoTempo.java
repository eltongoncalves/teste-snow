package com.snow.previsaotempo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.previsaotempo.model.client.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class ServicoPrevisaoTempo {

    @Autowired
    private TemplateEngine templateEngine;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String chaveApi = "d04c1a7db73948b396b51427242207";

    public String obterPrevisaoTempo(String localizacao) {
        try {
            String urlApi = "https://api.weatherapi.com/v1/forecast.json?key=" + chaveApi + "&q=" + localizacao + "&days=7";
            String resposta = restTemplate.getForObject(urlApi, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            WeatherResponse weatherResponse = objectMapper.readValue(resposta, WeatherResponse.class);
            WeatherResponse.Forecast.ForecastDay forecastDay = weatherResponse.getForecast().getForecastday().get(1); // Pegue o segundo dia
            WeatherResponse.Forecast.ForecastDay.Day day = forecastDay.getDay();
//            return "Previsão para " + forecastDay.getDate() + ": " + day.getCondition().getText();
           return  enviarEmailParaListaHtml(weatherResponse.getCurrent(), day, weatherResponse.getLocation().getName(), weatherResponse.getLocation().getRegion());
        } catch (Exception e) {
            e.printStackTrace();
            return "Não foi possível obter a previsão do tempo.";
        }
    }


    public String enviarEmailParaListaHtml(WeatherResponse.Current current,
                                           WeatherResponse.Forecast.ForecastDay.Day day, String locationName, String locationRegion) {

//        Context thymeleafContext = new Context();
//        thymeleafContext.setVariables(thymeleafContext);
//        String conteudoHtml = templateEngine.process("email-template", thymeleafContext);
//


        Context context = new Context();
        context.setVariable("locationName", locationName);
        context.setVariable("locationRegion", locationRegion);
        context.setVariable("currentConditionIcon", "https:" + current.getCondition().getIcon());
        context.setVariable("currentConditionText", current.getCondition().getText());
        context.setVariable("currentTempC", current.getTemp_c());
        context.setVariable("currentFeelsLikeC", day.getCondition().getText()); // Ajuste conforme necessário
        context.setVariable("currentHumidity", 95); // Dados de exemplo; ajuste conforme necessário
        context.setVariable("currentWindMph", 5.1); // Dados de exemplo; ajuste conforme necessário
        context.setVariable("currentPressureMb", 1014.0); // Dados de exemplo; ajuste conforme necessário
        context.setVariable("forecastDate", "2024-07-23"); // Substitua com a data real
        context.setVariable("forecastConditionIcon", "https:" + day.getCondition().getIcon());
        context.setVariable("forecastConditionText", day.getCondition().getText());
        context.setVariable("maxTempC", day.getMaxtemp_c());
        context.setVariable("minTempC", day.getMintemp_c());
        context.setVariable("avgTempC", "26.3"); // Ajuste conforme necessário
        context.setVariable("totalPrecipMm", 4.64); // Ajuste conforme necessário
        context.setVariable("uvIndex", 11.0); // Ajuste conforme necessário
        return templateEngine.process("previsao-tempo", context);
    }

}