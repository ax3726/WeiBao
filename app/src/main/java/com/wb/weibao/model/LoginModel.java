package com.wb.weibao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/28.
 */

public class LoginModel implements Serializable {
    public String code  ;
    public String message  ;
    public  Data data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Userbean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public class Data implements Serializable  {
        public String loginAccount  ;
        public String createUserId  ;
        public String salt  ;
        public String updateUserId  ;
        public long updateTime  ;
        public String phoneNo  ;
        public String password  ;
        public String companyId  ;
        public Object roleIds  ;
        public long createTime  ;
        public String name  ;
        public Object startTime  ;
        public Object endTime  ;
        public int id  ;
        public String email  ;
        public String status  ;
        public  Institutions institutions;

        public String getLoginAccount() {
            return loginAccount;
        }

        public void setLoginAccount(String loginAccount) {
            this.loginAccount = loginAccount;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public Object getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(Object roleIds) {
            this.roleIds = roleIds;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Institutions getInstitutions() {
            return institutions;
        }

        public void setInstitutions(Institutions institutions) {
            this.institutions = institutions;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "loginAccount='" + loginAccount + '\'' +
                    ", createUserId='" + createUserId + '\'' +
                    ", salt='" + salt + '\'' +
                    ", updateUserId='" + updateUserId + '\'' +
                    ", updateTime=" + updateTime +
                    ", phoneNo='" + phoneNo + '\'' +
                    ", password='" + password + '\'' +
                    ", companyId='" + companyId + '\'' +
                    ", roleIds=" + roleIds +
                    ", createTime=" + createTime +
                    ", name='" + name + '\'' +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", id=" + id +
                    ", email='" + email + '\'' +
                    ", status='" + status + '\'' +
                    ", institutions=" + institutions +
                    ", userRoles=" + userRoles +
                    '}';
        }

        public class Institutions implements Serializable  {
            public String areaCityCode  ;
            public String createUserId  ;
            public String linkPhone  ;
            public String code  ;
            public String latitude  ;
            public int type  ;
            public String linkMan  ;
            public String parentCode  ;
            public Object startTime  ;
            public int id  ;
            public String longitude  ;
            public int level  ;
            public String updateUserId  ;
            public String principalName  ;
            public long updateTime  ;
            public String certificateNo  ;
            public String parentId  ;
            public String instType  ;
            public String parentName  ;
            public long createTime  ;
            public String linkEmergencyPhone  ;
            public Object principalArea  ;
            public String name  ;
            public String linkType  ;
            public Object endTime  ;
            public String shortName  ;
            public String status  ;
            public String certificateType  ;
            public String linkEmail  ;

            public String getAreaCityCode() {
                return areaCityCode;
            }

            public void setAreaCityCode(String areaCityCode) {
                this.areaCityCode = areaCityCode;
            }

            public String getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(String createUserId) {
                this.createUserId = createUserId;
            }

            public String getLinkPhone() {
                return linkPhone;
            }

            public void setLinkPhone(String linkPhone) {
                this.linkPhone = linkPhone;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getLinkMan() {
                return linkMan;
            }

            public void setLinkMan(String linkMan) {
                this.linkMan = linkMan;
            }

            public String getParentCode() {
                return parentCode;
            }

            public void setParentCode(String parentCode) {
                this.parentCode = parentCode;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getUpdateUserId() {
                return updateUserId;
            }

            public void setUpdateUserId(String updateUserId) {
                this.updateUserId = updateUserId;
            }

            public String getPrincipalName() {
                return principalName;
            }

            public void setPrincipalName(String principalName) {
                this.principalName = principalName;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getCertificateNo() {
                return certificateNo;
            }

            public void setCertificateNo(String certificateNo) {
                this.certificateNo = certificateNo;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getInstType() {
                return instType;
            }

            public void setInstType(String instType) {
                this.instType = instType;
            }

            public String getParentName() {
                return parentName;
            }

            public void setParentName(String parentName) {
                this.parentName = parentName;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getLinkEmergencyPhone() {
                return linkEmergencyPhone;
            }

            public void setLinkEmergencyPhone(String linkEmergencyPhone) {
                this.linkEmergencyPhone = linkEmergencyPhone;
            }

            public Object getPrincipalArea() {
                return principalArea;
            }

            public void setPrincipalArea(Object principalArea) {
                this.principalArea = principalArea;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLinkType() {
                return linkType;
            }

            public void setLinkType(String linkType) {
                this.linkType = linkType;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCertificateType() {
                return certificateType;
            }

            public void setCertificateType(String certificateType) {
                this.certificateType = certificateType;
            }

            public String getLinkEmail() {
                return linkEmail;
            }

            public void setLinkEmail(String linkEmail) {
                this.linkEmail = linkEmail;
            }

            @Override
            public String toString() {
                return "Institutions{" +
                        "areaCityCode='" + areaCityCode + '\'' +
                        ", createUserId='" + createUserId + '\'' +
                        ", linkPhone='" + linkPhone + '\'' +
                        ", code='" + code + '\'' +
                        ", latitude='" + latitude + '\'' +
                        ", type=" + type +
                        ", linkMan='" + linkMan + '\'' +
                        ", parentCode='" + parentCode + '\'' +
                        ", startTime=" + startTime +
                        ", id=" + id +
                        ", longitude='" + longitude + '\'' +
                        ", level=" + level +
                        ", updateUserId='" + updateUserId + '\'' +
                        ", principalName='" + principalName + '\'' +
                        ", updateTime=" + updateTime +
                        ", certificateNo='" + certificateNo + '\'' +
                        ", parentId='" + parentId + '\'' +
                        ", instType='" + instType + '\'' +
                        ", parentName='" + parentName + '\'' +
                        ", createTime=" + createTime +
                        ", linkEmergencyPhone='" + linkEmergencyPhone + '\'' +
                        ", principalArea=" + principalArea +
                        ", name='" + name + '\'' +
                        ", linkType='" + linkType + '\'' +
                        ", endTime=" + endTime +
                        ", shortName='" + shortName + '\'' +
                        ", status='" + status + '\'' +
                        ", certificateType='" + certificateType + '\'' +
                        ", linkEmail='" + linkEmail + '\'' +
                        '}';
            }
        }
        public List<UserRoles> userRoles;
        public class UserRoles implements Serializable  {
            public int roleId  ;
            public Object startTime  ;
            public Object endTime  ;
            public int id  ;
            public int userId  ;

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            @Override
            public String toString() {
                return "UserRoles{" +
                        "roleId=" + roleId +
                        ", startTime=" + startTime +
                        ", endTime=" + endTime +
                        ", id=" + id +
                        ", userId=" + userId +
                        '}';
            }
        }
    }
}

