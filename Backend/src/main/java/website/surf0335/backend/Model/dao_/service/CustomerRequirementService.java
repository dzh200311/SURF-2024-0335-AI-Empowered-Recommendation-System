package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.CustomerRequirement;

import java.util.List;

@Repository
public interface CustomerRequirementService {
    CustomerRequirement querySingleByRequirementId(int requirementId);

    /**
     * Retrieves a list of customer requirements associated with a specific user ID.
     * @param userId the ID of the user whose requirements are to be retrieved
     * @return a list of customer requirements
     */
    List<CustomerRequirement> queryMultipleByUserId(int userId);

    /**
     * Adds a new customer requirement to the database.
     * @param requirementId the ID of the new requirement
     * @param userId the ID of the user who has the requirement
     * @param requirementDetail a detailed description of the requirement
     * @param isRecentUse an integer flag indicating whether this requirement is recently used
     * @return true if the addition is successful, false otherwise
     */
    boolean addCustomerRequirement(int requirementId, int userId, String requirementDetail, int isRecentUse);

    /**
     * Deletes a customer requirement based on its requirement ID.
     * @param requirementId the ID of the requirement to delete
     * @return true if the deletion is successful, false otherwise
     */
    boolean deleteCustomerRequirement(int requirementId);

    /**
     * Updates an existing customer requirement.
     * @param requirementId the ID of the requirement to update
     * @param userId the ID of the user associated with the requirement
     * @param requirementDetail new details of the requirement
     * @param isRecentUse an updated integer flag indicating whether this requirement is recently used
     * @return true if the update is successful, false otherwise
     */
    boolean updateCustomerRequirement(int requirementId, int userId, String requirementDetail, int isRecentUse);

}
