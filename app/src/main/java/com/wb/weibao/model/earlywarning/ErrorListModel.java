package com.wb.weibao.model.earlywarning;

import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class ErrorListModel {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"pageNum":1,"pageSize":15,"size":1,"orderBy":null,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"startTime":null,"endTime":null,"id":4,"instCode":"0001","instName":"卡檬","projectId":"3","flowNo":null,"projectName":"商城","projectPrincipalName":null,"projectPrincipalPhone":null,"projectArea":"文二路199号","projectAreaCode":null,"mechanismId":"1","status":"1","rdescribe":null,"ploop":"","ppoint":"","confirmTime":null,"warningTime":"2018-08-31 15:21:18","warningType":"","subWarningType":"","changeStatus":"","confirmUserId":null,"closeUserId":null,"closeTime":null,"type":"1","level":"2","equipmentId":"1","equipmentType":"2","equipmentDetails":null,"latitude":null,"longitude":null,"earlyNum":0,"earlyTime":1535700078000,"createUserId":"1","updateUserId":null,"createTime":1536032512000,"updateTime":null}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * pageNum : 1
         * pageSize : 15
         * size : 1
         * orderBy : null
         * startRow : 1
         * endRow : 1
         * total : 1
         * pages : 1
         * list : [{"startTime":null,"endTime":null,"id":4,"instCode":"0001","instName":"卡檬","projectId":"3","flowNo":null,"projectName":"商城","projectPrincipalName":null,"projectPrincipalPhone":null,"projectArea":"文二路199号","projectAreaCode":null,"mechanismId":"1","status":"1","rdescribe":null,"ploop":"","ppoint":"","confirmTime":null,"warningTime":"2018-08-31 15:21:18","warningType":"","subWarningType":"","changeStatus":"","confirmUserId":null,"closeUserId":null,"closeTime":null,"type":"1","level":"2","equipmentId":"1","equipmentType":"2","equipmentDetails":null,"latitude":null,"longitude":null,"earlyNum":0,"earlyTime":1535700078000,"createUserId":"1","updateUserId":null,"createTime":1536032512000,"updateTime":null}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
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
        private List<Integer> navigatepageNums;

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

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * startTime : null
             * endTime : null
             * id : 4
             * instCode : 0001
             * instName : 卡檬
             * projectId : 3
             * flowNo : null
             * projectName : 商城
             * projectPrincipalName : null
             * projectPrincipalPhone : null
             * projectArea : 文二路199号
             * projectAreaCode : null
             * mechanismId : 1
             * status : 1
             * rdescribe : null
             * ploop :
             * ppoint :
             * confirmTime : null
             * warningTime : 2018-08-31 15:21:18
             * warningType :
             * subWarningType :
             * changeStatus :
             * confirmUserId : null
             * closeUserId : null
             * closeTime : null
             * type : 1
             * level : 2
             * equipmentId : 1
             * equipmentType : 2
             * equipmentDetails : null
             * latitude : null
             * longitude : null
             * earlyNum : 0
             * earlyTime : 1535700078000
             * createUserId : 1
             * updateUserId : null
             * createTime : 1536032512000
             * updateTime : null
             */

            private Object startTime;
            private Object endTime;
            private int id;
            private String instCode;
            private String instName;
            private String projectId;
            private Object flowNo;
            private String projectName;
            private Object projectPrincipalName;
            private Object projectPrincipalPhone;
            private String projectArea;
            private Object projectAreaCode;
            private String mechanismId;
            private String status;
            private Object rdescribe;
            private String ploop;
            private String ppoint;
            private Object confirmTime;
            private String warningTime;
            private String warningType;
            private String subWarningType;
            private String changeStatus;
            private Object confirmUserId;
            private Object closeUserId;
            private Object closeTime;
            private String type;
            private String level;
            private String equipmentId;
            private String equipmentType;
            private Object equipmentDetails;
            private Object latitude;
            private Object longitude;
            private int earlyNum;
            private long earlyTime;
            private String createUserId;
            private Object updateUserId;
            private long createTime;
            private Object updateTime;

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

            public String getInstCode() {
                return instCode;
            }

            public void setInstCode(String instCode) {
                this.instCode = instCode;
            }

            public String getInstName() {
                return instName;
            }

            public void setInstName(String instName) {
                this.instName = instName;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public Object getFlowNo() {
                return flowNo;
            }

            public void setFlowNo(Object flowNo) {
                this.flowNo = flowNo;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public Object getProjectPrincipalName() {
                return projectPrincipalName;
            }

            public void setProjectPrincipalName(Object projectPrincipalName) {
                this.projectPrincipalName = projectPrincipalName;
            }

            public Object getProjectPrincipalPhone() {
                return projectPrincipalPhone;
            }

            public void setProjectPrincipalPhone(Object projectPrincipalPhone) {
                this.projectPrincipalPhone = projectPrincipalPhone;
            }

            public String getProjectArea() {
                return projectArea;
            }

            public void setProjectArea(String projectArea) {
                this.projectArea = projectArea;
            }

            public Object getProjectAreaCode() {
                return projectAreaCode;
            }

            public void setProjectAreaCode(Object projectAreaCode) {
                this.projectAreaCode = projectAreaCode;
            }

            public String getMechanismId() {
                return mechanismId;
            }

            public void setMechanismId(String mechanismId) {
                this.mechanismId = mechanismId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getRdescribe() {
                return rdescribe;
            }

            public void setRdescribe(Object rdescribe) {
                this.rdescribe = rdescribe;
            }

            public String getPloop() {
                return ploop;
            }

            public void setPloop(String ploop) {
                this.ploop = ploop;
            }

            public String getPpoint() {
                return ppoint;
            }

            public void setPpoint(String ppoint) {
                this.ppoint = ppoint;
            }

            public Object getConfirmTime() {
                return confirmTime;
            }

            public void setConfirmTime(Object confirmTime) {
                this.confirmTime = confirmTime;
            }

            public String getWarningTime() {
                return warningTime;
            }

            public void setWarningTime(String warningTime) {
                this.warningTime = warningTime;
            }

            public String getWarningType() {
                return warningType;
            }

            public void setWarningType(String warningType) {
                this.warningType = warningType;
            }

            public String getSubWarningType() {
                return subWarningType;
            }

            public void setSubWarningType(String subWarningType) {
                this.subWarningType = subWarningType;
            }

            public String getChangeStatus() {
                return changeStatus;
            }

            public void setChangeStatus(String changeStatus) {
                this.changeStatus = changeStatus;
            }

            public Object getConfirmUserId() {
                return confirmUserId;
            }

            public void setConfirmUserId(Object confirmUserId) {
                this.confirmUserId = confirmUserId;
            }

            public Object getCloseUserId() {
                return closeUserId;
            }

            public void setCloseUserId(Object closeUserId) {
                this.closeUserId = closeUserId;
            }

            public Object getCloseTime() {
                return closeTime;
            }

            public void setCloseTime(Object closeTime) {
                this.closeTime = closeTime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getEquipmentId() {
                return equipmentId;
            }

            public void setEquipmentId(String equipmentId) {
                this.equipmentId = equipmentId;
            }

            public String getEquipmentType() {
                return equipmentType;
            }

            public void setEquipmentType(String equipmentType) {
                this.equipmentType = equipmentType;
            }

            public Object getEquipmentDetails() {
                return equipmentDetails;
            }

            public void setEquipmentDetails(Object equipmentDetails) {
                this.equipmentDetails = equipmentDetails;
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

            public int getEarlyNum() {
                return earlyNum;
            }

            public void setEarlyNum(int earlyNum) {
                this.earlyNum = earlyNum;
            }

            public long getEarlyTime() {
                return earlyTime;
            }

            public void setEarlyTime(long earlyTime) {
                this.earlyTime = earlyTime;
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
        }
    }
}
