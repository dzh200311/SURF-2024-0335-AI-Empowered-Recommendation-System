package website.surf0335.backend.Model.dao_.domain;

import java.sql.Timestamp;

public class Product {
    private Integer product_id;
    private String product_name;
    private double price;
    private double discount;
    private Integer category_id;
    private String description;
    private String image;
    private Integer favorite;
    private String sale_count;
    private Integer is_new;
    private Integer is_recommend;
    private Timestamp discount_starttime;
    private Timestamp discount_endtime;
    private Integer specification_id;

    public Product() {
    }

    public Product(Integer product_id, String product_name, double price, double discount, Integer category_id, String description, String image, Integer favorite, String sale_count, Integer is_new, Integer is_recommend, Timestamp discount_starttime, Timestamp discount_endtime, Integer specification_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.discount = discount;
        this.category_id = category_id;
        this.description = description;
        this.image = image;
        this.favorite = favorite;
        this.sale_count = sale_count;
        this.is_new = is_new;
        this.is_recommend = is_recommend;
        this.discount_starttime = discount_starttime;
        this.discount_endtime = discount_endtime;
        this.specification_id = specification_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", category_id=" + category_id +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", favorite=" + favorite +
                ", sale_count='" + sale_count + '\'' +
                ", is_new=" + is_new +
                ", is_recommend=" + is_recommend +
                ", discount_starttime=" + discount_starttime +
                ", discount_endtime=" + discount_endtime +
                ", specification_id=" + specification_id +
                '}';
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public String getSale_count() {
        return sale_count;
    }

    public void setSale_count(String sale_count) {
        this.sale_count = sale_count;
    }

    public Integer getIs_new() {
        return is_new;
    }

    public void setIs_new(Integer is_new) {
        this.is_new = is_new;
    }

    public Integer getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(Integer is_recommend) {
        this.is_recommend = is_recommend;
    }

    public Timestamp getDiscount_starttime() {
        return discount_starttime;
    }

    public void setDiscount_starttime(Timestamp discount_starttime) {
        this.discount_starttime = discount_starttime;
    }

    public Timestamp getDiscount_endtime() {
        return discount_endtime;
    }

    public void setDiscount_endtime(Timestamp discount_endtime) {
        this.discount_endtime = discount_endtime;
    }

    public Integer getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(Integer specification_id) {
        this.specification_id = specification_id;
    }
}
