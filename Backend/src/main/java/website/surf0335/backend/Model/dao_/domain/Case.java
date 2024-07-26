package website.surf0335.backend.Model.dao_.domain;

import java.sql.Timestamp;

public class Case {
    private Integer case_id;
    private Integer staff_id;
    private Integer user_id;
    private String record;
    private Integer state;
    private Timestamp start_date;
    private Timestamp end_date;

    public Case() {
    }

    public Case(int case_id, int staff_id, int user_id, String record, int state, Timestamp start_date, Timestamp end_date) {
        this.case_id = case_id;
        this.staff_id = staff_id;
        this.user_id = user_id;
        this.record = record;
        this.state = state;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "Case{" +
                "case_id=" + case_id +
                ", staff_id=" + staff_id +
                ", user_id=" + user_id +
                ", record='" + record + '\'' +
                ", state=" + state +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                '}';
    }
}


