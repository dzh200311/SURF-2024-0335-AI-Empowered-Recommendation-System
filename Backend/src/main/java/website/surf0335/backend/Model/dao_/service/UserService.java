package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.User;

import java.util.List;

@Repository
public interface UserService {

//    public int getUserCount();

    public boolean unblockUser(int ID);

    public boolean checkEmail(String email);

    public boolean updatePassword(User user);
    public List<User> getPartUsers(int begin);
    public boolean blockUser(int ID);

    public Long getUserNumber();

    public User userLogin(String email);
    User queryUserById(int userId);
    User queryUserByEmail(String email);
    boolean addUser(User user);
    boolean updateUser(User user);
    public boolean updateUserPassword(String password, String email);
    boolean deleteUser(int userId);
    User getUserByUsername(String username);
}
