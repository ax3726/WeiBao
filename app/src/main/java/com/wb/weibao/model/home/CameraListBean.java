package com.wb.weibao.model.home;

import java.util.List;

/**
 * Created by Administrator on 2019/3/21.
 */

public class CameraListBean {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"pageNum":0,"pageSize":0,"size":1,"orderBy":null,"startRow":1,"endRow":1,"total":1,"pages":0,"list":[{"id":38,"cameraName":"Camera 01","mac":null,"ipUrl":null,"type":"1","status":"1","distributionStatus":"1","warningStatus":"1","principalName":null,"principalPhone":null,"instCode":null,"instId":null,"instName":null,"projectId":null,"projectName":null,"latitude":null,"longitude":null,"createUserId":"1","updateUserId":null,"createTime":1552632233000,"updateTime":null,"userUuid":"admin","netzoneUuid":"domain0","cameraUuid":"2dbd1c8c784f4ccfaaf03c31f6b83fbc","host":"120.199.24.122","appKey":"22552062","secret":"xKppLPCG600fh76wqd2u"}],"prePage":0,"nextPage":0,"isFirstPage":false,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[],"navigateFirstPage":0,"navigateLastPage":0,"firstPage":0,"lastPage":0}
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
         * pageNum : 0
         * pageSize : 0
         * size : 1
         * orderBy : null
         * startRow : 1
         * endRow : 1
         * total : 1
         * pages : 0
         * list : [{"id":38,"cameraName":"Camera 01","mac":null,"ipUrl":null,"type":"1","status":"1","distributionStatus":"1","warningStatus":"1","principalName":null,"principalPhone":null,"instCode":null,"instId":null,"instName":null,"projectId":null,"projectName":null,"latitude":null,"longitude":null,"createUserId":"1","updateUserId":null,"createTime":1552632233000,"updateTime":null,"userUuid":"admin","netzoneUuid":"domain0","cameraUuid":"2dbd1c8c784f4ccfaaf03c31f6b83fbc","host":"120.199.24.122","appKey":"22552062","secret":"xKppLPCG600fh76wqd2u"}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : false
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : []
         * navigateFirstPage : 0
         * navigateLastPage : 0
         * firstPage : 0
         * lastPage : 0
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private Object orderBy;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<?> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<?> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<?> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 38
             * cameraName : Camera 01
             * mac : null
             * ipUrl : null
             * type : 1
             * status : 1
             * distributionStatus : 1
             * warningStatus : 1
             * principalName : null
             * principalPhone : null
             * instCode : null
             * instId : null
             * instName : null
             * projectId : null
             * projectName : null
             * latitude : null
             * longitude : null
             * createUserId : 1
             * updateUserId : null
             * createTime : 1552632233000
             * updateTime : null
             * userUuid : admin
             * netzoneUuid : domain0
             * cameraUuid : 2dbd1c8c784f4ccfaaf03c31f6b83fbc
             * host : 120.199.24.122
             * appKey : 22552062
             * secret : xKppLPCG600fh76wqd2u
             */

            private int id;
            private String cameraName;
            private Object mac;
            private Object ipUrl;
            private String type;
            private String status;
            private String distributionStatus;
            private String warningStatus;
            private Object principalName;
            private Object principalPhone;
            private Object instCode;
            private Object instId;
            private Object instName;
            private Object projectId;
            private Object projectName;
            private Object latitude;
            private Object longitude;
            private String createUserId;
            private Object updateUserId;
            private long createTime;
            private Object updateTime;
            private String userUuid;
            private String netzoneUuid;
            private String cameraUuid;
            private String host;
            private String appKey;
            private String secret;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCameraName() {
                return cameraName;
            }

            public void setCameraName(String cameraName) {
                this.cameraName = cameraName;
            }

            public Object getMac() {
                return mac;
            }

            public void setMac(Object mac) {
                this.mac = mac;
            }

            public Object getIpUrl() {
                return ipUrl;
            }

            public void setIpUrl(Object ipUrl) {
                this.ipUrl = ipUrl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDistributionStatus() {
                return distributionStatus;
            }

            public void setDistributionStatus(String distributionStatus) {
                this.distributionStatus = distributionStatus;
            }

            public String getWarningStatus() {
                return warningStatus;
            }

            public void setWarningStatus(String warningStatus) {
                this.warningStatus = warningStatus;
            }

            public Object getPrincipalName() {
                return principalName;
            }

            public void setPrincipalName(Object principalName) {
                this.principalName = principalName;
            }

            public Object getPrincipalPhone() {
                return principalPhone;
            }

            public void setPrincipalPhone(Object principalPhone) {
                this.principalPhone = principalPhone;
            }

            public Object getInstCode() {
                return instCode;
            }

            public void setInstCode(Object instCode) {
                this.instCode = instCode;
            }

            public Object getInstId() {
                return instId;
            }

            public void setInstId(Object instId) {
                this.instId = instId;
            }

            public Object getInstName() {
                return instName;
            }

            public void setInstName(Object instName) {
                this.instName = instName;
            }

            public Object getProjectId() {
                return projectId;
            }

            public void setProjectId(Object projectId) {
                this.projectId = projectId;
            }

            public Object getProjectName() {
                return projectName;
            }

            public void setProjectName(Object projectName) {
                this.projectName = projectName;
            }

            public Object getLatitude() {
                return latitude;
            }

            public void setLatitude(Object latitude) {
                this.latitude = latitude;
            }

            public Object getLongitude() {
                return longitude;
            }

            public void setLongitude(Object longitude) {
                this.longitude = longitude;
            }

            public String getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(String createUserId) {
                this.createUserId = createUserId;
            }

            public Object getUpdateUserId() {
                return updateUserId;
            }

            public void setUpdateUserId(Object updateUserId) {
                this.updateUserId = updateUserId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public String getUserUuid() {
                return userUuid;
            }

            public void setUserUuid(String userUuid) {
                this.userUuid = userUuid;
            }

            public String getNetzoneUuid() {
                return netzoneUuid;
            }

            public void setNetzoneUuid(String netzoneUuid) {
                this.netzoneUuid = netzoneUuid;
            }

            public String getCameraUuid() {
                return cameraUuid;
            }

            public void setCameraUuid(String cameraUuid) {
                this.cameraUuid = cameraUuid;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public String getAppKey() {
                return appKey;
            }

            public void setAppKey(String appKey) {
                this.appKey = appKey;
            }

            public String getSecret() {
                return secret;
            }

            public void setSecret(String secret) {
                this.secret = secret;
            }
        }
    }
}
