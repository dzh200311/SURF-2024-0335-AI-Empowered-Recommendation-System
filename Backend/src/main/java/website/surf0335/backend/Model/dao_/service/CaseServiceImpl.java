package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.CaseDao;
import website.surf0335.backend.Model.dao_.domain.Case;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class CaseServiceImpl implements CaseService {
    private CaseDao caseDao = new CaseDao();
    private static final Logger logger = LoggerFactory.getLogger(CaseServiceImpl.class);

    public Case querySingleByCaseId(int caseId) {
        logger.info("Querying for case with ID: {}", caseId);
        Case QUERY = caseDao.querySingle("SELECT * FROM `case` WHERE `case_id` = ?", Case.class, caseId);
        return caseDao.querySingle("SELECT * FROM `case` WHERE `case_id` = ?", Case.class, caseId);
    }

    public List<Case> queryMultipleByUserId(int userId) {
        logger.info("Querying for cases by user ID: {}", userId);
        List<Case> cases = caseDao.queryMultiple("SELECT * FROM `case` WHERE `user_id` = ?", Case.class, userId);
        logger.info("Number of cases found for user ID {}: {}", userId, cases.size());
        return cases;
    }

    public boolean addCase(int caseId, int staffId, int userId, String record, int state, Timestamp startDate, Timestamp endDate) {
        logger.info("Adding new case with ID: {}, Staff ID: {}, User ID: {}, State: {}, Start Date: {}, End Date: {}", caseId, staffId, userId, state, startDate, endDate);
        int update = caseDao.update("INSERT INTO `case`(`case_id`, `staff_id`, `user_id`, `record`, `state`, `start_date`, `end_date`) VALUES (?, ?, ?, ?, ?, ?, ?)",
                caseId, staffId, userId, record, state, startDate, endDate);
        return update > 0;
    }

    public boolean deleteCase(int caseId) {
        logger.info("Attempting to delete case with ID: {}", caseId);
        int update = caseDao.update("DELETE FROM `case` WHERE `case_id` = ?", caseId);
        return update > 0;
    }

    public boolean updateCase(int caseId, int staffId, int userId, String record, int state, Timestamp startDate, Timestamp endDate) {
        logger.info("Updating case ID: {}, Staff ID: {}, User ID: {}, Record: '{}', State: {}, Start Date: {}, End Date: {}", caseId, staffId, userId, record, state, startDate, endDate);
        int update = caseDao.update("UPDATE `case` SET `staff_id` = ?, `user_id` = ?, `record` = ?, `state` = ?, `start_date` = ?, `end_date` = ? WHERE `case_id` = ?",
                staffId, userId, record, state, startDate, endDate, caseId);
        boolean success = update > 0;
        if (success) {
            logger.info("Successfully updated case with ID: {}", caseId);
        } else {
            logger.warn("Failed to update case with ID: {}", caseId);
        }
        return update > 0;
    }


}
