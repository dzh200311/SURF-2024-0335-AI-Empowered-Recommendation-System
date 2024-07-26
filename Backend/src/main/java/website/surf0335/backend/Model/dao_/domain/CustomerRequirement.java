package website.surf0335.backend.Model.dao_.domain;

public class CustomerRequirement {
    private Integer requirement_id;
    private Integer user_id;
    private String requirement_detail;
    private Integer is_recent_use;

    public CustomerRequirement() {
    }

    public CustomerRequirement(int requirement_id, int user_id, String requirement_detail, int is_recent_use) {
        this.requirement_id = requirement_id;
        this.user_id = user_id;
        this.requirement_detail = requirement_detail;
        this.is_recent_use = is_recent_use;
    }

    public int getRequirement_id() {
        return requirement_id;
    }

    public void setRequirement_id(int requirement_id) {
        this.requirement_id = requirement_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRequirement_detail() {
        return requirement_detail;
    }

    public void setRequirement_detail(String requirement_detail) {
        this.requirement_detail = requirement_detail;
    }

    public int getIs_recent_use() {
        return is_recent_use;
    }

    public void setIs_recent_use(int is_recent_use) {
        this.is_recent_use = is_recent_use;
    }

    @Override
    public String toString() {
        return "CustomerRequirement{" +
                "requirement_id=" + requirement_id +
                ", user_id=" + user_id +
                ", requirement_detail='" + requirement_detail + '\'' +
                ", is_recent_use=" + is_recent_use +
                '}';
    }
}
