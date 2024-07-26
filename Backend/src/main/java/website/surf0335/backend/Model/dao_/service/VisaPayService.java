package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.VisaPay;

import java.util.List;

@Repository
public interface VisaPayService {
    VisaPay querySingleById(int id);
    List<VisaPay> queryMultipleByUserId(int userId);
    boolean addVisaPay(int userId, String bankName, String type, String cardNumber, String expireDate, String secret, String isRecentUse);
    boolean deleteVisaPay(int id);
    boolean updateVisaPay(int id, int userId, String bankName, String type, String cardNumber, String expireDate, String secret, String isRecentUse);
}
