package com.snow.previsaotempo.schedule;

import com.snow.previsaotempo.model.entity.Usuario;
import com.snow.previsaotempo.service.ServicoEmail;
import com.snow.previsaotempo.service.ServicoPrevisaoTempo;
import com.snow.previsaotempo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TarefasAgendadas {

    private static final Logger logger = LoggerFactory.getLogger(TarefasAgendadas.class);


    @Autowired
    private ServicoPrevisaoTempo servicoPrevisaoTempo;

    @Autowired
    private ServicoEmail servicoEmail;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Scheduled(cron = "*/60 * * * * *")
    public void enviarEmailDiario() {
        logger.info("Iniciando tarefa agendada de envio de email");
        List<Usuario> usuarios = usuarioRepository.findAll();
        String previsao = servicoPrevisaoTempo.obterPrevisaoTempo("Belém");
        for (Usuario usuario : usuarios) {
            logger.info("Enviando email para: {}", usuario.getEmail());
            servicoEmail.enviarEmail(usuario.getEmail(), "Previsão do Tempo para Amanhã", previsao);
        }
        logger.info("Tarefa agendada de envio de email concluída");
    }
}
