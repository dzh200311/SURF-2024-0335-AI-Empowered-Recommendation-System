package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.AddressDao;
import website.surf0335.backend.Model.dao_.domain.Address;

import java.util.List;

@Repository
public class AddressServiceImpl implements AddressService{
    private AddressDao addressDao = new AddressDao();
    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    public Address querySingleByAddressId(int addressId) {
        logger.info("Querying for address with ID: {}", addressId);
        Address address = addressDao.querySingle("SELECT * FROM `address` WHERE `address_id` = ?", Address.class, addressId);
        return addressDao.querySingle("SELECT * FROM `address` WHERE `address_id` = ?", Address.class, addressId);
    }

    public List<Address> queryMultipleByUserId(int userId) {
        logger.info("Querying for multiple addresses by user ID: {}", userId);
        List<Address> addresses = addressDao.queryMultiple("SELECT * FROM `address` WHERE `user_id` = ?", Address.class, userId);
        logger.info("Number of addresses found for user ID {}: {}", userId, addresses.size());
        return addresses;
    }

    public boolean addAddress(int addressId, int userId, String addressDetail, String isRecentUse) {
        logger.info("Adding new address with ID: {}, User ID: {}, Detail: {}, Recent Use: {}", addressId, userId, addressDetail, isRecentUse);
        int update = addressDao.update("INSERT INTO `address`(`address_id`, `user_id`, `address_detail`, `is_recent_use`) VALUES (?, ?, ?, ?)",
                addressId, userId, addressDetail, isRecentUse);
        return update > 0;
    }

    public boolean deleteAddress(int addressId) {
        logger.info("Attempting to delete address with ID: {}", addressId);
        int update = addressDao.update("DELETE FROM `address` WHERE `address_id` = ?", addressId);
        return update > 0;
    }

    public boolean updateAddress(int addressId, int userId, String addressDetail, String isRecentUse) {
        logger.info("Updating address ID: {}, User ID: {}, Detail: {}, Recent Use: {}", addressId, userId, addressDetail, isRecentUse);
        int update = addressDao.update("UPDATE `address` SET `user_id` = ?, `address_detail` = ?, `is_recent_use` = ? WHERE `address_id` = ?",
                userId, addressDetail, isRecentUse, addressId);
        return update > 0;
    }



}
