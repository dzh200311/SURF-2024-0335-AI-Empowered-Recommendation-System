package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.ActiveUser;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ActiveUserService {
    /**
     * Queries for a single active user by their ID.
     * @param activeUserId the ID of the active user
     * @return the active user object or null if not found
     */
    ActiveUser querySingleByActiveUserId(int activeUserId);

    /**
     * Queries for all active users in the database.
     * @return a list of all active users
     */
    List<ActiveUser> queryAllActiveUsers();

    /**
     * Adds a new active user to the database.
     * @param activeUserId the ID of the active user
     * @param lastLoginTime the timestamp of the last login
     * @param ip the IP address from which the last login was made
     * @return true if the operation was successful, false otherwise
     */
    boolean addActiveUser(int activeUserId, Timestamp lastLoginTime, String ip);

    /**
     * Deletes an active user from the database based on their ID.
     * @param activeUserId the ID of the active user to delete
     * @return true if the operation was successful, false otherwise
     */
    boolean deleteActiveUser(int activeUserId);

    /**
     * Updates the information of an existing active user in the database.
     * @param activeUserId the ID of the active user
     * @param lastLoginTime the new timestamp of the last login
     * @param ip the new IP address from which the last login was made
     * @return true if the update was successful, false otherwise
     */
    boolean updateActiveUser(int activeUserId, Timestamp lastLoginTime, String ip);
}
