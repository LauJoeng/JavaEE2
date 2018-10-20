package com.yang.bean;

public class UserAddress {
    private Integer id;
    private String userAddress;
    private String userId;
    private String consignee;
    private String phoneNumber;
    private String isDefault;

    public UserAddress() {
        super();
    }

    public UserAddress(Integer id, String userAddress, String userId, String consignee, String phoneNumber, String isDefault) {
        this.id = id;
        this.userAddress = userAddress;
        this.userId = userId;
        this.consignee = consignee;
        this.phoneNumber = phoneNumber;
        this.isDefault = isDefault;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", userAddress='" + userAddress + '\'' +
                ", userId='" + userId + '\'' +
                ", consignee='" + consignee + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isDefault='" + isDefault + '\'' +
                '}';
    }
}
