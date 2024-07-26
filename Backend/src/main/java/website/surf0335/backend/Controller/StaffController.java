package website.surf0335.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import website.surf0335.backend.Model.dao_.domain.Staff;
import website.surf0335.backend.Model.dao_.service.StaffService;
import website.surf0335.backend.Service.TokenService;
import website.surf0335.backend.utils.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/add_staff")
    public boolean addStaff(@RequestParam("staff_name") String staff_name,
                            @RequestParam("position") int position,
                            @RequestParam("shift") int shift,
                            @RequestParam("salary") Double salary,
                            @RequestParam("store_id") int store_id,
                            @RequestParam("role") int role){
        Staff staff = new Staff();
        staff.setRole(role);
        staff.setEnable(99);
        staff.setStaff_name(staff_name);
        staff.setPosition(position);
        staff.setShift(shift);
        staff.setSalary(salary);
        staff.setEmployment_Status(0);
        staff.setStore_id(store_id);
        return staffService.addStaff(staff);
    }

    @GetMapping("/get_staff_pages")
    public int getStaffPages(@RequestParam("role") int role){
        Long total = staffService.getStaffNumberByRole(role);
        System.out.println("total: " + total);
        System.out.println("total pages :" + (int) Math.ceil(total / 20.0));
        return (int) Math.ceil(total / 20.0);
    }

    @GetMapping("/get_staff_number_in_role")
    public int getStaffByRole(@RequestParam("role") int role){
        Long total = staffService.getStaffNumberByRole(role);
        System.out.println("role "+role+": " + total);
        return total.intValue();
    }

    @GetMapping("/get_staff_total")
    public int getStaffTotal(){
        Long total = staffService.getStaffNumberInTotal();
        System.out.println("total: " + total);
        return total.intValue();
    }

    @PostMapping("/login")
    public Result login(@RequestParam("email") String email, @RequestParam("password") String password){

        Staff staff = staffService.staffLogin(email);
        System.out.println("user: " + staff);
        if (staff != null) {
            System.out.println(staff);
            if (staff.getPassword().equals(password)){
                Map<String,String> message = new HashMap<>();
                String token = tokenService.generateTempToken(String.valueOf(staff.getStaff_id()));
                message.put("token", token);
                message.put("role", String.valueOf(staff.getRole()));
                message.put("staff_id", String.valueOf(staff.getStaff_id()));
                message.put("staff_name", staff.getStaff_name());
                message.put("avatar", staff.getAvatar());
                return Result.ok().data(message);
            }else {
                return Result.error().data("error", "password error");
            }
        }else {
            return Result.error().data("error", "user not found");
        }

    }

    @PostMapping("/block_staff")
    public boolean blockStaff(@RequestParam("id") int id){
        return staffService.blockStaff(id);
    }

    @PostMapping("/unblock_staff")
    public boolean unblockStaff(@RequestParam("id") int id){
        return staffService.unblockStaff(id);
    }

    @GetMapping("/get_all_staff")
    public List<Map<String, Object>> getAllStaff(@RequestParam("page") int page, @RequestParam("role") int role){
        List<Map<String, Object>> userList = new ArrayList<>();
        int begin = (page - 1) * 20;
        List<Staff> staffs = staffService.getPartStaff(begin,role);
        System.out.println(staffs);


        for (Staff staff : staffs) {
            Map<String, Object> staffMap = new HashMap<>();
            staffMap.put("name", staff.getStaff_name());
            staffMap.put("email", staff.getEmail());
            staffMap.put("staff_ID", staff.getStaff_id());
            staffMap.put("phone", staff.getPhone());
            staffMap.put("enable", staff.getEnable());
            staffMap.put("role", staff.getRole());
            staffMap.put("position", staff.getPosition());
            staffMap.put("date_birth", staff.getDate_birth());
            staffMap.put("address", staff.getAddress());
            staffMap.put("shift", staff.getShift());
            staffMap.put("salary", staff.getSalary());
            staffMap.put("employment_status", staff.getEmployment_Status());
            staffMap.put("store_id", staff.getStore_id());


            userList.add(staffMap);
        }
        System.out.println("staffList: " + userList);

        return userList;
    }
}
