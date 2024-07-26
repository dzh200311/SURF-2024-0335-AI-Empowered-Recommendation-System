package website.surf0335.backend.Model.dao_.domain;

public class Device {
    private Integer device_id;
    private String token;
    private String mac_address;
    private Integer enable;
    private String remove_times;

    public Device() {
    }

    public Device(Integer device_id, String token, String mac_address, Integer enable, String remove_times) {
        this.device_id = device_id;
        this.token = token;
        this.mac_address = mac_address;
        this.enable = enable;
        this.remove_times = remove_times;
    }

    public Integer getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Integer device_id) {
        this.device_id = device_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getRemove_times() {
        return remove_times;
    }

    public void setRemove_times(String remove_times) {
        this.remove_times = remove_times;
    }

    @Override
    public String toString() {
        return "Device{" +
                "device_id=" + device_id +
                ", token='" + token + '\'' +
                ", mac_address='" + mac_address + '\'' +
                ", enable=" + enable +
                ", remove_times='" + remove_times + '\'' +
                '}';
    }
}
