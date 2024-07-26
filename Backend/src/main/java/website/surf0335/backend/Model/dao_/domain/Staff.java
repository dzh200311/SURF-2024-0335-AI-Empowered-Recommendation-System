package website.surf0335.backend.Model.dao_.domain;

public class Staff {
    private Integer staff_id;
    private String staff_name;
    private int position;
    private String date_birth;
    private String address;
    private String phone;
    private int shift;
    private Double salary;
    private double Employment_Status;
    private Integer store_id;

    private Integer role;
    private String email;
    private Integer enable;

    private String password;

    private String avatar;

    public Staff() {
    }

    public Staff(Integer staff_id, String avatar,String staff_name, int position, String date_birth, String address, String phone, int shift, Double salary, double employment_Status, Integer store_id, Integer role, String email, Integer enable, String password) {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.position = position;
        this.date_birth = date_birth;
        this.address = address;
        this.phone = phone;
        this.shift = shift;
        this.salary = salary;
        Employment_Status = employment_Status;
        this.store_id = store_id;
        this.role = role;
        this.email = email;
        this.enable = enable;
        this.password = password;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public double getEmployment_Status() {
        return Employment_Status;
    }

    public void setEmployment_Status(double employment_Status) {
        Employment_Status = employment_Status;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
