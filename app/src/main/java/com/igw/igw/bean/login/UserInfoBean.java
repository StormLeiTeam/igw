package com.igw.igw.bean.login;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class UserInfoBean {


    /**
     * code : 200
     * data : {"accountNonExpired":true,"accountNonLocked":true,"agencyName":"明咯咙","birthday":"2017-04-20","cityId":1,"cnBalance":0,"countryId":1,"credentialsNonExpired":true,"ctime":"2020-04-20 15:52:53","email":"578864539@qq.com","enBalance":0,"enabled":true,"firstName":"你就","hasPay":1,"headImage":"","id":1,"inviteCode":"","lastLoginIp":"219.142.145.78","lastLoginTime":"2020-04-28 19:04:58","lastName":"民","lastPasswordReset":"2020-04-28 19:07:26","mobilePhone":"18235188642","nickName":"hah\n哈哈","password":"$2a$10$Ay7MKcILBv3DNin7wdAAtu5zzclz3ROOg4.GSoLlYurNcQQPUVPEK","rongyunToken":"","sex":1,"sortWithOutOrderBy":"","sort_":"","status":0,"userDesc":"我哈哈哈哈哈哈哈哈","userName":"18235188642","userType":4,"utime":"2020-04-28 19:07:26"}
     * message : 操作成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * accountNonExpired : true
         * accountNonLocked : true
         * agencyName : 明咯咙
         * birthday : 2017-04-20
         * cityId : 1
         * cnBalance : 0.0
         * countryId : 1
         * credentialsNonExpired : true
         * ctime : 2020-04-20 15:52:53
         * email : 578864539@qq.com
         * enBalance : 0.0
         * enabled : true
         * firstName : 你就
         * hasPay : 1
         * headImage :
         * id : 1
         * inviteCode :
         * lastLoginIp : 219.142.145.78
         * lastLoginTime : 2020-04-28 19:04:58
         * lastName : 民
         * lastPasswordReset : 2020-04-28 19:07:26
         * mobilePhone : 18235188642
         * nickName : hah
         哈哈
         * password : $2a$10$Ay7MKcILBv3DNin7wdAAtu5zzclz3ROOg4.GSoLlYurNcQQPUVPEK
         * rongyunToken :
         * sex : 1
         * sortWithOutOrderBy :
         * sort_ :
         * status : 0
         * userDesc : 我哈哈哈哈哈哈哈哈
         * userName : 18235188642
         * userType : 4
         * utime : 2020-04-28 19:07:26
         */

        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private String agencyName;
        private String birthday;
        private int cityId;
        private double cnBalance;
        private int countryId;
        private boolean credentialsNonExpired;
        private String ctime;
        private String email;
        private double enBalance;
        private boolean enabled;
        private String firstName;
        private int hasPay;
        private String headImage;
        private int id;
        private String inviteCode;
        private String lastLoginIp;
        private String lastLoginTime;
        private String lastName;
        private String lastPasswordReset;
        private String mobilePhone;
        private String nickName;
        private String password;
        private String rongyunToken;
        private int sex;
        private String sortWithOutOrderBy;
        private String sort_;
        private int status;
        private String userDesc;
        private String userName;
        private int userType;
        private String utime;

        public boolean isAccountNonExpired() {
            return accountNonExpired;
        }

        public void setAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
        }

        public boolean isAccountNonLocked() {
            return accountNonLocked;
        }

        public void setAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
        }

        public String getAgencyName() {
            return agencyName;
        }

        public void setAgencyName(String agencyName) {
            this.agencyName = agencyName;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public double getCnBalance() {
            return cnBalance;
        }

        public void setCnBalance(double cnBalance) {
            this.cnBalance = cnBalance;
        }

        public int getCountryId() {
            return countryId;
        }

        public void setCountryId(int countryId) {
            this.countryId = countryId;
        }

        public boolean isCredentialsNonExpired() {
            return credentialsNonExpired;
        }

        public void setCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public double getEnBalance() {
            return enBalance;
        }

        public void setEnBalance(double enBalance) {
            this.enBalance = enBalance;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public int getHasPay() {
            return hasPay;
        }

        public void setHasPay(int hasPay) {
            this.hasPay = hasPay;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getLastLoginIp() {
            return lastLoginIp;
        }

        public void setLastLoginIp(String lastLoginIp) {
            this.lastLoginIp = lastLoginIp;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getLastPasswordReset() {
            return lastPasswordReset;
        }

        public void setLastPasswordReset(String lastPasswordReset) {
            this.lastPasswordReset = lastPasswordReset;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRongyunToken() {
            return rongyunToken;
        }

        public void setRongyunToken(String rongyunToken) {
            this.rongyunToken = rongyunToken;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getSortWithOutOrderBy() {
            return sortWithOutOrderBy;
        }

        public void setSortWithOutOrderBy(String sortWithOutOrderBy) {
            this.sortWithOutOrderBy = sortWithOutOrderBy;
        }

        public String getSort_() {
            return sort_;
        }

        public void setSort_(String sort_) {
            this.sort_ = sort_;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUserDesc() {
            return userDesc;
        }

        public void setUserDesc(String userDesc) {
            this.userDesc = userDesc;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getUtime() {
            return utime;
        }

        public void setUtime(String utime) {
            this.utime = utime;
        }
    }
}
