package com.snow.previsaotempo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ServicoEmail {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarEmail(String para, String assunto, String texto) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom("seu-email@example.com");
        mensagem.setTo(para);
        mensagem.setSubject(assunto);
        mensagem.setText(texto);
        emailSender.send(mensagem);
    }
}
