package com.wb.weibao.model.home;

import java.util.List;

/**
 * Created by Administrator on 2019/3/1.
 */

public class Handoverbean {


    /**
     * code : 200
     * message : SUCCESS
     * data : {"pageNum":0,"pageSize":0,"size":3,"orderBy":null,"startRow":1,"endRow":3,"total":3,"pages":0,"list":[{"id":15,"crossUserId":"1","crossUserName":"admin","connectUserId":"4","connectUserName":"test","handoverTime":1550713991000,"companyId":"00010002"},{"id":16,"crossUserId":"1","crossUserName":"admin","connectUserId":"100","connectUserName":"test1","handoverTime":1551419650000,"companyId":"00010002"},{"id":17,"crossUserId":"4","crossUserName":"test","connectUserId":"1","connectUserName":"admin","handoverTime":1551419791000,"companyId":"00010002"}],"prePage":0,"nextPage":0,"isFirstPage":false,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[],"navigateFirstPage":0,"navigateLastPage":0,"firstPage":0,"lastPage":0}
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
         * size : 3
         * orderBy : null
         * startRow : 1
         * endRow : 3
         * total : 3
         * pages : 0
         * list : [{"id":15,"crossUserId":"1","crossUserName":"admin","connectUserId":"4","connectUserName":"test","handoverTime":1550713991000,"companyId":"00010002"},{"id":16,"crossUserId":"1","crossUserName":"admin","connectUserId":"100","connectUserName":"test1","handoverTime":1551419650000,"companyId":"00010002"},{"id":17,"crossUserId":"4","crossUserName":"test","connectUserId":"1","connectUserName":"admin","handoverTime":1551419791000,"companyId":"00010002"}]
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
             * id : 15
             * crossUserId : 1
             * crossUserName : admin
             * connectUserId : 4
             * connectUserName : test
             * handoverTime : 1550713991000
             * companyId : 00010002
             */

            private int id;
            private String crossUserId;
            private String crossUserName;
            private String connectUserId;
            private String connectUserName;
            private long handoverTime;
            private String companyId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCrossUserId() {
                return crossUserId;
            }

            public void setCrossUserId(String crossUserId) {
                this.crossUserId = crossUserId;
            }

            public String getCrossUserName() {
                return crossUserName;
            }

            public void setCrossUserName(String crossUserName) {
                this.crossUserName = crossUserName;
            }

            public String getConnectUserId() {
                return connectUserId;
            }

            public void setConnectUserId(String connectUserId) {
                this.connectUserId = connectUserId;
            }

            public String getConnectUserName() {
                return connectUserName;
            }

            public void setConnectUserName(String connectUserName) {
                this.connectUserName = connectUserName;
            }

            public long getHandoverTime() {
                return handoverTime;
            }

            public void setHandoverTime(long handoverTime) {
                this.handoverTime = handoverTime;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }
        }
    }
}
