package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.ProductDao;
import website.surf0335.backend.Model.dao_.domain.Product;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class ProductServiceImpl implements ProductService{
    private ProductDao productDao = new ProductDao();
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public Product querySingleByProductId(int productId) {
        logger.info("Querying for product with ID: {}", productId);
        return productDao.querySingle("SELECT * FROM `product` WHERE `product_id` = ?", Product.class, productId);
    }


    @Override
    public List<Product> queryAllProducts() {
        logger.info("Querying for all products");
        List<Product> products = productDao.queryMultiple("SELECT * FROM `product`", Product.class);
        logger.info("Number of products retrieved: {}", products.size());
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        logger.info("Adding new product with Name: {}, Price: {}, Discount: {}, Category ID: {}, Description: {}, Image: {},  Is New: {}, Discount Start Time: {}, Discount End Time: {}",
                product.getProduct_name(), product.getPrice(), product.getDiscount(), product.getCategory_id(), product.getDescription(), product.getImage(), product.getIs_new(), product.getDiscount_starttime(), product.getDiscount_endtime());
        int update = productDao.update("INSERT INTO `product`(`product_name`, `price`, `discount`, `category_id`, `description`, `image`,  `is_new`, `is_recommend`,`discount_starttime`, `discount_endtime`, `specification_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                product.getProduct_name(),
                product.getPrice(), product.getDiscount(), product.getCategory_id(),
                product.getDescription(), product.getImage(),
                product.getIs_new(), product.getIs_recommend(),product.getDiscount_starttime(),
                product.getDiscount_endtime(),product.getSpecification_id());
        return update > 0;
    }

    @Override
    public int getProductId() {
        return productDao.getLastProductId();
    }


    @Override
    public List<Product> queryDefaultProducts(int categoryId) {
        logger.info("Querying default products");
        List<Product> products = productDao.queryMultiple("SELECT * FROM `product` where category_id = ?", Product.class, categoryId);

        return products;
    }

    @Override
    public boolean updateProduct(Product product) {
        logger.info("Attempting to update product with ID: {}", product.getProduct_id());
        int update = productDao.update("UPDATE `product` SET `product_name` = ?,`price` = ?,`discount` = ?,`category_id` = ?,`description` = ?,`image` = ?,`is_new` = ?,`is_recommend` = ?,`discount_starttime` = ?,`discount_endtime` = ?,`specification_id` = ? WHERE`product_id` = ?",
                product.getProduct_name(), product.getPrice(), product.getDiscount(), product.getCategory_id(), product.getDescription(), product.getImage(), product.getIs_new(), product.getIs_recommend(), product.getDiscount_starttime(), product.getDiscount_endtime(), product.getSpecification_id(), product.getProduct_id());
        return update > 0;
    }


    @Override
    public boolean deleteProduct(int productId) {
        logger.info("Attempting to delete product with ID: {}", productId);
        int update = productDao.update("DELETE FROM `product` WHERE `product_id` = ?", productId);
        return update > 0;
    }


    @Override
    public boolean updateProductImage(int productId, String image ) {
        int update = productDao.update("UPDATE `product` SET `image` = ? WHERE `product_id` = ?",
                 image, productId);
        return update > 0;
    }

    public static void main(String[] args) {
        ProductServiceImpl productService = new ProductServiceImpl();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp later = new Timestamp(System.currentTimeMillis() + 3600000); // One hour later

        Product product = new Product(201, "Coffee Maker1222", 49.99, 0.12, 1, "A coffee maker", "image.png", null, null, 1, 0, now, later, 100);

        System.out.println(productService.updateProduct(product));



        // Test updating a product
//        System.out.println("Testing updateProduct:");
//        boolean updated = productService.updateProduct(4, "Advanced Coffee Maker", 99.99, "15%", 1, "New a2nd improved coffee maker", "image2.png", 20, "100", 0, now, later, 150);
//        System.out.println("Product Updated: " + updated);
//
//        // Test deleting a product
//        System.out.println("Testing deleteProduct:");
//        boolean deleted = productService.deleteProduct(1);
//        System.out.println("Product Deleted: " + deleted);

        System.out.println(productService.getProductId());
    }
}
