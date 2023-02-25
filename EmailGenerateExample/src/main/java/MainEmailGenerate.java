import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class MainEmailGenerate {
    public static void main(String[] args) {
        System.out.println("Preparing to send Message.. ");
        String message="This is the mesagge for test mail";
        String subject="Test email sending to my mail id";
        String to="akhileshnegi@bidgely.com";
        String from="akhileshnegi1710@gmail.com";
sendEmail(message,subject,to,from);
    }

//    This method is responsible to send Emails..
    private static void sendEmail(String message, String subject, String to, String from) {

//        Variable for gmail host
        String host="smtp.gmail.com";

//        get the System properties
        Properties properties=System.getProperties();
        System.out.println("Properties "+properties);

        //  *******************      setting important information to properties object  *************
//        host set as key and value
        properties.put("mail.smtp.host", host);
//        port
        properties.put("mail.smtp.port", "465");
//        ssl enable
        properties.put("mail.smtp.ssl.enable", "true");
//        auth
        properties.put("mail.smtp.auth", "true");



//        Stpe 1 to get the session object

//        create object of anonymous class
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
// authentication is done after session            PasswordAuthentication has userName and password
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("akhileshnegi1710@gmail.com","fidcjzlizmfpgkyv");
            }
        });
//to debug the session
        session.setDebug(true);




//        STEP 2 : compose the message [text. image]


//        MimeMessage is the child class of Message
MimeMessage msg=new MimeMessage(session);

        try {
//from email
            msg.setFrom(from);
//Adding  recipent for single message (InternetAddress) if mail to be for all users then use array in InternetAddress
//            Message is a Parent class
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//adding subject of mail
            msg.setSubject(subject);

// ****** Adding text in mail , we can use             msg.setContent();
//            msg.setText(message);

// ****** Attachment
//File path
            String path="/Users/akhileshnegi/Documents/Pilot/PSEG/Emails/MonthlyTracker/Email/images/PSEG/EVTOU/1x/bulb.png";
            MimeMultipart mimeMultipart=new MimeMultipart();


//            for text
            MimeBodyPart textMine=new MimeBodyPart();
//            for file
            MimeBodyPart fileMine=new MimeBodyPart();

            try {
            textMine.setText(message);
            File file=new File(path);
            fileMine.attachFile(file);
//            text and file
                mimeMultipart.addBodyPart(textMine);
                mimeMultipart.addBodyPart(fileMine);
            }
            catch (Exception e)
            {


            }
            msg.setContent(mimeMultipart);
//send

//            STEP 3 send the message using Transport class
            Transport.send(msg);
            System.out.println("Sent Successfully.......");
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

    }
}
