package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private static final String TEMPLATE_VEICULO_RESERVADO = "email-veiculo-reservado";
    private static final String TEMPLATE_VEICULO_CONFIRMADO = "email-veiculo-confirmado";
    private static final String TEMPLATE_VEICULO_CANCELADO = "email-veiculo-cancelado";

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    private void sendEmail(String to, Map<String, Object> templateModel, String subject, String htmlTemplateName) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(templateModel);

        String htmlContent = templateEngine.process(htmlTemplateName, context);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // true indicates HTML content

        emailSender.send(mimeMessage);
    }

    @Override
    public void sendReserved(String to, String name, String link, String dadosVeiculo) throws MessagingException {
        Map<String, Object> templateModel = Map.of("name", name, "vehicle", dadosVeiculo, "link", link);
        sendEmail(to, templateModel, "Veículo reservado com sucesso!", TEMPLATE_VEICULO_RESERVADO);
    }

    @Override
    public void sendConfirmed(String to, String name, String dadosVeiculo) throws MessagingException {
        Map<String, Object> templateModel = Map.of("name", name, "vehicle", dadosVeiculo);
        sendEmail(to, templateModel, "Pagamento confirmado com sucesso!", TEMPLATE_VEICULO_CONFIRMADO);
    }

    @Override
    public void sendCanceled(String to, String name, String dadosVeiculo) throws MessagingException {
        Map<String, Object> templateModel = Map.of("name", name, "vehicle", dadosVeiculo);
        sendEmail(to, templateModel, "Compra cancelada", TEMPLATE_VEICULO_CANCELADO);

    }
}
