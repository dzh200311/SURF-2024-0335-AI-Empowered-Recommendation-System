package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.StoreList;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StoreListService {
    StoreList querySingleById(int storeId);
    List<StoreList> queryByManager(String manager);
    boolean addStoreList(Integer storeId, String restaurantName, String position, String manager,
                         String phone, String status, Timestamp startTime, Timestamp closeTime);
    boolean deleteStoreList(Integer storeId);
    boolean updateStoreList(Integer storeId, String restaurantName, String position, String manager,
                            String phone, String status, Timestamp startTime, Timestamp closeTime);
}
