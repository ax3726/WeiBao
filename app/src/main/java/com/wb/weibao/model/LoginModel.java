package com.wb.weibao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/28.
 */

public class LoginModel implements Serializable {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"startTime":null,"endTime":null,"id":1,"name":"超级管理员","loginAccount":"admin","password":"******","salt":"***","phoneNo":"15868476323","email":"1","status":"0","companyId":"0001","createTime":1504681409000,"updateTime":1534847417000,"createUserId":"1","updateUserId":"1","projectId":null,"projectName":null,"companyCode":null,"companyName":null,"type":"1","userRoles":[{"startTime":null,"endTime":null,"id":9,"userId":1,"roleId":1}],"roleIds":null,"institutions":{"startTime":null,"endTime":null,"id":1,"code":"0001","name":"浙江创源消防科技有限公司","shortName":"创源消防","instType":"0","level":1,"type":0,"status":"0","areaCityCode":"310101","areaId":null,"principalArea":"浙江杭州","principalName":"王一晓","certificateType":"1","certificateNo":"2154656464","parentId":"null","parentName":"null","parentCode":"null","createUserId":"1","updateUserId":"1","createTime":1536752936000,"updateTime":1539605118000,"linkMan":"王一晓","linkPhone":"13777810881","linkType":"tel","linkEmail":"13425636325@qq.com","linkEmergencyPhone":"13777810881","latitude":"29.891850"}}
     */

    private String code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * startTime : null
         * endTime : null
         * id : 1
         * name : 超级管理员
         * loginAccount : admin
         * password : ******
         * salt : ***
         * phoneNo : 15868476323
         * email : 1
         * status : 0
         * companyId : 0001
         * createTime : 1504681409000
         * updateTime : 1534847417000
         * createUserId : 1
         * updateUserId : 1
         * projectId : null
         * projectName : null
         * companyCode : null
         * companyName : null
         * type : 1
         * userRoles : [{"startTime":null,"endTime":null,"id":9,"userId":1,"roleId":1}]
         * roleIds : null
         * institutions : {"startTime":null,"endTime":null,"id":1,"code":"0001","name":"浙江创源消防科技有限公司","shortName":"创源消防","instType":"0","level":1,"type":0,"status":"0","areaCityCode":"310101","areaId":null,"principalArea":"浙江杭州","principalName":"王一晓","certificateType":"1","certificateNo":"2154656464","parentId":"null","parentName":"null","parentCode":"null","createUserId":"1","updateUserId":"1","createTime":1536752936000,"updateTime":1539605118000,"linkMan":"王一晓","linkPhone":"13777810881","linkType":"tel","linkEmail":"13425636325@qq.com","linkEmergencyPhone":"13777810881","latitude":"29.891850"}
         */

        private Object startTime;
        private Object endTime;
        private int id;
        private String name;
        private String loginAccount;
        private String password;
        private String salt;
        private String phoneNo;
        private String email;
        private String status;
        private String companyId;
        private long createTime;
        private long updateTime;
        private String createUserId;
        private String updateUserId;
        private String projectId;
        private Object projectName;
        private Object companyCode;
        private Object companyName;
        private String type;
        private Object roleIds;
        private InstitutionsBean institutions;
        private List<UserRolesBean> userRoles;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLoginAccount() {
            return loginAccount;
        }

        public void setLoginAccount(String loginAccount) {
            this.loginAccount = loginAccount;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
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

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public String getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public Object getProjectName() {
            return projectName;
        }

        public void setProjectName(Object projectName) {
            this.projectName = projectName;
        }

        public Object getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(Object companyCode) {
            this.companyCode = companyCode;
        }

        public Object getCompanyName() {
            return companyName;
        }

        public void setCompanyName(Object companyName) {
            this.companyName = companyName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(Object roleIds) {
            this.roleIds = roleIds;
        }

        public InstitutionsBean getInstitutions() {
            return institutions;
        }

        public void setInstitutions(InstitutionsBean institutions) {
            this.institutions = institutions;
        }

        public List<UserRolesBean> getUserRoles() {
            return userRoles;
        }

        public void setUserRoles(List<UserRolesBean> userRoles) {
            this.userRoles = userRoles;
        }

        public static class InstitutionsBean {
            /**
             * startTime : null
             * endTime : null
             * id : 1
             * code : 0001
             * name : 浙江创源消防科技有限公司
             * shortName : 创源消防
             * instType : 0
             * level : 1
             * type : 0
             * status : 0
             * areaCityCode : 310101
             * areaId : null
             * principalArea : 浙江杭州
             * principalName : 王一晓
             * certificateType : 1
             * certificateNo : 2154656464
             * parentId : null
             * parentName : null
             * parentCode : null
             * createUserId : 1
             * updateUserId : 1
             * createTime : 1536752936000
             * updateTime : 1539605118000
             * linkMan : 王一晓
             * linkPhone : 13777810881
             * linkType : tel
             * linkEmail : 13425636325@qq.com
             * linkEmergencyPhone : 13777810881
             * latitude : 29.891850
             */

            private Object startTime;
            private Object endTime;
            private int id;
            private String code;
            private String name;
            private String shortName;
            private String instType;
            private int level;
            private int type;
            private String status;
            private String areaCityCode;
            private Object areaId;
            private String principalArea;
            private String principalName;
            private String certificateType;
            private String certificateNo;
            private String parentId;
            private String parentName;
            private String parentCode;
            private String createUserId;
            private String updateUserId;
            private long createTime;
            private long updateTime;
            private String linkMan;
            private String linkPhone;
            private String linkType;
            private String linkEmail;
            private String linkEmergencyPhone;
            private String latitude;

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

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }

            public String getInstType() {
                return instType;
            }

            public void setInstType(String instType) {
                this.instType = instType;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAreaCityCode() {
                return areaCityCode;
            }

            public void setAreaCityCode(String areaCityCode) {
                this.areaCityCode = areaCityCode;
            }

            public Object getAreaId() {
                return areaId;
            }

            public void setAreaId(Object areaId) {
                this.areaId = areaId;
            }

            public String getPrincipalArea() {
                return principalArea;
            }

            public void setPrincipalArea(String principalArea) {
                this.principalArea = principalArea;
            }

            public String getPrincipalName() {
                return principalName;
            }

            public void setPrincipalName(String principalName) {
                this.principalName = principalName;
            }

            public String getCertificateType() {
                return certificateType;
            }

            public void setCertificateType(String certificateType) {
                this.certificateType = certificateType;
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

            public String getParentName() {
                return parentName;
            }

            public void setParentName(String parentName) {
                this.parentName = parentName;
            }

            public String getParentCode() {
                return parentCode;
            }

            public void setParentCode(String parentCode) {
                this.parentCode = parentCode;
            }

            public String getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(String createUserId) {
                this.createUserId = createUserId;
            }

            public String getUpdateUserId() {
                return updateUserId;
            }

            public void setUpdateUserId(String updateUserId) {
                this.updateUserId = updateUserId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getLinkMan() {
                return linkMan;
            }

            public void setLinkMan(String linkMan) {
                this.linkMan = linkMan;
            }

            public String getLinkPhone() {
                return linkPhone;
            }

            public void setLinkPhone(String linkPhone) {
                this.linkPhone = linkPhone;
            }

            public String getLinkType() {
                return linkType;
            }

            public void setLinkType(String linkType) {
                this.linkType = linkType;
            }

            public String getLinkEmail() {
                return linkEmail;
            }

            public void setLinkEmail(String linkEmail) {
                this.linkEmail = linkEmail;
            }

            public String getLinkEmergencyPhone() {
                return linkEmergencyPhone;
            }

            public void setLinkEmergencyPhone(String linkEmergencyPhone) {
                this.linkEmergencyPhone = linkEmergencyPhone;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }
        }

        public static class UserRolesBean {
            /**
             * startTime : null
             * endTime : null
             * id : 9
             * userId : 1
             * roleId : 1
             */

            private Object startTime;
            private Object endTime;
            private int id;
            private int userId;
            private int roleId;

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

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }
        }
    }
}

