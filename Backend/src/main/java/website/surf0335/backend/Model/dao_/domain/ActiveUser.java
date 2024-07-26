package website.surf0335.backend.Model.dao_.domain;

import java.sql.Timestamp;

public class ActiveUser {
    private Integer active_user_id;
    private Timestamp last_login_time;
    private String ip;

    public ActiveUser() {
    }

    public ActiveUser(int active_user_id, Timestamp last_login_time, String ip) {
        this.active_user_id = active_user_id;
        this.last_login_time = last_login_time;
        this.ip = ip;
    }

    public int getActive_user_id() {
        return active_user_id;
    }

    public void setActive_user_id(int active_user_id) {
        this.active_user_id = active_user_id;
    }

    public Timestamp getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Timestamp last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "ActiveUser{" +
                "active_user_id=" + active_user_id +
                ", last_login_time=" + last_login_time +
                ", ip='" + ip + '\'' +
                '}';
    }
}
