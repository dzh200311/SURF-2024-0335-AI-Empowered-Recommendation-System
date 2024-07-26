package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.Address;

import java.util.List;
@Repository
public interface AddressService {
    /**
     * Retrieves a single address record based on its ID.
     * @param addressId the ID of the address to be retrieved
     * @return the address object if found, otherwise null
     */
    Address querySingleByAddressId(int addressId);

    /**
     * Retrieves a list of address records associated with a specific user ID.
     * @param userId the ID of the user whose addresses are to be retrieved
     * @return a list of addresses associated with the user
     */
    List<Address> queryMultipleByUserId(int userId);

    /**
     * Adds a new address record to the database.
     * @param addressId the ID of the new address (assumes address ID is provided, which might be unusual)
     * @param userId the ID of the user to whom the address belongs
     * @param addressDetail detailed description of the address
     * @param isRecentUse flag indicating whether this address is the most recently used
     * @return true if the addition is successful, false otherwise
     */
    boolean addAddress(int addressId, int userId, String addressDetail, String isRecentUse);

    /**
     * Deletes an address record based on its ID.
     * @param addressId the ID of the address to delete
     * @return true if the deletion is successful, false otherwise
     */
    boolean deleteAddress(int addressId);

    /**
     * Updates an existing address record.
     * @param addressId the ID of the address to update
     * @param userId the ID of the user to whom the address belongs
     * @param addressDetail new detailed description of the address
     * @param isRecentUse new flag status indicating whether this address is now the most recently used
     * @return true if the update is successful, false otherwise
     */
    boolean updateAddress(int addressId, int userId, String addressDetail, String isRecentUse);
}
