package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.Category;

import java.util.List;

@Repository
public interface CategoryService {
    /**
     * Retrieves a single category based on its category ID.
     * @param categoryId the ID of the category to retrieve
     * @return the category object if found, otherwise null
     */
    Category querySingleByCategoryId(int categoryId);

    /**
     * Retrieves all categories from the database.
     * @return a list of all categories
     */
    List<Category> queryAllCategories();

    /**
     * Adds a new category to the database.
     * @param category the category object to be added
     * @return true if the addition is successful, false otherwise
     */
    boolean addCategory(Category category);

    /**
     * Deletes a category based on its category ID.
     * @param categoryId the ID of the category to be deleted
     * @return true if the deletion is successful, false otherwise
     */
    boolean deleteCategory(int categoryId);

    /**
     * Updates an existing category.
     * @param categoryId the ID of the category to update
     * @param name the updated name of the category
     * @param description the updated description of the category
     * @param image the updated link or path to an image representing the category
     * @return true if the update is successful, false otherwise
     */
    boolean updateCategory(int categoryId, String name, String description, String image);
}
