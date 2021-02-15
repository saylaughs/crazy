package com.saylaughs.crazysaylaugh.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * 邮件创建步骤:
 *
 *
 * 创建一个邮件对象（MimeMessage）； 设置发件人，收件人，可选增加多个收件人，抄送人，密送人； 设置邮件的主题（标题）； 设置邮件的正文（内容）；
 * 设置显示的发送时间； 保存到本地。
 */
public class EmailSent extends Authenticator {
    public static String myEmailSMTPHost = "smtp.qq.com";
   private static  String send =""; //"3052068273@qq.com";
    private static String pa = "ppubfvteshhndgjj";
   private static String to ="";//"501449250@qq.com";

    public static boolean  sentMail(String sendAccount,String receiveAccount,Integer num){
            send=sendAccount;
            to=receiveAccount;
        // 附件部分
//         messageBodyPart = new MimeBodyPart();
//         String filename = "file.txt";
//         DataSource source = new FileDataSource(filename);
//         messageBodyPart.setDataHandler(new DataHandler(source));
//         messageBodyPart.setFileName(filename);
//         multipart.addBodyPart(messageBodyPart);

        try {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "SMTP");// 使用协议
            props.setProperty("mail.smtp.host", myEmailSMTPHost);// 发件人邮箱服务地址
            props.setProperty("mail.smtp.auth", "true");// 需要请求认证
            props.setProperty("mail.smtp.port", "465");// ssl端口
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");
            props.setProperty("mail.user", sendAccount);
            props.setProperty("mail.password", pa);
            Session session = Session.getInstance(props, new EmailSent());
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(sendAccount));
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveAccount));
            // Set Subject: 头字段
            message.setSubject("saylaugh");
            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();
            // 消息
            messageBodyPart.setText("这是验证码："+num);
            // 创建多重消息
            Multipart multipart = new MimeMultipart();
            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);
            // 发送完整消息
            message.setContent(multipart);
            System.out.println(message);
            //   发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = this.send;
        String password = this.pa;
        if ((username != null) && (username.length() > 0) && (password != null) && (password.length() > 0)) {

            return new PasswordAuthentication(username, password);
        }

        return null;
    }
}