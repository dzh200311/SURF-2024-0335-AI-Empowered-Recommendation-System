package website.surf0335.backend.Model.dao_.domain;

public class VisaPay {
    private Integer id;
    private Integer user_id;
    private String bank_name;
    private String type;
    private String card_number;
    private String expire_date;
    private String secret;
    private String is_recent_use;

    public VisaPay() {
    }

    public VisaPay(Integer id, Integer user_id, String bank_name, String type, String card_number, String expire_date, String secret, String is_recent_use) {
        this.id = id;
        this.user_id = user_id;
        this.bank_name = bank_name;
        this.type = type;
        this.card_number = card_number;
        this.expire_date = expire_date;
        this.secret = secret;
        this.is_recent_use = is_recent_use;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIs_recent_use() {
        return is_recent_use;
    }

    public void setIs_recent_use(String is_recent_use) {
        this.is_recent_use = is_recent_use;
    }

    @Override
    public String toString() {
        return "Visa_Pay{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", bank_name='" + bank_name + '\'' +
                ", type='" + type + '\'' +
                ", card_number='" + card_number + '\'' +
                ", expire_date='" + expire_date + '\'' +
                ", secret='" + secret + '\'' +
                ", is_recent_use='" + is_recent_use + '\'' +
                '}';
    }
}
