package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.TotalDiscountDao;
import website.surf0335.backend.Model.dao_.domain.TotalDiscount;

import java.util.List;

@Repository
public class TotalDiscountServiceImpl implements TotalDiscountService {
    private TotalDiscountDao totalDiscountDao = new TotalDiscountDao();
    private static final Logger logger = LoggerFactory.getLogger(TotalDiscountServiceImpl.class);

    public TotalDiscount querySingleByTotalDiscountId(int totalDiscountId) {
        logger.info("Querying for total discount with ID: {}", totalDiscountId);
        return totalDiscountDao.querySingle("SELECT * FROM `total_discount` WHERE `total_discount_id` = ?", TotalDiscount.class, totalDiscountId);
    }

    public List<TotalDiscount> queryAllTotalDiscounts() {
        logger.info("Querying for all total discounts");
        List<TotalDiscount> discounts = totalDiscountDao.queryMultiple("SELECT * FROM `total_discount`", TotalDiscount.class);
        logger.info("Number of total discounts retrieved: {}", discounts.size());
        return discounts;
    }

    public boolean addTotalDiscount(int totalDiscountId, String name, String discount, String description) {
        logger.info("Adding new total discount with ID: {}, Name: {}, Discount: {}, Description: {}",
                totalDiscountId, name, discount, description);
        int update = totalDiscountDao.update("INSERT INTO `total_discount`(`total_discount_id`, `name`, `discount`, `description`) VALUES (?, ?, ?, ?)",
                totalDiscountId, name, discount, description);
        return update > 0;
    }

    public boolean deleteTotalDiscount(int totalDiscountId) {
        logger.info("Attempting to delete total discount with ID: {}", totalDiscountId);
        int update = totalDiscountDao.update("DELETE FROM `total_discount` WHERE `total_discount_id` = ?", totalDiscountId);
        return update > 0;
    }

    public boolean updateTotalDiscount(int totalDiscountId, String name, String discount, String description) {
        logger.info("Updating total discount ID: {}, Name: {}, Discount: {}, Description: {}",
                totalDiscountId, name, discount, description);
        int update = totalDiscountDao.update("UPDATE `total_discount` SET `name` = ?, `discount` = ?, `description` = ? WHERE `total_discount_id` = ?",
                name, discount, description, totalDiscountId);
        return update > 0;
    }


}
