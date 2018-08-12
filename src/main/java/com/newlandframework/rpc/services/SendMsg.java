package com.newlandframework.rpc.services;

import javax.mail.MessagingException;

public interface SendMsg {


   String sendEmail(String channel, String from, String to, String subject, String content);

}
