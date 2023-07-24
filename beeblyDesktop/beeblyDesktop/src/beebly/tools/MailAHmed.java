/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.tools;

import beebly.entity.Reclamation;
import beebly.entity.Reponse;
import beebly.entity.User;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author l king ahmed
 */
public class MailAHmed {
 

   private static String username = "ahmedamri910@gmail.com";
    private static String password = "dqhqregjuphrqicw";

   public static void envoyer(User user ,Reclamation reclamation,Reponse reponse) {
// Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //Set TLS encryption enabled
        props.put("mail.smtp.host", "smtp.gmail.com");  //Set SMTP host
        props.put("mail.smtp.port", "587"); //Set smtp port
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
// Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ahmedamri910@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getAdrmail()));
            message.setSubject("Réponse à la réclamation : "+reclamation.getSujet());
            message.setText("MR/MME "+user.getNom()+" "+user.getPrenom()+" "+"concernant votre reclamation de sujet : "+reclamation.getSujet()+""
                    + "notre réponse est : "+reponse.getContenu() );
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("message sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void envoyer_modification_reponse(User user ,Reclamation reclamation,Reponse reponse) {
// Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //Set TLS encryption enabled
        props.put("mail.smtp.host", "smtp.gmail.com");  //Set SMTP host
        props.put("mail.smtp.port", "587"); //Set smtp port
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
// Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ahmedamri910@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getAdrmail()));
            message.setSubject("Réponse à la réclamation : "+reclamation.getSujet());
            message.setText("MR/MME "+user.getNom()+" "+user.getPrenom()+" "+"concernant votre reclamation de sujet : "+reclamation.getSujet()+""
                    + "notre avons modifié notre réponse la nouvelle est : "+reponse.getContenu() );
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("message sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void envoyer_suppresion_reclamation(User user ,Reclamation reclamation,Reponse reponse) {
// Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //Set TLS encryption enabled
        props.put("mail.smtp.host", "smtp.gmail.com");  //Set SMTP host
        props.put("mail.smtp.port", "587"); //Set smtp port
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
// Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ahmedamri910@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getAdrmail()));
            message.setSubject("Supression de la réclamation : "+reclamation.getSujet());
            message.setText("MR/MME "+user.getNom()+" "+user.getPrenom()+" "+"votre reclamation de sujet : "+reclamation.getSujet()+""
                    + "a été supprimée par l'administrateur "+reponse.getContenu() );
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("message sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
//Etape 4 : Tester la méthode

}

