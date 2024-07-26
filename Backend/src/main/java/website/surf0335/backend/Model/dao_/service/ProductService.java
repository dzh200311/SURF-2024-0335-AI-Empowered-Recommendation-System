package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.Product;

import java.util.List;

@Repository
public interface ProductService {
    public boolean updateProductImage(int productId, String image);


    public List<Product> queryDefaultProducts(int categoryId);

    public boolean updateProduct(Product product);

    Product querySingleByProductId(int productId);

    List<Product> queryAllProducts();

    boolean addProduct(Product product);

    public int getProductId();

    boolean deleteProduct(int productId);

}
