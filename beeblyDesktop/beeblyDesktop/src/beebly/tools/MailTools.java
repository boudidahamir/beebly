/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beebly.tools;


import beebly.entity.User;
import beebly.entity.Utilisateurkhaled;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author khaled
 */
public class MailTools {
    
    
    
    private static String username = "khaledaziz.Baccouche@esprit.tn";
    private static String password = "muzioahovwovmcmb";

    public static void sendMail(User user) {
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
            message.setFrom(new InternetAddress("khaledaziz.baccouche@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getAdrmail()));
            message.setSubject("Avertissement");
            message.setText("MR/MME "+user.getNom()+" "+user.getPrenom()+" "+" votre participation a ete effecter avec sucess");
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("message sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
