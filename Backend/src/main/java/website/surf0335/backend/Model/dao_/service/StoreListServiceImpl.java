package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.StoreListDao;
import website.surf0335.backend.Model.dao_.domain.StoreList;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class StoreListServiceImpl implements StoreListService {
        private StoreListDao storeListDao = new StoreListDao();
    private static final Logger logger = LoggerFactory.getLogger(StoreListServiceImpl.class);

        public StoreList querySingleById(int storeId) {
            logger.info("Querying for store with ID: {}", storeId);
            return storeListDao.querySingle("SELECT * FROM `store_list` WHERE store_id = ?", StoreList.class, storeId);
        }

        public List<StoreList> queryByManager(String manager) {
            logger.info("Querying for stores managed by: {}", manager);
            List<StoreList> stores = storeListDao.queryMultiple("SELECT * FROM `store_list` WHERE manager = ?", StoreList.class, manager);
            logger.info("Number of stores managed by {}: {}", manager, stores.size());
            return stores;
        }

        public boolean addStoreList(Integer storeId, String restaurantName, String position, String manager,
                                    String phone, String status, Timestamp startTime, Timestamp closeTime) {
            logger.info("Adding new store with ID: {}, Name: {}, Manager: {}, Position: {}, Phone: {}, Status: {}, Start Time: {}, Close Time: {}",
                    storeId, restaurantName, position, manager, phone, status, startTime, closeTime);
            int update = storeListDao.update("INSERT INTO `store_list` (store_id, restaurant_name, position, manager, phone, status, start_time, close_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    storeId, restaurantName, position, manager, phone, status, startTime, closeTime);
            return update > 0;
        }

        public boolean deleteStoreList(Integer storeId) {
            logger.info("Attempting to delete store with ID: {}", storeId);
            int update = storeListDao.update("DELETE FROM `store_list` WHERE store_id = ?", storeId);
            return update > 0;
        }

        public boolean updateStoreList(Integer storeId, String restaurantName, String position, String manager,
                                       String phone, String status, Timestamp startTime, Timestamp closeTime) {
            logger.info("Updating store ID: {}, Name: {}, Manager: {}, Position: {}, Phone: {}, Status: {}, Start Time: {}, Close Time: {}",
                    storeId, restaurantName, position, manager, phone, status, startTime, closeTime);
            int update = storeListDao.update("UPDATE `store_list` SET restaurant_name = ?, position = ?, manager = ?, phone = ?, status = ?, start_time = ?, close_time = ? WHERE store_id = ?",
                    restaurantName, position, manager, phone, status, startTime, closeTime, storeId);
            return update > 0;
        }


    }

