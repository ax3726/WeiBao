package com.wb.weibao.model.home;

import java.util.List;

public class SignListModel {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"pageNum":0,"pageSize":0,"size":12,"orderBy":null,"startRow":1,"endRow":12,"total":12,"pages":0,"list":[{"id":17,"name":"超级管理员","userId":"1","status":"1","signTime":1551086981000},{"id":16,"name":"超级管理员","userId":"1","status":"0","signTime":1551086976000},{"id":15,"name":"超级管理员","userId":"1","status":"1","signTime":1551086970000},{"id":14,"name":"超级管理员","userId":"1","status":"0","signTime":1551086968000},{"id":13,"name":"超级管理员","userId":"1","status":"1","signTime":1551003425000},{"id":12,"name":"超级管理员","userId":"1","status":"0","signTime":1551003422000},{"id":11,"name":"超级管理员","userId":"1","status":"1","signTime":1551003416000},{"id":10,"name":"超级管理员","userId":"1","status":"0","signTime":1551003262000},{"id":9,"name":"超级管理员","userId":"1","status":"1","signTime":1551003257000},{"id":8,"name":"超级管理员","userId":"1","status":"1","signTime":1551001314000},{"id":7,"name":"超级管理员","userId":"1","status":"1","signTime":1551000615000},{"id":6,"name":"超级管理员","userId":"1","status":"1","signTime":1551000605000}],"prePage":0,"nextPage":0,"isFirstPage":false,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[],"navigateFirstPage":0,"navigateLastPage":0,"lastPage":0,"firstPage":0}
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
         * size : 12
         * orderBy : null
         * startRow : 1
         * endRow : 12
         * total : 12
         * pages : 0
         * list : [{"id":17,"name":"超级管理员","userId":"1","status":"1","signTime":1551086981000},{"id":16,"name":"超级管理员","userId":"1","status":"0","signTime":1551086976000},{"id":15,"name":"超级管理员","userId":"1","status":"1","signTime":1551086970000},{"id":14,"name":"超级管理员","userId":"1","status":"0","signTime":1551086968000},{"id":13,"name":"超级管理员","userId":"1","status":"1","signTime":1551003425000},{"id":12,"name":"超级管理员","userId":"1","status":"0","signTime":1551003422000},{"id":11,"name":"超级管理员","userId":"1","status":"1","signTime":1551003416000},{"id":10,"name":"超级管理员","userId":"1","status":"0","signTime":1551003262000},{"id":9,"name":"超级管理员","userId":"1","status":"1","signTime":1551003257000},{"id":8,"name":"超级管理员","userId":"1","status":"1","signTime":1551001314000},{"id":7,"name":"超级管理员","userId":"1","status":"1","signTime":1551000615000},{"id":6,"name":"超级管理员","userId":"1","status":"1","signTime":1551000605000}]
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
         * lastPage : 0
         * firstPage : 0
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
        private int lastPage;
        private int firstPage;
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

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
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
             * id : 17
             * name : 超级管理员
             * userId : 1
             * status : 1
             * signTime : 1551086981000
             */

            private int id;
            private String name;
            private String userId;
            private String status;
            private long signTime;

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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public long getSignTime() {
                return signTime;
            }

            public void setSignTime(long signTime) {
                this.signTime = signTime;
            }
        }
    }
}
