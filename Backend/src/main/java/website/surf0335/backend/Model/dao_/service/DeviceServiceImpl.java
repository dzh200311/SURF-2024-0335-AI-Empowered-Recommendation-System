package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.DeviceDao;
import website.surf0335.backend.Model.dao_.domain.Device;

import java.util.List;
@Repository
public class DeviceServiceImpl implements DeviceService {
    private DeviceDao deviceDao = new DeviceDao();
    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    public Device querySingleByDeviceId(int deviceId) {
        logger.info("Querying for device with ID: {}", deviceId);
        return deviceDao.querySingle("SELECT * FROM `device` WHERE `device_id` = ?", Device.class, deviceId);
    }

    public List<Device> queryAllDevices() {
        logger.info("Querying for all devices");
        List<Device> devices = deviceDao.queryMultiple("SELECT * FROM `device`", Device.class);
        logger.info("Number of devices retrieved: {}", devices.size());
        return devices;
    }

    public boolean addDevice(int deviceId, String token, String macAddress, int enable, String removeTimes) {
        logger.info("Adding new device with ID: {}, Token: {}, MAC Address: {}, Enable: {}, Remove Times: {}",
                deviceId, token, macAddress, enable, removeTimes);
        int update = deviceDao.update("INSERT INTO `device`(`device_id`, `token`, `mac_address`, `enable`, `remove_times`) VALUES (?, ?, ?, ?, ?)",
                deviceId, token, macAddress, enable, removeTimes);
        return update > 0;
    }

    public boolean deleteDevice(int deviceId) {
        logger.info("Attempting to delete device with ID: {}", deviceId);
        int update = deviceDao.update("DELETE FROM `device` WHERE `device_id` = ?", deviceId);
        return update > 0;
    }

    public boolean updateDevice(int deviceId, String token, String macAddress, int enable, String removeTimes) {
        logger.info("Updating device ID: {}, Token: {}, MAC Address: {}, Enable: {}, Remove Times: {}");
        int update = deviceDao.update("UPDATE `device` SET `token` = ?, `mac_address` = ?, `enable` = ?, `remove_times` = ? WHERE `device_id` = ?",
                token, macAddress, enable, removeTimes, deviceId);
        return update > 0;
    }


}
