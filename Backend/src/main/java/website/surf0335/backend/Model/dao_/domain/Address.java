package website.surf0335.backend.Model.dao_.domain;

public class Address {
    private Integer address_id;
    private Integer user_id;
    private String address_detail;
    private String is_rencent_use;

    public Address() {
    }

    public Address(int address_id, int user_id, String address_detail, String is_rencent_use) {
        this.address_id = address_id;
        this.user_id = user_id;
        this.address_detail = address_detail;
        this.is_rencent_use = is_rencent_use;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getIs_rencent_use() {
        return is_rencent_use;
    }

    public void setIs_rencent_use(String is_rencent_use) {
        this.is_rencent_use = is_rencent_use;
    }
}
