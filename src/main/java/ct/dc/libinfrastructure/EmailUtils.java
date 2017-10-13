package ct.dc.libinfrastructure;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by CTWLPC on 2017/10/12.
 */
public class EmailUtils {

    private String mailHost;
    private boolean auth;
    private int port;
    private boolean isDebug;
    private String userName;
    private String passWord;
    private String protocolType;

    private MimeMessage mimeMessage;
    private Session session;
    public EmailUtils(String emailConfigPath) throws IOException {
        FileInputStream fis = new FileInputStream(emailConfigPath);
        Properties properties = new Properties();
        properties.load(fis);
        protocolType = properties.getProperty("mail.type");
        this.mailHost = properties.getProperty(String.format("mail.%s.host",protocolType));
        this.auth = Boolean.valueOf(properties.getProperty(String.format("mail.%s.auth",protocolType)));
        this.port = Integer.valueOf(properties.getProperty(String.format("mail.%s.port",protocolType)));
        this.userName = properties.getProperty("mail.sender.username");
        this.passWord = properties.getProperty("mail.sender.password");
        this.isDebug = Boolean.valueOf(properties.getProperty("mail.debug", "false"));


        System.out.println(protocolType);
        System.out.println(mailHost);
        System.out.println(auth);
        System.out.println(userName);
        System.out.println(passWord);

        Properties pro = new Properties();
        pro.put(String.format("mail.%s.host",protocolType), mailHost);
        pro.put(String.format("mail.%s.port",protocolType), this.port);
        pro.put(String.format("mail.%s.auth",protocolType), auth);
        pro.put("mail.sender.username", userName);
        pro.put("mail.sender.password", passWord);
        if (protocolType.equals("smtp")){
            pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            pro.put("mail.smtp.socketFactory.port", port);
            pro.put("mail.smtp.socketFactory.fallback", "false");
        }
        session = Session.getDefaultInstance(pro, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName,passWord);
            }
        });
        session.setDebug(isDebug);
        mimeMessage = new MimeMessage(session);
    }

    /**
     * 发送邮件
     * @param subject
     * @param content
     * @param receivers
     */
    public void sendEmail(String subject,String content,String[] receivers) throws MessagingException {
        mimeMessage.setFrom(new InternetAddress(userName));
        for (String receiver:receivers){
            mimeMessage.addRecipients(Message.RecipientType.TO, receiver);
        }
        mimeMessage.setSubject(subject);
        mimeMessage.setContent(content,"text/html;charset=UTF-8");
        mimeMessage.saveChanges();
        Transport transport= session.getTransport(protocolType);
        transport.connect(mailHost, port, userName, passWord);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
    }

    /**
     * 发送邮件
     * @param subject
     * @param content
     * @param attachmentFile
     * @param receivers
     */
    public void sendEmail(String subject, String content,String attachmentFile,String[] receivers) throws MessagingException, UnsupportedEncodingException {
        mimeMessage.setFrom(new InternetAddress(userName));
        for (String receiver:receivers){
            mimeMessage.addRecipients(Message.RecipientType.TO, receiver);
        }
        mimeMessage.setSubject(subject);

        Multipart multipart = new MimeMultipart();

        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(content,"text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);

        File file = new File(attachmentFile);
        if (file.exists()){
            BodyPart attachmentPart = new MimeBodyPart();
            DataSource dataSource = new FileDataSource(file);
            attachmentPart.setDataHandler(new DataHandler(dataSource));
            attachmentPart.setFileName(MimeUtility.encodeWord(file.getName()));
            multipart.addBodyPart(attachmentPart);
        }

        mimeMessage.setContent(multipart);
        mimeMessage.saveChanges();
        Transport transport= session.getTransport(protocolType);
        transport.connect(mailHost, port, userName, passWord);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
    }
}
