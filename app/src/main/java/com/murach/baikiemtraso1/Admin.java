package com.murach.baikiemtraso1;

public class Admin {
    private String id;
    private String adminName;
    private String adminEmail;
    private String websiteUrl;
    private String adminLogo;
    private String adminAddress;
    private String adminPhone;
    private String adminChildId;

    public Admin(String id, String adminName, String adminEmail, String websiteUrl, String adminLogo, String adminAddress, String adminPhone, String adminChildId) {
        this.id = id;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.websiteUrl = websiteUrl;
        this.adminLogo = adminLogo;
        this.adminAddress = adminAddress;
        this.adminPhone = adminPhone;
        this.adminChildId = adminChildId;
    }
    public Admin( String adminName, String adminEmail, String websiteUrl, String adminLogo, String adminAddress, String adminPhone, String adminChildId) {
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.websiteUrl = websiteUrl;
        this.adminLogo = adminLogo;
        this.adminAddress = adminAddress;
        this.adminPhone = adminPhone;
        this.adminChildId = adminChildId;
    }

    public Admin(String adminName, String adminEmail, String adminAddress, String adminPhone) {
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminAddress = adminAddress;
        this.adminPhone = adminPhone;
    }

    public Admin() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getAdminLogo() {
        return adminLogo;
    }

    public void setAdminLogo(String adminLogo) {
        this.adminLogo = adminLogo;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminChildId() {
        return adminChildId;
    }

    public void setAdminChildId(String adminChildId) {
        this.adminChildId = adminChildId;
    }


}
