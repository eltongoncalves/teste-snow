package com.snow.previsaotempo.schedule;

import com.snow.previsaotempo.model.client.EnvioPrevisaoResponse;
import com.snow.previsaotempo.model.client.WeatherResponse;
import com.snow.previsaotempo.model.entity.Usuario;
import com.snow.previsaotempo.repository.UsuarioRepository;
import com.snow.previsaotempo.service.ServicoEmail;
import com.snow.previsaotempo.service.ServicoPrevisaoTempo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.List;

@Component
public class TarefasAgendadas {

    private static final Logger logger = LoggerFactory.getLogger(TarefasAgendadas.class);
    private final ServicoPrevisaoTempo servicoPrevisaoTempo;
    private final ServicoEmail servicoEmail;
    private final UsuarioRepository usuarioRepository;
    private final TemplateEngine templateEngine;

    public TarefasAgendadas(ServicoPrevisaoTempo servicoPrevisaoTempo,
                            ServicoEmail servicoEmail,
                            UsuarioRepository usuarioRepository,
                            TemplateEngine templateEngine
                            ) {
        this.servicoPrevisaoTempo = servicoPrevisaoTempo;
        this.servicoEmail = servicoEmail;
        this.usuarioRepository = usuarioRepository;
        this.templateEngine = templateEngine;
    }


    @Scheduled(cron = "0 0 8 * * ?")
    public EnvioPrevisaoResponse enviarEmailDiario()  {

        logger.info("Iniciando tarefa agendada de envio de email");
        List<Usuario> usuarios = usuarioRepository.findAll();
        EnvioPrevisaoResponse response = new EnvioPrevisaoResponse();

        for(var usuario : usuarios) {
            WeatherResponse weatherResponse = servicoPrevisaoTempo.obterPrevisaoTempo(usuario.getLocalizacao());
            String previsao = gerarTemplateHtmlPrevisaoTempo(weatherResponse);

            logger.info("Enviando email para: {}", usuario.getEmail());
            servicoEmail.enviarEmailParaEmailHtml(usuario.getEmail(), "Previsão do Tempo para Amanhã", previsao);

            response.getDestinatarios().add(usuario.getEmail());
            response.setConteudo(response.getConteudo() + previsao);
            logger.info("Tarefa agendada de envio de email concluída com resposta: {}", response);
        }

        return response;
    }


    public String gerarTemplateHtmlPrevisaoTempo(WeatherResponse weatherResponse) {

        WeatherResponse.Forecast.ForecastDay forecastDay = weatherResponse.getForecast().getForecastday().get(1);
        WeatherResponse.Forecast.ForecastDay.Day day = forecastDay.getDay();
        String locationName = weatherResponse.getLocation().getName();
        String locationRegion = weatherResponse.getLocation().getRegion();

        Context context = new Context();
        context.setVariable("locationName", locationName);
        context.setVariable("locationRegion", locationRegion);
        context.setVariable("forecastDate", forecastDay.getDate());
        context.setVariable("forecastConditionIcon", "https:" + day.getCondition().getIcon());
        context.setVariable("forecastConditionText", day.getCondition().getText());
        context.setVariable("maxTempC", day.getMaxtemp_c());
        context.setVariable("minTempC", day.getMintemp_c());
        context.setVariable("avgTempC", day.getAvgtemp_c());
        context.setVariable("totalPrecipMm", day.getTotalprecip_mm());
        context.setVariable("uvIndex", day.getUv());
        
        return templateEngine.process("previsao-tempo", context);
    }





}
