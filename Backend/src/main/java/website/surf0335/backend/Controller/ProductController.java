package website.surf0335.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;
import website.surf0335.backend.Model.dao_.domain.Category;
import website.surf0335.backend.Model.dao_.domain.Product;
import website.surf0335.backend.Model.dao_.service.CategoryService;
import website.surf0335.backend.Model.dao_.service.ProductService;
import website.surf0335.backend.Model.dao_.service.SpecificationService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SpecificationService specificationService;

    @GetMapping("/get_category")
    public List<Category> getCategory() {
        return categoryService.queryAllCategories();
    }

    @GetMapping("/get_default_product")
    public List<Product> getDefaultProduct() {
        return productService.queryDefaultProducts(1);
    }

    @PostMapping("/get_product")
    public List<Product> getProduct(@RequestParam("category_id") int categoryId) {
        return productService.queryDefaultProducts(categoryId);
    }

    @PostMapping("/add_category")
    public boolean addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }


    @PostMapping("/modify_category")
    public boolean modifyCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category.getCategory_id(), category.getName(),
                category.getDescription(), category.getImage());
    }

    @GetMapping("/get_specification")
    public String[] getSpecification(@RequestParam("id") int id) {
        return specificationService.getSpecificationByID(id);
    }

    @GetMapping("/get_all_specification")
    public List<String[]> getAllSpecification() {
        return specificationService.getAllSpecification();
    }

    @PostMapping("/add_specification")
    public boolean addSpecification(@RequestParam("content") String content) {
        return specificationService.addSpecification(content);
    }

    @PostMapping("/modify_specification")
    public boolean modifySpecification(@RequestParam("content") String content, @RequestParam("id") int id) {
        return specificationService.modifySpecification(content, id);
    }

    @PostMapping("/add_product")
    public boolean addProduct(@RequestBody Product product) {
        System.out.println(product);
        return productService.addProduct(product);
    }

    @PostMapping("/add_product_image")
    public boolean addProductImage(@RequestParam("image") String image, @RequestParam("id") int id) {
        return productService.updateProductImage(id, image);
    }

    @PostMapping("/modify_product")
    public boolean modifyProduct(@RequestBody Product product) {
        System.out.println(product);
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete_product/{productId}")
    public boolean deleteProduct(@PathVariable int productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/get_next_product_id")
    public int getNextProductId() {
        return productService.getProductId() + 1;
    }

}
