package website.surf0335.backend.Model.dao_.domain;

public class User {
    private Integer user_id;
    private String userName;
    private String fullName;
    private String email;
    private String passwordHash;
    private String phone;
    private Integer enable;
    private String github_login;
    private String facebook_login;
    private String google_login;
    private Integer membership;
    private String avatar;
    private Integer credits;
    private Integer visa_pay_id;

    public User() {
    }

    public User(Integer user_id, String userName, String fullName, String email, String passwordHash, String phone, Integer enable, String github_login, String facebook_login, String google_login, Integer membership, String avatar, Integer credits, Integer visa_pay_id) {
        this.user_id = user_id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.enable = enable;
        this.github_login = github_login;
        this.facebook_login = facebook_login;
        this.google_login = google_login;
        this.membership = membership;
        this.avatar = avatar;
        this.credits = credits;
        this.visa_pay_id = visa_pay_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getGithub_login() {
        return github_login;
    }

    public void setGithub_login(String github_login) {
        this.github_login = github_login;
    }

    public String getFacebook_login() {
        return facebook_login;
    }

    public void setFacebook_login(String facebook_login) {
        this.facebook_login = facebook_login;
    }

    public String getGoogle_login() {
        return google_login;
    }

    public void setGoogle_login(String google_login) {
        this.google_login = google_login;
    }

    public Integer getMembership() {
        return membership;
    }

    public void setMembership(Integer membership) {
        this.membership = membership;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getVisa_pay_id() {
        return visa_pay_id;
    }

    public void setVisa_pay_id(Integer visa_pay_id) {
        this.visa_pay_id = visa_pay_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", phone='" + phone + '\'' +
                ", enable=" + enable +
                ", github_login='" + github_login + '\'' +
                ", facebook_login='" + facebook_login + '\'' +
                ", google_login='" + google_login + '\'' +
                ", membership=" + membership +
                ", avatar='" + avatar + '\'' +
                ", credits=" + credits +
                ", visa_pay_id=" + visa_pay_id +
                '}';
    }
}
