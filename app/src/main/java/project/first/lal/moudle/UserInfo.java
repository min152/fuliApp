package project.first.lal.moudle;

import java.util.Date;

public class UserInfo {
    /**
     * 每页大小 默认10条
     */
    public Integer pageSize = 10;

    /**
     * 起始页
     */
    public Integer startRow;

    /**
     * 总页数
     */
    public Integer totalPage;


    private Integer userId;

    private String userName;

    private Integer userStatus;

    private Integer userSex;

    private Integer certStatus;

    private String userPwd;

    private String userHeadPic;

    private String cardFrontPic;

    private String cardBackPic;

    private String email;

    private String phone;

    private String mobilePhone;

    private Date addTime;

    private Date modifyTime;

    private String modifyStartTime;
    private String modifyEndTime;

    private String userAccount;

    private String companyId;
    private String companyName;

    private String manageStatus;

    private String userAddress;

    private String userPos;

    private Integer userType;

    private Date lastLoginTime;
    private String lastLoginStartTime;
    private String lastLoginEndTime;



    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModifyStartTime() {
        return modifyStartTime;
    }

    public void setModifyStartTime(String modifyStartTime) {
        this.modifyStartTime = modifyStartTime;
    }

    public String getModifyEndTime() {
        return modifyEndTime;
    }

    public void setModifyEndTime(String modifyEndTime) {
        this.modifyEndTime = modifyEndTime;
    }

    public String getLastLoginStartTime() {
        return lastLoginStartTime;
    }

    public void setLastLoginStartTime(String lastLoginStartTime) {
        this.lastLoginStartTime = lastLoginStartTime;
    }

    public String getLastLoginEndTime() {
        return lastLoginEndTime;
    }

    public void setLastLoginEndTime(String lastLoginEndTime) {
        this.lastLoginEndTime = lastLoginEndTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(Integer certStatus) {
        this.certStatus = certStatus;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserHeadPic() {
        return userHeadPic;
    }

    public void setUserHeadPic(String userHeadPic) {
        this.userHeadPic = userHeadPic == null ? null : userHeadPic.trim();
    }

    public String getCardFrontPic() {
        return cardFrontPic;
    }

    public void setCardFrontPic(String cardFrontPic) {
        this.cardFrontPic = cardFrontPic == null ? null : cardFrontPic.trim();
    }

    public String getCardBackPic() {
        return cardBackPic;
    }

    public void setCardBackPic(String cardBackPic) {
        this.cardBackPic = cardBackPic == null ? null : cardBackPic.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getManageStatus() {
        return manageStatus;
    }

    public void setManageStatus(String manageStatus) {
        this.manageStatus = manageStatus == null ? null : manageStatus.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserPos() {
        return userPos;
    }

    public void setUserPos(String userPos) {
        this.userPos = userPos == null ? null : userPos.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}