package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.RiderDao;
import website.surf0335.backend.Model.dao_.domain.Rider;

import java.util.List;

@Repository
public class RiderServiceImpl implements RiderService {
    private RiderDao riderDao = new RiderDao();
    private static final Logger logger = LoggerFactory.getLogger(RiderServiceImpl.class);

    public Rider querySingleByRiderId(int riderId) {
        logger.info("Querying for rider with ID: {}", riderId);
        return riderDao.querySingle("SELECT * FROM `rider` WHERE `rider_id` = ?", Rider.class, riderId);
    }

    public List<Rider> queryAllRiders() {
        logger.info("Querying for all riders");
        List<Rider> riders = riderDao.queryMultiple("SELECT * FROM `rider`", Rider.class);
        logger.info("Number of riders retrieved: {}", riders.size());
        return riders;
    }

    public boolean addRider(int riderId, String fullName, String phoneNumber, String email, String vehicleType, String licensePlate, String status, Integer storeId) {
        logger.info("Adding new rider with ID: {}, Name: {}, Phone: {}, Email: {}, Vehicle Type: {}, License Plate: {}, Status: {}, Store ID: {}",
                riderId, fullName, phoneNumber, email, vehicleType, licensePlate, status, storeId);
        int update = riderDao.update("INSERT INTO `rider`(`rider_id`, `full_name`, `phone_number`, `email`, `vehicle_type`, `license_plate`, `status`, `store_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                riderId, fullName, phoneNumber, email, vehicleType, licensePlate, status, storeId);
        return update > 0;
    }

    public boolean deleteRider(int riderId) {
        logger.info("Attempting to delete rider with ID: {}", riderId);
        int update = riderDao.update("DELETE FROM `rider` WHERE `rider_id` = ?", riderId);
        return update > 0;
    }

    public boolean updateRider(int riderId, String fullName, String phoneNumber, String email, String vehicleType, String licensePlate, String status, Integer storeId) {
        logger.info("Updating rider ID: {}, Name: {}, Phone: {}, Email: {}, Vehicle Type: {}, License Plate: {}, Status: {}, Store ID: {}",
                riderId, fullName, phoneNumber, email, vehicleType, licensePlate, status, storeId);
        int update = riderDao.update("UPDATE `rider` SET `full_name` = ?, `phone_number` = ?, `email` = ?, `vehicle_type` = ?, `license_plate` = ?, `status` = ?, `store_id` = ? WHERE `rider_id` = ?",
                fullName, phoneNumber, email, vehicleType, licensePlate, status, storeId, riderId);
        return update > 0;
    }


}
