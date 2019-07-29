package com.wb.weibao.model.record;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class RecordListModel implements Serializable {


    /**
     * code : 200
     * message : SUCCESS
     * data : {"pageNum":1,"pageSize":2,"size":2,"orderBy":null,"startRow":1,"endRow":2,"total":10,"pages":5,"list":[{"startTime":null,"endTime":null,"id":229,"instCode":"00010001","instName":"","projectId":"1","flowNo":"2018111833601793","projectName":"宜家时代大厦","projectPrincipalName":"陈静","projectPrincipalPhone":"15868476323","projectArea":"滨江区1号大街000号","projectAreaCode":"3310","mechanismId":"10","status":"1","rdescribe":"火灾报警系统通用：5，3。２＃４层送风口   监管","ploop":"5","ppoint":"3","confirmTime":null,"warningTime":"2018-11-18 03:30:00","warningType":"3","subWarningType":"34","changeStatus":"2","confirmUserId":null,"closeUserId":null,"closeTime":null,"type":"1","level":"1","equipmentId":"803","equipmentType":"3","equipmentDetails":"宜家时代大厦111","latitude":"30.221700","longitude":"120.228200","earlyNum":0,"earlyTime":1542483091000,"earlyType":"3","createUserId":"system","updateUserId":null,"createTime":1542483091000,"updateTime":null},{"startTime":null,"endTime":null,"id":205,"instCode":"00010001","instName":"宜家时代大厦","projectId":"1","flowNo":"20181107291","projectName":"宜家时代大厦","projectPrincipalName":"陈静","projectPrincipalPhone":"15868476323","projectArea":"滨江区1号大街000号","projectAreaCode":"3310","mechanismId":"1","status":"1","rdescribe":"火灾报警系统通用：1，4。地下室       监管","ploop":"1","ppoint":"4","confirmTime":null,"warningTime":"2018-11-07 20:16:00","warningType":"3","subWarningType":"34","changeStatus":"2","confirmUserId":null,"closeUserId":null,"closeTime":null,"type":"1","level":"1","equipmentId":"4","equipmentType":"3","equipmentDetails":"宜家时代大厦111","latitude":"30.221700","longitude":"120.228200","earlyNum":0,"earlyTime":1541593044000,"earlyType":"3","createUserId":"system","updateUserId":null,"createTime":1541593044000,"updateTime":null}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5],"navigateFirstPage":1,"navigateLastPage":5,"firstPage":1,"lastPage":5}
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

    public static class DataBean implements Serializable{
        /**
         * pageNum : 1
         * pageSize : 2
         * size : 2
         * orderBy : null
         * startRow : 1
         * endRow : 2
         * total : 10
         * pages : 5
         * list : [{"startTime":null,"endTime":null,"id":229,"instCode":"00010001","instName":"","projectId":"1","flowNo":"2018111833601793","projectName":"宜家时代大厦","projectPrincipalName":"陈静","projectPrincipalPhone":"15868476323","projectArea":"滨江区1号大街000号","projectAreaCode":"3310","mechanismId":"10","status":"1","rdescribe":"火灾报警系统通用：5，3。２＃４层送风口   监管","ploop":"5","ppoint":"3","confirmTime":null,"warningTime":"2018-11-18 03:30:00","warningType":"3","subWarningType":"34","changeStatus":"2","confirmUserId":null,"closeUserId":null,"closeTime":null,"type":"1","level":"1","equipmentId":"803","equipmentType":"3","equipmentDetails":"宜家时代大厦111","latitude":"30.221700","longitude":"120.228200","earlyNum":0,"earlyTime":1542483091000,"earlyType":"3","createUserId":"system","updateUserId":null,"createTime":1542483091000,"updateTime":null},{"startTime":null,"endTime":null,"id":205,"instCode":"00010001","instName":"宜家时代大厦","projectId":"1","flowNo":"20181107291","projectName":"宜家时代大厦","projectPrincipalName":"陈静","projectPrincipalPhone":"15868476323","projectArea":"滨江区1号大街000号","projectAreaCode":"3310","mechanismId":"1","status":"1","rdescribe":"火灾报警系统通用：1，4。地下室       监管","ploop":"1","ppoint":"4","confirmTime":null,"warningTime":"2018-11-07 20:16:00","warningType":"3","subWarningType":"34","changeStatus":"2","confirmUserId":null,"closeUserId":null,"closeTime":null,"type":"1","level":"1","equipmentId":"4","equipmentType":"3","equipmentDetails":"宜家时代大厦111","latitude":"30.221700","longitude":"120.228200","earlyNum":0,"earlyTime":1541593044000,"earlyType":"3","createUserId":"system","updateUserId":null,"createTime":1541593044000,"updateTime":null}]
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5]
         * navigateFirstPage : 1
         * navigateLastPage : 5
         * firstPage : 1
         * lastPage : 5
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

        public static class ListBean implements Serializable{
            /**
             * startTime : null
             * endTime : null
             * id : 229
             * instCode : 00010001
             * instName :
             * projectId : 1
             * flowNo : 2018111833601793
             * projectName : 宜家时代大厦
             * projectPrincipalName : 陈静
             * projectPrincipalPhone : 15868476323
             * projectArea : 滨江区1号大街000号
             * projectAreaCode : 3310
             * mechanismId : 10
             * status : 1
             * rdescribe : 火灾报警系统通用：5，3。２＃４层送风口   监管
             * ploop : 5
             * ppoint : 3
             * confirmTime : null
             * warningTime : 2018-11-18 03:30:00
             * warningType : 3
             * subWarningType : 34
             * changeStatus : 2
             * confirmUserId : null
             * closeUserId : null
             * closeTime : null
             * type : 1
             * level : 1
             * equipmentId : 803
             * equipmentType : 3
             * equipmentDetails : 宜家时代大厦111
             * latitude : 30.221700
             * longitude : 120.228200
             * earlyNum : 0
             * earlyTime : 1542483091000
             * earlyType : 3
             * createUserId : system
             * updateUserId : null
             * createTime : 1542483091000
             * updateTime : null
             */

            private Object startTime;
            private Object endTime;
            private int id;
            private String instCode;
            private String instName;
            private String projectId;
            private String flowNo;
            private String projectName;
            private String projectPrincipalName;
            private String projectPrincipalPhone;
            private String projectArea;
            private String projectAreaCode;
            private String mechanismId;
            private String status;
            private String rdescribe;
            private String ploop;
            private String ppoint;
            private Object confirmTime;
            private String warningTime;
            private String warningType;
            private String subWarningTypeName;
            private String subWarningType;
            private String changeStatus;
            private Object confirmUserId;
            private Object closeUserId;
            private Object closeTime;
            private String type;
            private String level;
            private String equipmentId;
            private String equipmentType;
            private String equipmentDetails;
            private String equipmentName;
            private String latitude;
            private String longitude;
            private int earlyNum;
            private long earlyTime;
            private String earlyType;
            private String createUserId;
            private Object updateUserId;
            private long createTime;
            private Object updateTime;

            public String getSubWarningTypeName() {
                return subWarningTypeName;
            }

            public void setSubWarningTypeName(String subWarningTypeName) {
                this.subWarningTypeName = subWarningTypeName;
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

            public String getFlowNo() {
                return flowNo;
            }

            public void setFlowNo(String flowNo) {
                this.flowNo = flowNo;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getProjectPrincipalName() {
                return projectPrincipalName;
            }

            public void setProjectPrincipalName(String projectPrincipalName) {
                this.projectPrincipalName = projectPrincipalName;
            }

            public String getProjectPrincipalPhone() {
                return projectPrincipalPhone;
            }

            public void setProjectPrincipalPhone(String projectPrincipalPhone) {
                this.projectPrincipalPhone = projectPrincipalPhone;
            }

            public String getProjectArea() {
                return projectArea;
            }

            public void setProjectArea(String projectArea) {
                this.projectArea = projectArea;
            }

            public String getProjectAreaCode() {
                return projectAreaCode;
            }

            public void setProjectAreaCode(String projectAreaCode) {
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

            public String getRdescribe() {
                return rdescribe;
            }

            public void setRdescribe(String rdescribe) {
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

            public String getEquipmentDetails() {
                return equipmentDetails;
            }

            public void setEquipmentDetails(String equipmentDetails) {
                this.equipmentDetails = equipmentDetails;
            }

            public String getEquipmentName() {
                return equipmentName;
            }

            public void setEquipmentName(String equipmentName) {
                this.equipmentName = equipmentName;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
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

            public String getEarlyType() {
                return earlyType;
            }

            public void setEarlyType(String earlyType) {
                this.earlyType = earlyType;
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
