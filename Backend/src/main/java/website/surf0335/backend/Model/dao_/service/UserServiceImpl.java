package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.UserDao;
import website.surf0335.backend.Model.dao_.domain.User;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserDao userDao = new UserDao();

    @Override
    public User queryUserById(int userId) {
        User user = userDao.querySingle("SELECT * FROM `user` WHERE user_id = ?", User.class, userId);
        logger.info("Queried User by ID {}: {}", userId, user);
        return user;
    }

    @Override
    public List<User> getPartUsers(int begin) {
        int start = begin + 1212120;
        int end = start + 20;
        return userDao.queryMultiple("SELECT * FROM user WHERE user_id BETWEEN ? AND ?", User.class, start, end);
    }



    @Override
    public User userLogin(String email){
        User user = userDao.querySingle("SELECT * FROM `user` WHERE email = ?", User.class, email);
        logger.info("Queried User by Email {}: {}", email, user);
        return user;
    }


    @Override
    public boolean updateUserPassword(String password, String email){
        return true;
    }

    @Override
    public User queryUserByEmail(String email) {
        User user = userDao.querySingle("SELECT * FROM `user` WHERE email = ?", User.class, email);
        logger.info("Queried User by Email {}: {}", email, user);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userDao.querySingle("SELECT * FROM `user` WHERE userName = ?", User.class, username);
        logger.info("Queried User by Username {}: {}", username, user);
        return user;
    }

    @Override
    public boolean addUser(User user) {
        boolean result = userDao.update(
                "INSERT INTO `user` (userName, fullName, email, passwordHash, phone, enable, github_login, facebook_login, google_login, membership, avatar, credits, visa_pay_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                user.getUserName(), user.getFullName(), user.getEmail(), user.getPasswordHash(), user.getPhone(),
                user.getEnable(), user.getGithub_login(), user.getFacebook_login(), user.getGoogle_login(),
                user.getMembership(), user.getAvatar(), user.getCredits(), user.getVisa_pay_id()
        ) > 0;
        logger.info("Added User {}: {}", user.getUserName(), result);
        return result;
    }

    @Override
    public Long getUserNumber(){
        Long a = (long) userDao.queryCount("user");
        return a;
    }

    @Override
    public boolean blockUser(int ID) {
        return userDao.update(
                "UPDATE `user` SET enable = ? WHERE user_id = ?", 1, ID
        ) > 0;
    }

    @Override
    public boolean unblockUser(int ID) {
        return userDao.update(
                "UPDATE `user` SET enable = ? WHERE user_id = ?", 0, ID
        ) > 0;
    }

    @Override
    public boolean checkEmail(String email) {
        System.out.println(email);
        User user = userDao.querySingle("SELECT * FROM `user` WHERE email = ?", User.class, email);
        return user != null;
    }

    @Override
    public boolean updateUser(User user) {
        boolean result = userDao.update(
                "UPDATE `user` SET userName = ?, fullName = ?, email = ?, passwordHash = ?, phone = ?, enable = ?, github_login = ?, facebook_login = ?, google_login = ?, membership = ?, avatar = ?, credits = ?, visa_pay_id = ? WHERE user_id = ?",
                user.getUserName(), user.getFullName(), user.getEmail(), user.getPasswordHash(), user.getPhone(),
                user.getEnable(), user.getGithub_login(), user.getFacebook_login(), user.getGoogle_login(),
                user.getMembership(), user.getAvatar(), user.getCredits(), user.getVisa_pay_id(), user.getUser_id()
        ) > 0;
        logger.info("Updated User {}: {}", user.getUser_id(), result);
        return result;
    }

    @Override
    public boolean updatePassword(User user) {
        boolean result = userDao.update(
                "UPDATE `user` SET  passwordHash = ? WHERE user_id = ?",
                user.getPasswordHash(), user.getUser_id()
        ) > 0;
        logger.info("Updated User {}: {}", user.getUser_id(), result);
        return result;
    }

    @Override
    public boolean deleteUser(int userId) {
        boolean result = userDao.update("DELETE FROM `user` WHERE user_id = ?", userId) > 0;
        logger.info("Deleted User ID {}: {}", userId, result);
        return result;
    }

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

//        // 测试查询用户ID
//        System.out.println("Testing queryUserById:");
//        User userById = userService.queryUserById(1212121);
//        System.out.println(userById);

        // 测试查询用户数量
//        System.out.println("Testing getUserNumber:");
//        System.out.println(userService.getUserNumber());

        User user = new User();
        user.setUserName("testname");
        user.setEmail("testemail");
        user.setPasswordHash("testpassword");

        userService.addUser(user);
//        user.setPasswordHash("123456");
//        user.setUser_id(1212330);

//        userService.updatePassword(user);


    }
    }

