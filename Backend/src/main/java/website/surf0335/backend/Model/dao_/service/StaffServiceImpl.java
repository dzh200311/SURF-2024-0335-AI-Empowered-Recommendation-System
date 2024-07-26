package website.surf0335.backend.Model.dao_.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.dao.StaffDao;
import website.surf0335.backend.Model.dao_.dao.UserDao;
import website.surf0335.backend.Model.dao_.domain.Staff;

import java.util.List;

@Repository
public class StaffServiceImpl implements StaffService {
    private StaffDao staffDao = new StaffDao();
    private UserDao userDao = new UserDao();
    private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Override
    public Staff staffLogin(String email){
        Staff user = staffDao.querySingle("SELECT * FROM `staff` WHERE email = ?", Staff.class, email);
        logger.info("Queried staff by Email {}: {}", email, user);
        return user;
    };

    @Override
    public boolean blockStaff(int ID) {
        return userDao.update(
                "UPDATE `staff` SET enable = ? WHERE staff_id = ?", 1, ID
        ) > 0;
    }

    @Override
    public boolean unblockStaff(int ID) {
        return userDao.update(
                "UPDATE `staff` SET enable = ? WHERE staff_id = ?", 0, ID
        ) > 0;
    }

    @Override
    public Long getStaffNumberInTotal(){
        Long a = (long) userDao.queryCount("staff");
        return a;
    }

    @Override
    public Long getStaffNumberByRole(int role){
        Long a = (long) staffDao.queryCountByRole(role);
        return a;
    }

    @Override
    public List<Staff> getPartStaff(int begin, int role) {
        System.out.println(begin);
        return staffDao.queryMultiple("SELECT * FROM staff WHERE role = ? LIMIT ?, 20",
                Staff.class, role, begin);
    }

    public Staff querySingleByStaffId(int staffId) {
        logger.info("Querying for staff with ID: {}", staffId);
        return staffDao.querySingle("SELECT * FROM `staff` WHERE `staff_id` = ?", Staff.class, staffId);
    }

    public List<Staff> queryAllStaff() {
        logger.info("Querying for all staff");
        List<Staff> staffList = staffDao.queryMultiple("SELECT * FROM `staff`", Staff.class);
        logger.info("Number of staff retrieved: {}", staffList.size());
        return staffList;
    }

    public boolean addStaff(Staff staff) {
        return staffDao.update("INSERT INTO staff (staff_id, staff_name, position, shift, salary, employment_status, store_id, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                getLastStaffID() + 1,staff.getStaff_name(), staff.getPosition(), staff.getShift(), staff.getSalary(), staff.getEmployment_Status(), staff.getStore_id(), staff.getRole())
                > 0;
    }

    public int getLastStaffID() {
        return staffDao.getLastStaffId();
    }



    public boolean deleteStaff(int staffId) {
        logger.info("Attempting to delete staff with ID: {}", staffId);
        int update = staffDao.update("DELETE FROM `staff` WHERE `staff_id` = ?", staffId);
        return update > 0;
    }

    public boolean updateStaff(int staffId, String staffName, String position, String dateOfBirth, String address,
                               String phone, String shift, Double salary, String employmentStatus, Integer storeId) {
        logger.info("Updating staff ID: {}, Name: {}, Position: {}, Date of Birth: {}, Address: {}, Phone: {}, Shift: {}, Salary: {}, Employment Status: {}, Store ID: {}",
                staffId, staffName, position, dateOfBirth, address, phone, shift, salary, employmentStatus, storeId);
        int update = staffDao.update("UPDATE `staff` SET `staff_name` = ?, `position` = ?, `date_birth` = ?, `address` = ?, `phone` = ?, `shift` = ?, `salary` = ?, `employment_status` = ?, `store_id` = ? WHERE `staff_id` = ?",
                staffName, position, dateOfBirth, address, phone, shift, salary, employmentStatus, storeId, staffId);
        return update > 0;
    }

    public static void main(String[] args) {
        StaffServiceImpl staffService = new StaffServiceImpl();
        System.out.println(staffService.getLastStaffID());
    }


}
