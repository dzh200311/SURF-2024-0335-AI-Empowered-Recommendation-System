package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.CategoryDao;
import website.surf0335.backend.Model.dao_.domain.Category;

import java.util.List;

@Repository
public class CategoryServiceImpl implements CategoryService{
    private CategoryDao categoryDao = new CategoryDao();
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public Category querySingleByCategoryId(int categoryId) {
        return categoryDao.querySingle("SELECT * FROM `category` WHERE `category_id` = ?", Category.class, categoryId);
    }

    @Override
    public List<Category> queryAllCategories() {
        logger.info("Querying for all categories");
        List<Category> categories = categoryDao.queryMultiple("SELECT * FROM `category`", Category.class);
        logger.info("Number of categories found: {}", categories.size());
        return categories;
    }
    @Override
    public boolean addCategory(Category category) {
        logger.info("Adding new category with ID: {}, Name: {}, Description: {}, Image: {}", category.getCategory_id(), category.getName(), category.getDescription(), category.getImage());
        int update = categoryDao.update("INSERT INTO `category`(`category_id`, `name`, `description`, `image`) VALUES (?, ?, ?, ?)",
                category.getCategory_id(), category.getName(), category.getDescription(), category.getImage());

        return update > 0;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        logger.info("Attempting to delete category with ID: {}", categoryId);
        int update = categoryDao.update("DELETE FROM `category` WHERE `category_id` = ?", categoryId);
        return update > 0;
    }

    @Override
    public boolean updateCategory(int categoryId, String name, String description, String image) {
        logger.info("Updating category ID: {}, Name: {}, Description: {}, Image: {}", categoryId, name, description, image);
        int update = categoryDao.update("UPDATE `category` SET `name` = ?, `description` = ?, `image` = ? WHERE `category_id` = ?",
                name, description, image, categoryId);
        return update > 0;
    }


}

