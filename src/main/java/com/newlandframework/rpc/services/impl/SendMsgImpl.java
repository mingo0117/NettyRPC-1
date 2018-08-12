package com.newlandframework.rpc.services.impl;

import com.newlandframework.rpc.services.SendMsg;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMsgImpl implements SendMsg {
    @Override
    public String sendEmail(String channel, String from, String to, String subject, String content){
        try {
            switch (channel){
                case "126":
                    return sendEmail126(from,to,subject,content);
                default:
                    return null;
        }}catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String sendEmail126(String from, String to, String subject, String content) throws MessagingException {
        Properties prop=new Properties();
        prop.put("mail.host","smtp.126.com" );
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", true);
        //使用java发送邮件5步骤
        //1.创建sesssion
        Session session=Session.getInstance(prop);
        //开启session的调试模式，可以查看当前邮件发送状态
        session.setDebug(true);

        //3.通过邮件用户名密码链接

        //2.通过session获取Transport对象（发送邮件的核心API）
        Transport ts=session.getTransport();
        ts.connect(from, "xzbb5201");
        //4.创建邮件
        MimeMessage mm=new MimeMessage(session);
        //设置发件人
        mm.setFrom(new InternetAddress(from));
        //设置收件人
        mm.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //设置抄送人
        //mm.setRecipient(Message.RecipientType.CC, new InternetAddress(""));

        mm.setSubject(subject);
        mm.setContent(content, "text/html;charset=utf-8");

        //5.发送电子邮件
        ts.sendMessage(mm, mm.getAllRecipients());
        String ret = "sendEmail126 success!";
        System.out.println(ret);
        return ret;
    }

}
