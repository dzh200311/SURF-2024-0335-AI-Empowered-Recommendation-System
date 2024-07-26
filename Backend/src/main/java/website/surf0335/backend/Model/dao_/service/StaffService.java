package website.surf0335.backend.Model.dao_.service;

import org.springframework.stereotype.Repository;
import website.surf0335.backend.Model.dao_.domain.Staff;

import java.util.List;

@Repository
public interface StaffService {

    public Staff staffLogin(String email);
    public Long getStaffNumberByRole(int role);

    public Long getStaffNumberInTotal();

    public boolean unblockStaff(int ID);

    public boolean blockStaff(int ID);

    public List<Staff> getPartStaff(int begin, int role);
    Staff querySingleByStaffId(int staffId);
    List<Staff> queryAllStaff();
    boolean addStaff(Staff staff);
    boolean deleteStaff(int staffId);
    boolean updateStaff(int staffId, String staffName, String position, String dateOfBirth, String address,
                        String phone, String shift, Double salary, String employmentStatus, Integer storeId);
}
