package beebly.tools;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsTool {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC39fa940bdd1be4d3294e6cb4f449bbba";
    public static final String AUTH_TOKEN = "c92414983dd4f9e8693dfd02e36c9ba8";

    public static void envoyer(String msg,String numDest) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(numDest),
                new PhoneNumber("+15672352667"),
                msg).create();

        System.out.println(message.getSid());
    }
}