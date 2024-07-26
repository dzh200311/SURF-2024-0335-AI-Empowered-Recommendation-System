package website.surf0335.backend.Model.dao_.domain;

public class TotalDiscount {
    private Integer total_discount;
    private String name;
    private String discount;
    private String description;

    public TotalDiscount() {
    }

    public TotalDiscount(Integer total_discount, String name, String discount, String description) {
        this.total_discount = total_discount;
        this.name = name;
        this.discount = discount;
        this.description = description;
    }

    public Integer getTotal_discount() {
        return total_discount;
    }

    public void setTotal_discount(Integer total_discount) {
        this.total_discount = total_discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Total_discount{" +
                "total_discount=" + total_discount +
                ", name='" + name + '\'' +
                ", discount='" + discount + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
