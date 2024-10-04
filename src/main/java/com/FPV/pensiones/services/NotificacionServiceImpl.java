package com.FPV.pensiones.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificacionServiceImpl implements NotificacionService {

    private final JavaMailSender mailSender;
    private final SmsSender smsSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${spring.mail.host}")
    private String emailHost;

    @Value("${spring.mail.port}")
    private int emailPort;

    @Override
    public void enviarNotificacion(String destinatario, String mensaje, String tipoNotificacion) {
        log.info("Iniciando envío de notificación tipo {} a {}", tipoNotificacion, destinatario);
        try {
            if ("EMAIL".equals(tipoNotificacion)) {
                enviarEmail(destinatario, mensaje);
            } else if ("SMS".equals(tipoNotificacion)) {
                enviarSMS(destinatario, mensaje);
            } else {
                log.warn("Tipo de notificación no soportado: {}", tipoNotificacion);
                throw new IllegalArgumentException("Tipo de notificación no soportado: " + tipoNotificacion);
            }
            log.info("Notificación enviada exitosamente a {}", destinatario);
        } catch (MailException e) {
            log.error("Error al enviar el correo electrónico a {}: {}", destinatario, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al enviar el correo electrónico: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error al enviar la notificación a {}: {}", destinatario, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al enviar la notificación: " + e.getMessage(), e);
        }
    }

    private void enviarEmail(String destinatario, String mensaje) {
        log.info("Preparando envío de email a: {}", destinatario);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailFrom);
        mailMessage.setTo(destinatario);
        mailMessage.setSubject("Notificación Importante");
        mailMessage.setText(mensaje);

        log.debug("Configuración de correo: host={}, port={}, username={}",
                emailHost, emailPort, emailFrom);

        try {
            log.info("Intentando enviar email...");
            mailSender.send(mailMessage);
            log.info("Email enviado exitosamente a {}", destinatario);
        } catch (MailException e) {
            log.error("Error al enviar email a {}: {}", destinatario, e.getMessage(), e);
            throw e;
        }
    }

    private void enviarSMS(String destinatario, String mensaje) {
        log.info("Intentando enviar SMS a: {}", destinatario);
        try {
            smsSender.sendSms(destinatario, mensaje);
            log.info("SMS enviado exitosamente a {}", destinatario);
        } catch (Exception e) {
            log.error("Error al enviar SMS a {}: {}", destinatario, e.getMessage(), e);
            throw new RuntimeException("Error al enviar SMS: " + e.getMessage(), e);
        }
    }
}