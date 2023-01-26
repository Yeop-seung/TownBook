package com.ssafy.townbook.model.service;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    String sendSimpleMessage(String to)throws Exception;

    MimeMessage createMessage(String to)throws Exception;

    MimeMessage createPasswordMessage(String to, String password)throws Exception;

    String getTmpPassword();

    void sendPasswordMessage(String to, String password)throws Exception;


}
