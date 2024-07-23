package com.snow.previsaotempo.schedule;

import com.snow.previsaotempo.model.client.EnvioPrevisaoResponse;
import com.snow.previsaotempo.model.client.WeatherResponse;
import com.snow.previsaotempo.model.entity.Usuario;
import com.snow.previsaotempo.service.ServicoEmail;
import com.snow.previsaotempo.service.ServicoPrevisaoTempo;
import com.snow.previsaotempo.repository.UsuarioRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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


//    @Scheduled(cron = "0 0/2 * * * *")
//    public EnvioPrevisaoResponse enviarEmailDiario()  {
//        logger.info("Iniciando tarefa agendada de envio de email");
//        List<Usuario> usuarios = usuarioRepository.findAll();
//        List<String> emails = usuarios.stream().map(Usuario::getEmail).toList();
//
//        EnvioPrevisaoResponse response;
//
//        if (!emails.isEmpty()) {
//            WeatherResponse weatherResponse = servicoPrevisaoTempo.obterPrevisaoTempo("Belém");
//            String previsao = gerarTemplateHtmlPrevisaoTempo(weatherResponse);
//            logger.info("Enviando email para: {}", emails);
//            servicoEmail.enviarEmailParaListaHtml(emails, "Previsão do Tempo para Amanhã", previsao);
//            response = new EnvioPrevisaoResponse(emails, previsao);
//        } else {
//            logger.info("Nenhum usuário encontrado para envio de email");
//            response = new EnvioPrevisaoResponse(emails, "Nenhuma previsão disponível");
//        }
//
//        logger.info("Tarefa agendada de envio de email concluída com resposta: {}", response);
//        return response;
//    }


    @Scheduled(cron = "0 0/2 * * * *")
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
        context.setVariable("forecastDate", forecastDay.getDate()); // Substitua com a data real
        context.setVariable("forecastConditionIcon", "https:" + day.getCondition().getIcon());
        context.setVariable("forecastConditionText", day.getCondition().getText());
        context.setVariable("maxTempC", day.getMaxtemp_c());
        context.setVariable("minTempC", day.getMintemp_c());
        context.setVariable("avgTempC", day.getAvgtemp_c()); // Ajuste conforme necessário
        context.setVariable("totalPrecipMm", day.getTotalprecip_mm()); // Ajuste conforme necessário
        context.setVariable("uvIndex", day.getUv()); // Ajuste conforme necessário
        
        return templateEngine.process("previsao-tempo", context);
    }





}
