package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.Rider;

import java.util.List;

@Repository
public interface RiderService {
    Rider querySingleByRiderId(int riderId);
    List<Rider> queryAllRiders();
    boolean addRider(int riderId, String fullName, String phoneNumber, String email, String vehicleType, String licensePlate, String status, Integer storeId);
    boolean deleteRider(int riderId);
    boolean updateRider(int riderId, String fullName, String phoneNumber, String email, String vehicleType, String licensePlate, String status, Integer storeId);
}
