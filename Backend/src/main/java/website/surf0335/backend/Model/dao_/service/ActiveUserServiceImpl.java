package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.ActiveUserDao;
import website.surf0335.backend.Model.dao_.domain.ActiveUser;

import java.sql.Timestamp;
import java.util.List;
@Repository
public class ActiveUserServiceImpl implements ActiveUserService {
    private static final Logger logger = LoggerFactory.getLogger(ActiveUserServiceImpl.class);
    private ActiveUserDao activeUserDao = new ActiveUserDao();

    public ActiveUser querySingleByActiveUserId(int activeUserId) {
        ActiveUser result = activeUserDao.querySingle("SELECT * FROM `active_user` WHERE `active_user_id` = ?", ActiveUser.class, activeUserId);
        logger.info("Query result: {}",result );
        return activeUserDao.querySingle("SELECT * FROM `active_user` WHERE `active_user_id` = ?", ActiveUser.class, activeUserId);

    }

    public List<ActiveUser> queryAllActiveUsers() {
        List<ActiveUser> users = activeUserDao.queryMultiple("SELECT * FROM `active_user`", ActiveUser.class);
        logger.info("Number of active users retrieved: {}", users.size());
        return activeUserDao.queryMultiple("SELECT * FROM `active_user`", ActiveUser.class);
    }

    public boolean addActiveUser(int activeUserId, Timestamp lastLoginTime, String ip) {
        int update = activeUserDao.update("INSERT INTO `active_user`(`active_user_id`, `last_login_time`, `ip`) VALUES (?, ?, ?)",
                activeUserId, lastLoginTime, ip);
        logger.info("Attempting to add a new active user with ID: {}, Last Login Time: {}, IP: {}", activeUserId, lastLoginTime, ip);
        return update > 0;
    }

    public boolean deleteActiveUser(int activeUserId) {
        int update = activeUserDao.update("DELETE FROM `active_user` WHERE `active_user_id` = ?", activeUserId);
        logger.info("Attempting to delete active user with ID: {}", activeUserId);
        return update > 0;
    }

    public boolean updateActiveUser(int activeUserId, Timestamp lastLoginTime, String ip) {
        int update = activeUserDao.update("UPDATE `active_user` SET `last_login_time` = ?, `ip` = ? WHERE `active_user_id` = ?",
                lastLoginTime, ip, activeUserId);
        logger.info("Attempting to update active user with ID: {}, Last Login Time: {}, IP: {}", activeUserId, lastLoginTime, ip);
        return update > 0;
    }


}
