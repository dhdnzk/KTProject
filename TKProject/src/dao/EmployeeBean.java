package dao;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * EmployeeBean
 *
 * @author NekoToken
 * @version 1.0.0
 */
// TODO : AddComment
public class EmployeeBean {

    private byte sex;

    private String empCode;

    private String lName;

    private String fName;

    private String lKana;

    private String fKana;

    private String sectionCode;

    private Date birth;

    private Date empDate;

    private Timestamp updateDate;

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setLKana(String lKana) {
        this.lKana = lKana;
    }

    public void setFKana(String fKana) {
        this.fKana = fKana;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setEmpDate(Date empDate) {
        this.empDate = empDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public byte getSex() {
        return sex;
    }

    public String getEmpCode() {
        return empCode;
    }

    public String getLName() {
        return lName;
    }

    public String getFName() {
        return fName;
    }

    public String getLKana() {
        return lKana;
    }

    public String getFKana() {
        return fKana;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public Date getBirth() {
        return birth;
    }

    public Date getEmpDate() {
        return empDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "sex=" + sex +
                ", empCode='" + empCode + '\'' +
                ", lName='" + lName + '\'' +
                ", fName='" + fName + '\'' +
                ", lKana='" + lKana + '\'' +
                ", fKana='" + fKana + '\'' +
                ", sectionCode='" + sectionCode + '\'' +
                ", birth=" + birth +
                ", empDate=" + empDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
