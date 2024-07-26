package website.surf0335.backend.Model.dao_.domain;

import java.io.Serializable;

public class ToEmail implements Serializable {
    private String tos;
    private String subject;
    private String content;

    public ToEmail() {
        // Default constructor
    }

    public ToEmail(String tos, String subject, String content) {
        this.tos = tos;
        this.subject = subject;
        this.content = content;
    }

    public String getTos() {
        return tos;
    }

    public void setTos(String tos) {
        this.tos = tos;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}

