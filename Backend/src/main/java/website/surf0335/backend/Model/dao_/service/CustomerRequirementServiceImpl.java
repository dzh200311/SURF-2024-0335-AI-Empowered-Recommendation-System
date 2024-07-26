package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.CustomerRequirementDao;
import website.surf0335.backend.Model.dao_.domain.CustomerRequirement;

import java.util.List;
@Repository
public class CustomerRequirementServiceImpl implements CustomerRequirementService {
    private CustomerRequirementDao customerRequirementDao = new CustomerRequirementDao();
    private static final Logger logger = LoggerFactory.getLogger(CustomerRequirementServiceImpl.class);

    public CustomerRequirement querySingleByRequirementId(int requirementId) {
        logger.info("Querying for customer requirement with ID: {}", requirementId);
        return customerRequirementDao.querySingle("SELECT * FROM `customer_requirement` WHERE `requirement_id` = ?", CustomerRequirement.class, requirementId);
    }

    public List<CustomerRequirement> queryMultipleByUserId(int userId) {
        logger.info("Querying for customer requirements by user ID: {}", userId);
        List<CustomerRequirement> requirements = customerRequirementDao.queryMultiple("SELECT * FROM `customer_requirement` WHERE `user_id` = ?", CustomerRequirement.class, userId);
        logger.info("Number of customer requirements found for user ID {}: {}", userId, requirements.size());
        return requirements;
    }

    public boolean addCustomerRequirement(int requirementId, int userId, String requirementDetail, int isRecentUse) {
        logger.info("Adding new customer requirement with ID: {}, User ID: {}, Is Recent Use: {}", requirementId, userId, isRecentUse);
        int update = customerRequirementDao.update("INSERT INTO `customer_requirement`(`requirement_id`, `user_id`, `requirement_detail`, `is_recent_use`) VALUES (?, ?, ?, ?)",
                requirementId, userId, requirementDetail, isRecentUse);
        return update > 0;
    }

    public boolean deleteCustomerRequirement(int requirementId) {
        logger.info("Attempting to delete customer requirement with ID: {}", requirementId);
        int update = customerRequirementDao.update("DELETE FROM `customer_requirement` WHERE `requirement_id` = ?", requirementId);
        return update > 0;
    }

    public boolean updateCustomerRequirement(int requirementId, int userId, String requirementDetail, int isRecentUse) {
        logger.info("Updating customer requirement ID: {}, User ID: {}, Is Recent Use: {}", requirementId, userId, isRecentUse);
        int update = customerRequirementDao.update("UPDATE `customer_requirement` SET `user_id` = ?, `requirement_detail` = ?, `is_recent_use` = ? WHERE `requirement_id` = ?",
                userId, requirementDetail, isRecentUse, requirementId);
        return update > 0;
    }


}
