package com.murach.baikiemtraso1;

public class Employee {
    private String id;
    private String employeeName;
    private String employeeRole;

    public Employee() {
    }

    private String employeeEmail;
    private String employeePhone;

    public Employee(String id, String employeeName,  String employeeEmail, String employeePhone, String employeeAvatar, String adminId) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
        this.employeeAvatar = employeeAvatar;
        this.adminId = adminId;
    }

    public Employee(String id, String employeeName, String employeeRole, String employeeEmail, String employeePhone, String employeeAvatar, String adminId) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
        this.employeeAvatar = employeeAvatar;
        this.adminId = adminId;
    }

    private String employeeAvatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeAvatar() {
        return employeeAvatar;
    }

    public void setEmployeeAvatar(String employeeAvatar) {
        this.employeeAvatar = employeeAvatar;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    private String adminId;

}
