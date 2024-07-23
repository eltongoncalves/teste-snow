package com.snow.previsaotempo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicoEmail {

    private final JavaMailSender emailSender;

    public ServicoEmail(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void enviarEmailParaLista(List<String> destinatarios, String assunto, String conteudo) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(destinatarios.toArray(new String[0]));
        mensagem.setSubject(assunto);
        mensagem.setText(conteudo);
        emailSender.send(mensagem);
    }

    public void enviarEmailParaListaHtml(List<String> destinatarios, String assunto, String conteudoHtml) {
        MimeMessage mensagem = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);
            helper.setTo(destinatarios.toArray(new String[0]));
            helper.setSubject(assunto);
            helper.setText(conteudoHtml, true); // Define o conteúdo como HTML
            emailSender.send(mensagem);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailParaEmailHtml(String destinatario, String assunto, String conteudoHtml) {
        MimeMessage mensagem = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(conteudoHtml, true); // Define o conteúdo como HTML
            emailSender.send(mensagem);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
