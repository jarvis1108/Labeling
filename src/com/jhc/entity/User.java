package com.jhc.entity;

public class User {
    private String username;
    private String password;
    private String sex;
    private String age;
    private String education;
    private String profession;
    private String labeling_exp;
    private String reading_exp;
    private int interfaceId;

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

    public int getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(int interfaceId) {
        this.interfaceId = interfaceId;
    }
}
