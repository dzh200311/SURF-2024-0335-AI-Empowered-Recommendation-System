package website.surf0335.backend.Model.dao_.domain;

public class GithubUserData {
    private String login;
    private String name;
    private String avatar_url;
    private String bio;
    private long id;

    @Override
    public String toString() {
        return "GithubUserData{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", bio='" + bio + '\'' +
                ", id=" + id +
                '}';
    }

    // Constructor
    public GithubUserData(String login, String name, String avatar_url, String bio, long id) {
        this.login = login;
        this.name = name;
        this.avatar_url = avatar_url;
        this.bio = bio;
        this.id = id;
    }

    // Getters and setters (if needed)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}