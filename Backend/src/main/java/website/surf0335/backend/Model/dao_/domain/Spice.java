package website.surf0335.backend.Model.dao_.domain;

public class Spice {
    private Integer spice_id;
    private String category;
    private String country_of_origin;
    private Double price;
    private Integer stock_quantity;
    private Integer store_id;

    public Spice() {
    }

    public Spice(Integer spice_id, String category, String country_of_origin, Double price, Integer stock_quantity, Integer store_id) {
        this.spice_id = spice_id;
        this.category = category;
        this.country_of_origin = country_of_origin;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.store_id = store_id;
    }

    public Integer getSpice_id() {
        return spice_id;
    }

    public void setSpice_id(Integer spice_id) {
        this.spice_id = spice_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "Spice{" +
                "spice_id=" + spice_id +
                ", category='" + category + '\'' +
                ", country_of_origin='" + country_of_origin + '\'' +
                ", price=" + price +
                ", stock_quantity=" + stock_quantity +
                ", store_id=" + store_id +
                '}';
    }
}
