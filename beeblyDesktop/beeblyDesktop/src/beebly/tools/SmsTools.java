/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beebly.tools;

import beebly.entity.User;
import beebly.entity.Utilisateurkhaled;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

/**
 *
 * @author khaled
 */
public class SmsTools {
        public static void sendSms( User user ) {
        String ACCOUNT_SID = "AC85ebc06c236f10681a7da16a15206d9e";
        String AUTH_TOKEN = "feb5a6a3f777b0f10b33de0721394c08";
        
          Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                // to phone number
                new com.twilio.type.PhoneNumber("+216"+user.getTel()),
                new com.twilio.type.PhoneNumber("+12762888467"),
                " Votre participation a ete effectuer avec sucess !! ")
            .create();

        System.out.println(message.getSid());
        }
    
}
