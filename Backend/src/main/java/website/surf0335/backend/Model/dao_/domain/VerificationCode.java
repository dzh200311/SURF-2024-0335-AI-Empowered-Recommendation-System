package website.surf0335.backend.Model.dao_.domain;

import java.sql.Timestamp;
import java.util.Date;

public class VerificationCode {
    private Long id;
    private String email;
    private String code;
    private Timestamp time; // Change the type to Timestamp
    private int type;

    public VerificationCode() {
    }

    public VerificationCode(Long id, String email, String code, Timestamp time, int type) {
        this.id = id;
        this.email = email;
        this.code = code;
        this.time = time;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}