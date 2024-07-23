package com.snow.previsaotempo.schedule;

import com.snow.previsaotempo.model.client.EnvioPrevisaoResponse;
import com.snow.previsaotempo.model.entity.Usuario;
import com.snow.previsaotempo.service.ServicoEmail;
import com.snow.previsaotempo.service.ServicoPrevisaoTempo;
import com.snow.previsaotempo.repository.UsuarioRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TarefasAgendadas {

    private static final Logger logger = LoggerFactory.getLogger(TarefasAgendadas.class);
    private final ServicoPrevisaoTempo servicoPrevisaoTempo;
    private final ServicoEmail servicoEmail;
    private final UsuarioRepository usuarioRepository;

    public TarefasAgendadas(ServicoPrevisaoTempo servicoPrevisaoTempo,
                            ServicoEmail servicoEmail,
                            UsuarioRepository usuarioRepository) {
        this.servicoPrevisaoTempo = servicoPrevisaoTempo;
        this.servicoEmail = servicoEmail;
        this.usuarioRepository = usuarioRepository;
    }

    @Scheduled(cron = "0 0/2 * * * *")
    public EnvioPrevisaoResponse enviarEmailDiario()  {
        logger.info("Iniciando tarefa agendada de envio de email");
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<String> emails = usuarios.stream().map(Usuario::getEmail).toList();

        EnvioPrevisaoResponse response;

        if (!emails.isEmpty()) {
            String previsao = servicoPrevisaoTempo.obterPrevisaoTempo("Belém");
            logger.info("Enviando email para: {}", emails);
            servicoEmail.enviarEmailParaListaHtml(emails, "Previsão do Tempo para Amanhã", previsao);

            response = new EnvioPrevisaoResponse(emails, previsao);
        } else {
            logger.info("Nenhum usuário encontrado para envio de email");
            response = new EnvioPrevisaoResponse(emails, "Nenhuma previsão disponível");
        }

        logger.info("Tarefa agendada de envio de email concluída com resposta: {}", response);
        return response;
    }





}
