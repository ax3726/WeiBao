package com.wb.weibao.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/28.
 */

public class LoginModel implements Serializable {


    /**
     * code : 200
     * data : {"authenticated":true,"authorities":[{"authority":"1"},{"authority":"ConfigurationMenu"},{"authority":"RoleManage"},{"authority":"add"},{"authority":"advice/list"},{"authority":"application"},{"authority":"attendanceManagement"},{"authority":"camera/list"},{"authority":"collector/list"},{"authority":"controlSchedule"},{"authority":"device"},{"authority":"duty/shift/list"},{"authority":"earlyWarning"},{"authority":"handover/list"},{"authority":"institutions/list"},{"authority":"intention/list"},{"authority":"maintenance/record/list"},{"authority":"mechanism/list"},{"authority":"news/list"},{"authority":"newsManage"},{"authority":"order/list"},{"authority":"organizational"},{"authority":"project/control/person/list"},{"authority":"project/list"},{"authority":"record/list"},{"authority":"shift/schedule/list"},{"authority":"signup/list"},{"authority":"templet/list"},{"authority":"user/list"},{"authority":"videoManagement"}],"credentials":null,"details":{"remoteAddress":"112.17.170.163","sessionId":"","verifyCode":""},"name":"admin","principal":{"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"email":"1343411580@qq.com","enabled":true,"idNumber":"362330195603082255","idType":"01","instCode":"0001","isFireSafePerson":false,"name":"超级管理员","phoneNo":"15868476323","projectId":"","status":"0","type":"1","userId":"1","username":"admin"}}
     * message : 登录成功
     */

    private String code;
    private DataBean data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * authenticated : true
         * authorities : [{"authority":"1"},{"authority":"ConfigurationMenu"},{"authority":"RoleManage"},{"authority":"add"},{"authority":"advice/list"},{"authority":"application"},{"authority":"attendanceManagement"},{"authority":"camera/list"},{"authority":"collector/list"},{"authority":"controlSchedule"},{"authority":"device"},{"authority":"duty/shift/list"},{"authority":"earlyWarning"},{"authority":"handover/list"},{"authority":"institutions/list"},{"authority":"intention/list"},{"authority":"maintenance/record/list"},{"authority":"mechanism/list"},{"authority":"news/list"},{"authority":"newsManage"},{"authority":"order/list"},{"authority":"organizational"},{"authority":"project/control/person/list"},{"authority":"project/list"},{"authority":"record/list"},{"authority":"shift/schedule/list"},{"authority":"signup/list"},{"authority":"templet/list"},{"authority":"user/list"},{"authority":"videoManagement"}]
         * credentials : null
         * details : {"remoteAddress":"112.17.170.163","sessionId":"","verifyCode":""}
         * name : admin
         * principal : {"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"email":"1343411580@qq.com","enabled":true,"idNumber":"362330195603082255","idType":"01","instCode":"0001","isFireSafePerson":false,"name":"超级管理员","phoneNo":"15868476323","projectId":"","status":"0","type":"1","userId":"1","username":"admin"}
         */

        private boolean authenticated;
        private Object credentials;
        private DetailsBean details;
        private String name;
        private PrincipalBean principal;
        private List<AuthoritiesBean> authorities;

        public boolean isAuthenticated() {
            return authenticated;
        }

        public void setAuthenticated(boolean authenticated) {
            this.authenticated = authenticated;
        }

        public Object getCredentials() {
            return credentials;
        }

        public void setCredentials(Object credentials) {
            this.credentials = credentials;
        }

        public DetailsBean getDetails() {
            return details;
        }

        public void setDetails(DetailsBean details) {
            this.details = details;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public PrincipalBean getPrincipal() {
            return principal;
        }

        public void setPrincipal(PrincipalBean principal) {
            this.principal = principal;
        }

        public List<AuthoritiesBean> getAuthorities() {
            return authorities;
        }

        public void setAuthorities(List<AuthoritiesBean> authorities) {
            this.authorities = authorities;
        }

        public static class DetailsBean {
            /**
             * remoteAddress : 112.17.170.163
             * sessionId :
             * verifyCode :
             */

            private String remoteAddress;
            private String sessionId;
            private String verifyCode;

            public String getRemoteAddress() {
                return remoteAddress;
            }

            public void setRemoteAddress(String remoteAddress) {
                this.remoteAddress = remoteAddress;
            }

            public String getSessionId() {
                return sessionId;
            }

            public void setSessionId(String sessionId) {
                this.sessionId = sessionId;
            }

            public String getVerifyCode() {
                return verifyCode;
            }

            public void setVerifyCode(String verifyCode) {
                this.verifyCode = verifyCode;
            }
        }

        public static class PrincipalBean {
            /**
             * accountNonExpired : true
             * accountNonLocked : true
             * credentialsNonExpired : true
             * email : 1343411580@qq.com
             * enabled : true
             * idNumber : 362330195603082255
             * idType : 01
             * instCode : 0001
             * isFireSafePerson : false
             * name : 超级管理员
             * phoneNo : 15868476323
             * projectId :
             * status : 0
             * type : 1
             * userId : 1
             * username : admin
             */

            private String projectName;

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            private String instName;

            public String getInstName() {
                return instName;
            }

            public void setInstName(String instName) {
                this.instName = instName;
            }

            private boolean accountNonExpired;
            private boolean accountNonLocked;
            private boolean credentialsNonExpired;
            private String email;
            private boolean enabled;
            private String idNumber;
            private String idType;
            private String instCode;
            private boolean isFireSafePerson;
            private String name;
            private String phoneNo;
            private String projectId;
            private String status;
            private String type;
            private String userId;
            private String username;

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

            public boolean isCredentialsNonExpired() {
                return credentialsNonExpired;
            }

            public void setCredentialsNonExpired(boolean credentialsNonExpired) {
                this.credentialsNonExpired = credentialsNonExpired;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getIdNumber() {
                return idNumber;
            }

            public void setIdNumber(String idNumber) {
                this.idNumber = idNumber;
            }

            public String getIdType() {
                return idType;
            }

            public void setIdType(String idType) {
                this.idType = idType;
            }

            public String getInstCode() {
                return instCode;
            }

            public void setInstCode(String instCode) {
                this.instCode = instCode;
            }

            public boolean isIsFireSafePerson() {
                return isFireSafePerson;
            }

            public void setIsFireSafePerson(boolean isFireSafePerson) {
                this.isFireSafePerson = isFireSafePerson;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoneNo() {
                return phoneNo;
            }

            public void setPhoneNo(String phoneNo) {
                this.phoneNo = phoneNo;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class AuthoritiesBean {
            /**
             * authority : 1
             */

            private String authority;

            public String getAuthority() {
                return authority;
            }

            public void setAuthority(String authority) {
                this.authority = authority;
            }
        }
    }
}

