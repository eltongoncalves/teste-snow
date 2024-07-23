package com.snow.previsaotempo.controller;

import com.snow.previsaotempo.model.client.WeatherResponse;
import com.snow.previsaotempo.service.ServicoPrevisaoTempo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrevisaoTempoController {

    @Autowired
    private ServicoPrevisaoTempo servicoPrevisaoTempo;

    @GetMapping("/previsao-tempo")
    public String obterPrevisaoTempo(@RequestParam String localizacao) throws Exception {
        return servicoPrevisaoTempo.obterPrevisaoTempo(localizacao);
    }
}
