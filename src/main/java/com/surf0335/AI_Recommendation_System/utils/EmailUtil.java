package com.surf0335.AI_Recommendation_System.utils;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.surf0335.AI_Recommendation_System.model.MailModel;

import javax.mail.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailUtil {
    public static List<MailModel> readMail(String username,String password,String host,String port,String protocol) {
        Properties properties = new Properties();
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", port);
        properties.put("mail.imap.protocol", protocol);
        properties.put("mail.imap.ssl.enable", "true");
        Session session = Session.getDefaultInstance(properties);
        ArrayList<MailModel> results = new ArrayList<>();
        try {
            Store store = session.getStore(protocol);
            store.connect(host, username, password);
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_ONLY);
            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                String subject = message.getSubject();
                String messageText = "";
                if (message.getContent() instanceof Multipart) {
                    Multipart multipart = (Multipart) message.getContent();
                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);
                        if (bodyPart.getContentType().contains("text/plain")) {
                            messageText = (String) bodyPart.getContent();
                            break;
                        }
                    }
                } else if (message.getContent() instanceof String) {
                    messageText = (String) message.getContent();
                }
                MailModel mailModel = new MailModel();
                mailModel.setSubject("".equals(subject) ? "空" : subject);
                mailModel.setContext("".equals(messageText) ? "空" : messageText);
                mailModel.setCreateDateTime(DateUtil.formatDateTime(message.getReceivedDate()));
                String sender = StrUtil.removeSuffix(StrUtil.subAfter(message.getFrom()[0].toString(), "@", true),">");
                mailModel.setSender("".equals(sender) ? "空" : sender);
                results.add(mailModel);
            }

            inbox.close(false);
            store.close();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return results;
        }

    }

}
