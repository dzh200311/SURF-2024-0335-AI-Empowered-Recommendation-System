package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.Device;

import java.util.List;

@Repository
public interface DeviceService {
    Device querySingleByDeviceId(int deviceId);

    /**
     * Retrieves all devices from the database.
     * @return a list of all devices
     */
    List<Device> queryAllDevices();

    /**
     * Adds a new device to the database.
     * @param deviceId the ID of the new device
     * @param token the security token associated with the device
     * @param macAddress the MAC address of the device
     * @param enable an integer indicating whether the device is enabled (1) or disabled (0)
     * @param removeTimes the number of times the device has been removed
     * @return true if the addition is successful, false otherwise
     */
    boolean addDevice(int deviceId, String token, String macAddress, int enable, String removeTimes);

    /**
     * Deletes a device based on its device ID.
     * @param deviceId the ID of the device to delete
     * @return true if the deletion is successful, false otherwise
     */
    boolean deleteDevice(int deviceId);

    /**
     * Updates an existing device.
     * @param deviceId the ID of the device to update
     * @param token the new security token for the device
     * @param macAddress the new MAC address of the device
     * @param enable an updated integer value indicating whether the device is enabled (1) or disabled (0)
     * @param removeTimes the updated number of times the device has been removed
     * @return true if the update is successful, false otherwise
     */
    boolean updateDevice(int deviceId, String token, String macAddress, int enable, String removeTimes);
}
