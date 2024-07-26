package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.Case;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CaseService {
    Case querySingleByCaseId(int caseId);

    /**
     * Retrieves a list of case records associated with a specific user ID.
     * @param userId the ID of the user whose cases are to be retrieved
     * @return a list of case records
     */
    List<Case> queryMultipleByUserId(int userId);

    /**
     * Adds a new case record to the database.
     * @param caseId the ID of the new case (assumes case ID is provided, which might be unusual)
     * @param staffId the ID of the staff member associated with the case
     * @param userId the ID of the user associated with the case
     * @param record a string detailing the record of the case
     * @param state the current state of the case
     * @param startDate the start date of the case
     * @param endDate the end date of the case
     * @return true if the addition is successful, false otherwise
     */
    boolean addCase(int caseId, int staffId, int userId, String record, int state, Timestamp startDate, Timestamp endDate);

    /**
     * Deletes a case record based on its ID.
     * @param caseId the ID of the case to delete
     * @return true if the deletion is successful, false otherwise
     */
    boolean deleteCase(int caseId);

    /**
     * Updates an existing case record.
     * @param caseId the ID of the case to update
     * @param staffId the ID of the staff member associated with the case
     * @param userId the ID of the user associated with the case
     * @param record updated details of the case record
     * @param state the updated state of the case
     * @param startDate the updated start date of the case
     * @param endDate the updated end date of the case
     * @return true if the update is successful, false otherwise
     */
    boolean updateCase(int caseId, int staffId, int userId, String record, int state, Timestamp startDate, Timestamp endDate);

}

