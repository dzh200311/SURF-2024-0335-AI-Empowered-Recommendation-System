package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.SpiceDao;
import website.surf0335.backend.Model.dao_.domain.Spice;

import java.util.List;

@Repository
public class SpiceServiceImpl implements SpiceService {
    private SpiceDao spiceDao = new SpiceDao();
    private static final Logger logger = LoggerFactory.getLogger(SpiceServiceImpl.class);

    public Spice querySingleBySpiceId(int spiceId) {
        logger.info("Querying for spice with ID: {}", spiceId);
        return spiceDao.querySingle("SELECT * FROM `spice` WHERE `spice_id` = ?", Spice.class, spiceId);
    }

    public List<Spice> queryAllSpices() {
        logger.info("Querying for all spices");
        List<Spice> spices = spiceDao.queryMultiple("SELECT * FROM `spice`", Spice.class);
        logger.info("Number of spices retrieved: {}", spices.size());
        return spices;
    }

    public boolean addSpice(int spiceId, String category, String countryOfOrigin, Double price, Integer stockQuantity, Integer storeId) {
        logger.info("Adding new spice with ID: {}, Category: {}, Country of Origin: {}, Price: {}, Stock Quantity: {}, Store ID: {}",
                spiceId, category, countryOfOrigin, price, stockQuantity, storeId);
        int update = spiceDao.update("INSERT INTO `spice`(`spice_id`, `category`, `country_of_origin`, `price`, `stock_quantity`, `store_id`) VALUES (?, ?, ?, ?, ?, ?)",
                spiceId, category, countryOfOrigin, price, stockQuantity, storeId);
        return update > 0;
    }

    public boolean deleteSpice(int spiceId) {
        logger.info("Attempting to delete spice with ID: {}", spiceId);
        int update = spiceDao.update("DELETE FROM `spice` WHERE `spice_id` = ?", spiceId);
        return update > 0;
    }

    public boolean updateSpice(int spiceId, String category, String countryOfOrigin, Double price, Integer stockQuantity, Integer storeId) {
        logger.info("Updating spice ID: {}, Category: {}, Country of Origin: {}, Price: {}, Stock Quantity: {}, Store ID: {}",
                spiceId, category, countryOfOrigin, price, stockQuantity, storeId);
        int update = spiceDao.update("UPDATE `spice` SET `category` = ?, `country_of_origin` = ?, `price` = ?, `stock_quantity` = ?, `store_id` = ? WHERE `spice_id` = ?",
                category, countryOfOrigin, price, stockQuantity, storeId, spiceId);
        return update > 0;
    }


}
