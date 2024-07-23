package com.snow.previsaotempo.schedule;

import com.snow.previsaotempo.service.ServicoEmail;
import com.snow.previsaotempo.service.ServicoPrevisaoTempo;
import com.snow.previsaotempo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TarefasAgendadas {

    @Autowired
    private ServicoPrevisaoTempo servicoPrevisaoTempo;

    @Autowired
    private ServicoEmail servicoEmail;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Scheduled(cron = "0 0 8 * * ?") // Executa diariamente às 8h da manhã
    public void enviarEmailDiario() {
        String previsaoTempo = servicoPrevisaoTempo.obterPrevisaoTempo("LOCALIZAÇÃO");
        usuarioRepository.findAll().forEach(usuario -> {
            servicoEmail.enviarEmail(usuario.getEmail(), "Previsão do Tempo para Amanhã", previsaoTempo);
        });
    }
}
