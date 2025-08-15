package com.nit.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service("purchaseservice")
public class PurchaseOrderImpl implements IpurchseOrder {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public String purchse(String[] items, double[] prices, String[] toEmails) throws Exception {
        double billamt = 0.0;
        for (double p : prices) {
            billamt += p;
        }

        String msg = Arrays.toString(items) + " with prices " +
                     Arrays.toString(prices) +
                     " are purchased with bill amount " + billamt;

        String result = sendMail(msg, toEmails);
        return msg + " -----> " + result;
    }

    private String sendMail(String msg, String[] toEmails) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromEmail);
        helper.setTo(toEmails); 
        helper.setSubject("Open it to know it");
        helper.setSentDate(new Date());
        helper.setText(msg);
        helper.addAttachment("nit.jpg", new ClassPathResource("nit.jpg"));

        sender.send(message);
        return "mail sent";
    }
}
