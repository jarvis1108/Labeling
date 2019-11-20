package com.jhc.entity;
import java.sql.Timestamp;

public class User {
    private String username;
    private String password;
    private String sex;
    private String age;
    private String education;
    private String profession;
    private String labeling_exp;
    private String reading_exp;
    private String account;
    private Timestamp finish_time;
    private String valid;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLabeling_exp() {
        return labeling_exp;
    }

    public void setLabeling_exp(String labeling_exp) {
        this.labeling_exp = labeling_exp;
    }

    public String getReading_exp() {
        return reading_exp;
    }

    public void setReading_exp(String reading_exp) {
        this.reading_exp = reading_exp;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Timestamp getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(Timestamp create_time) {
        this.finish_time = create_time;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
