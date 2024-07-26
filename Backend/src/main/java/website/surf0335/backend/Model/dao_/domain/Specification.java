package website.surf0335.backend.Model.dao_.domain;

public class Specification {
    private int id;
    private String content;

    public Specification() {
    }

    public Specification(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
