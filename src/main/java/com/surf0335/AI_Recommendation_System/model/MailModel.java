package com.surf0335.AI_Recommendation_System.model;

import java.util.Date;

public class MailModel {
    private String id;
    private String subject;
    private String context;
    private String sender;
    private String createDateTime;

    @Override
    public String toString() {
        return "MailModel{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", context='" + context + '\'' +
                ", sender='" + sender + '\'' +
                ", createDateTime='" + createDateTime + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String title) {
        this.subject = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }
}
