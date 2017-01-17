package ua.nure.tatarinov.SummaryTask4.core;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyUserDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class Utils {

    public static final Logger LOG = Logger.getLogger(Utils.class);

    public static boolean isDigit(String string) {
        if (string == null || string.length() == 0) return false;

        int i = 0;
        if (string.charAt(0) == '-') {
            if (string.length() == 1) {
                return false;
            }
            i = 1;
        }

        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

    public static String encrypt(String pass) {
        StringBuilder hex = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(pass.getBytes("UTF-8"));
            byte[] hash = digest.digest();
            for (int i = 0; i < hash.length; i++) {
                hex.append(String.format("%02x", hash[i]));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return hex.toString();
    }

    public static void sendMail(String recipient, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", 465);
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("kurson.aaa", "remdigga4237");
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("kurson.aaa@yandex.ua"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject("Courses");
            // set content
            message.setContent(text, "text/html; charset=utf-8");

            Transport.send(message);
            LOG.info("Message was sent to -->" + recipient);

        } catch (MessagingException ex) {
            LOG.error("Message wasn't sent to email -->" + recipient, ex);

            System.out.println("Message wasn't sent to email -->" + recipient);
        }

    }

    public static String generateMessage(String login) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890";
        UserDTO user = new DerbyUserDAO().findUserByLogin(login);
        StringBuilder newPass = new StringBuilder();
        int count = (int) ((Math.random() * 30) + 4);
        for (int i = 0; i < count; i++) {
            newPass.append(alphabet.charAt((int) (Math.random() * alphabet.length())));
        }
        String encryptPass = Utils.encrypt(String.valueOf(newPass));
        new DerbyUserDAO().setNewPassword(user.getIdUser(), String.valueOf(encryptPass));
        StringBuffer textMail = new StringBuffer();
        textMail.append("<h4>Hi! Your new login details:<br><br>").append("login: ")
                .append(user.getLogin()).append("<br>password: ").append(newPass)
                .append("<br><br><br><i>Sincerelly yours, Team Course.<i><h4>");
        return textMail.toString();
    }
}