package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.VisaPayDao;
import website.surf0335.backend.Model.dao_.domain.VisaPay;

import java.util.List;
@Repository
public class VisaPayServiceImpl implements VisaPayService{
    private VisaPayDao visaPayDao = new VisaPayDao();
    private static final Logger logger = LoggerFactory.getLogger(VisaPayServiceImpl.class);

    public VisaPay querySingleById(int id) {
        logger.info("Querying for VisaPay with ID: {}", id);
        return visaPayDao.querySingle("SELECT * FROM `visa_pay` WHERE id = ?", VisaPay.class, id);
    }

    public List<VisaPay> queryMultipleByUserId(int userId) {
        logger.info("Querying for VisaPay entries by user ID: {}", userId);
        List<VisaPay> visaPays = visaPayDao.queryMultiple("SELECT * FROM `visa_pay` WHERE user_id = ?", VisaPay.class, userId);
        logger.info("Number of VisaPay entries retrieved for user ID {}: {}", userId, visaPays.size());
        return visaPays;
    }

    public boolean addVisaPay(int userId, String bankName, String type, String cardNumber, String expireDate, String secret, String isRecentUse) {
        logger.info("Adding new VisaPay for user ID: {}, Bank Name: {}, Type: {}, Card Number: {}, Expire Date: {}, Secret: [PROTECTED], Is Recent Use: {}",
                userId, bankName, type, cardNumber, expireDate, isRecentUse);
        int update = visaPayDao.update("INSERT INTO `visa_pay`(`user_id`, `bank_name`, `type`, `card_number`, `expire_date`, `secret`, `is_recent_use`) VALUES ( ?, ?, ?, ?, ?, ?, ?)",
                userId, bankName, type, cardNumber, expireDate, secret, isRecentUse);
        return update > 0;
    }

    public boolean deleteVisaPay(int id) {
        logger.info("Attempting to delete VisaPay with ID: {}", id);
        int update = visaPayDao.update("DELETE FROM `visa_pay` WHERE `id` = ?", id);
        return update > 0;
    }

    public boolean updateVisaPay(int id, int userId, String bankName, String type, String cardNumber, String expireDate, String secret, String isRecentUse) {
        logger.info("Updating VisaPay ID: {}, for user ID: {}, Bank Name: {}, Type: {}, Card Number: {}, Expire Date: {}, Secret: [PROTECTED], Is Recent Use: {}",
                id, userId, bankName, type, cardNumber, expireDate, isRecentUse);
        int update = visaPayDao.update("UPDATE `visa_pay` SET `user_id` = ?, `bank_name` = ?, `type` = ?, `card_number` = ?, `expire_date` = ?, `secret` = ?, `is_recent_use` = ? WHERE `id` = ?",
                userId, bankName, type, cardNumber, expireDate, secret, isRecentUse, id);
        return update > 0;
    }


}
