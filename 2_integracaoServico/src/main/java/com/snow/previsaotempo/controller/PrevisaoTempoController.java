package com.snow.previsaotempo.controller;

import com.snow.previsaotempo.model.client.EnvioPrevisaoResponse;
import com.snow.previsaotempo.model.client.WeatherResponse;
import com.snow.previsaotempo.schedule.TarefasAgendadas;
import com.snow.previsaotempo.service.ServicoPrevisaoTempo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrevisaoTempoController {

    private final ServicoPrevisaoTempo servicoPrevisaoTempo;
    private final TarefasAgendadas tarefasAgendadas;

    public PrevisaoTempoController(ServicoPrevisaoTempo servicoPrevisaoTempo, TarefasAgendadas tarefasAgendadas) {
        this.servicoPrevisaoTempo = servicoPrevisaoTempo;
        this.tarefasAgendadas = tarefasAgendadas;
    }

    @GetMapping("/previsao-tempo")
    public WeatherResponse obterPrevisaoTempo(@RequestParam String localizacao) {
        return servicoPrevisaoTempo.obterPrevisaoTempo(localizacao);
    }

    @GetMapping("/previsao-tempo/envio")
    public EnvioPrevisaoResponse envioEmailPrevisaoTempo()  {
        return tarefasAgendadas.enviarEmailDiario();
    }
}
