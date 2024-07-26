package website.surf0335.backend.Model.dao_.domain;

public class Category {
    private Integer category_id;
    private String description;
    private String image;
    private String name;

    public Category() {
    }

    public Category(int category_id, String description, String image, String name) {
        this.category_id = category_id;
        this.description = description;
        this.image = image;
        this.name = name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
