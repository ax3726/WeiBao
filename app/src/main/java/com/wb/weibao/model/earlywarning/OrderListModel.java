package com.wb.weibao.model.earlywarning;

import java.util.List;

/**
 * Created by Administrator on 2018/10/18.
 */

public class OrderListModel {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"pageNum":1,"pageSize":15,"size":3,"orderBy":null,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"startTime":null,"endTime":null,"id":17,"orderNo":"201810181517115026332398","instCode":"0001","projectId":"1","userId":"1","principalName":"黎明","principalPhone":"15170193726","type":"1","amount":0,"endAmount":0,"status":"8","memo":"3.线路问题;5.排烟风机无法正常启动;","inventory":null,"returnMsg":null,"processingName":null,"processingRet":null,"processingTime":null,"createUserId":"1","updateUserId":null,"createTime":1539847032000,"updateTime":null},{"startTime":null,"endTime":null,"id":16,"orderNo":"201810172145263578854694","instCode":"0001","projectId":"1","userId":"1","principalName":"黎明","principalPhone":"15170193726","type":"1","amount":0,"endAmount":0,"status":"8","memo":"2.末端点位损坏;3.线路问题;6.水泵房内设备损坏","inventory":null,"returnMsg":null,"processingName":null,"processingRet":null,"processingTime":null,"createUserId":"1","updateUserId":null,"createTime":1539783927000,"updateTime":null},{"startTime":null,"endTime":null,"id":15,"orderNo":"201810172144245478512721","instCode":"0001","projectId":"1","userId":"1","principalName":"黎明","principalPhone":"15170193726","type":"1","amount":0,"endAmount":0,"status":"8","memo":"2.末端点位损坏;3.线路问题;6.水泵房内设备损坏","inventory":null,"returnMsg":null,"processingName":null,"processingRet":null,"processingTime":null,"createUserId":"1","updateUserId":null,"createTime":1539783865000,"updateTime":null}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"hasProcessing":false,"firstPage":1,"lastPage":1}
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
         * size : 3
         * orderBy : null
         * startRow : 1
         * endRow : 3
         * total : 3
         * pages : 1
         * list : [{"startTime":null,"endTime":null,"id":17,"orderNo":"201810181517115026332398","instCode":"0001","projectId":"1","userId":"1","principalName":"黎明","principalPhone":"15170193726","type":"1","amount":0,"endAmount":0,"status":"8","memo":"3.线路问题;5.排烟风机无法正常启动;","inventory":null,"returnMsg":null,"processingName":null,"processingRet":null,"processingTime":null,"createUserId":"1","updateUserId":null,"createTime":1539847032000,"updateTime":null},{"startTime":null,"endTime":null,"id":16,"orderNo":"201810172145263578854694","instCode":"0001","projectId":"1","userId":"1","principalName":"黎明","principalPhone":"15170193726","type":"1","amount":0,"endAmount":0,"status":"8","memo":"2.末端点位损坏;3.线路问题;6.水泵房内设备损坏","inventory":null,"returnMsg":null,"processingName":null,"processingRet":null,"processingTime":null,"createUserId":"1","updateUserId":null,"createTime":1539783927000,"updateTime":null},{"startTime":null,"endTime":null,"id":15,"orderNo":"201810172144245478512721","instCode":"0001","projectId":"1","userId":"1","principalName":"黎明","principalPhone":"15170193726","type":"1","amount":0,"endAmount":0,"status":"8","memo":"2.末端点位损坏;3.线路问题;6.水泵房内设备损坏","inventory":null,"returnMsg":null,"processingName":null,"processingRet":null,"processingTime":null,"createUserId":"1","updateUserId":null,"createTime":1539783865000,"updateTime":null}]
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
         * hasProcessing : false
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
        private boolean hasProcessing;
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

        public boolean isHasProcessing() {
            return hasProcessing;
        }

        public void setHasProcessing(boolean hasProcessing) {
            this.hasProcessing = hasProcessing;
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
             * id : 17
             * orderNo : 201810181517115026332398
             * instCode : 0001
             * projectId : 1
             * userId : 1
             * principalName : 黎明
             * principalPhone : 15170193726
             * type : 1
             * amount : 0.0
             * endAmount : 0.0
             * status : 8
             * memo : 3.线路问题;5.排烟风机无法正常启动;
             * inventory : null
             * returnMsg : null
             * processingName : null
             * processingRet : null
             * processingTime : null
             * createUserId : 1
             * updateUserId : null
             * createTime : 1539847032000
             * updateTime : null
             */

            private Object startTime;
            private Object endTime;
            private int id;
            private String orderNo;
            private String instCode;
            private String projectId;
            private String userId;
            private String principalName;
            private String principalPhone;
            private String type;
            private double amount;
            private double endAmount;
            private String status;
            private String memo;
            private Object inventory;
            private Object returnMsg;
            private Object processingName;
            private Object processingRet;
            private Object processingTime;
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

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getInstCode() {
                return instCode;
            }

            public void setInstCode(String instCode) {
                this.instCode = instCode;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getPrincipalName() {
                return principalName;
            }

            public void setPrincipalName(String principalName) {
                this.principalName = principalName;
            }

            public String getPrincipalPhone() {
                return principalPhone;
            }

            public void setPrincipalPhone(String principalPhone) {
                this.principalPhone = principalPhone;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public double getEndAmount() {
                return endAmount;
            }

            public void setEndAmount(double endAmount) {
                this.endAmount = endAmount;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public Object getInventory() {
                return inventory;
            }

            public void setInventory(Object inventory) {
                this.inventory = inventory;
            }

            public Object getReturnMsg() {
                return returnMsg;
            }

            public void setReturnMsg(Object returnMsg) {
                this.returnMsg = returnMsg;
            }

            public Object getProcessingName() {
                return processingName;
            }

            public void setProcessingName(Object processingName) {
                this.processingName = processingName;
            }

            public Object getProcessingRet() {
                return processingRet;
            }

            public void setProcessingRet(Object processingRet) {
                this.processingRet = processingRet;
            }

            public Object getProcessingTime() {
                return processingTime;
            }

            public void setProcessingTime(Object processingTime) {
                this.processingTime = processingTime;
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
