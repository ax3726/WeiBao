package com.wb.weibao.model.home;

import java.util.List;

/**
 * Created by shiqingqing on 2019/7/29.
 */

public class SmartPatrolBean {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"pageNum":1,"pageSize":10,"size":10,"orderBy":null,"startRow":1,"endRow":10,"total":11,"pages":2,"list":[{"id":1,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":2,"patrolStartTime":1563958590000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":3,"patrolStartTime":1564044990000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":4,"patrolStartTime":1564131390000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":5,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":6,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":7,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":8,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":9,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":384,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj250","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2],"navigateFirstPage":1,"navigateLastPage":2,"firstPage":1,"lastPage":2}
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
         * pageSize : 10
         * size : 10
         * orderBy : null
         * startRow : 1
         * endRow : 10
         * total : 11
         * pages : 2
         * list : [{"id":1,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":2,"patrolStartTime":1563958590000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":3,"patrolStartTime":1564044990000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":4,"patrolStartTime":1564131390000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":5,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":6,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":7,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":8,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":9,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj520","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null},{"id":384,"patrolStartTime":1563872190000,"patrolEndTime":1563872190000,"userId":"189","userName":"cykj250","userPhone":"14927979","projectId":"62","projectName":"liyan0507","isPatrolEnd":false,"trajectoryOssKeys":null,"createUserId":"189","updateUserId":null,"createTime":1563872190000,"updateTime":1563872190000,"patrolRecordPoints":null}]
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2]
         * navigateFirstPage : 1
         * navigateLastPage : 2
         * firstPage : 1
         * lastPage : 2
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
             * id : 1
             * patrolStartTime : 1563872190000
             * patrolEndTime : 1563872190000
             * userId : 189
             * userName : cykj520
             * userPhone : 14927979
             * projectId : 62
             * projectName : liyan0507
             * isPatrolEnd : false
             * trajectoryOssKeys : null
             * createUserId : 189
             * updateUserId : null
             * createTime : 1563872190000
             * updateTime : 1563872190000
             * patrolRecordPoints : null
             */

            private int id;
            private long patrolStartTime;
            private long patrolEndTime;
            private String userId;
            private String userName;
            private String userPhone;
            private String projectId;
            private String projectName;
            private boolean isPatrolEnd;
            private Object trajectoryOssKeys;
            private String createUserId;
            private Object updateUserId;
            private long createTime;
            private long updateTime;
            private Object patrolRecordPoints;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getPatrolStartTime() {
                return patrolStartTime;
            }

            public void setPatrolStartTime(long patrolStartTime) {
                this.patrolStartTime = patrolStartTime;
            }

            public long getPatrolEndTime() {
                return patrolEndTime;
            }

            public void setPatrolEndTime(long patrolEndTime) {
                this.patrolEndTime = patrolEndTime;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public boolean isIsPatrolEnd() {
                return isPatrolEnd;
            }

            public void setIsPatrolEnd(boolean isPatrolEnd) {
                this.isPatrolEnd = isPatrolEnd;
            }

            public Object getTrajectoryOssKeys() {
                return trajectoryOssKeys;
            }

            public void setTrajectoryOssKeys(Object trajectoryOssKeys) {
                this.trajectoryOssKeys = trajectoryOssKeys;
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

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public Object getPatrolRecordPoints() {
                return patrolRecordPoints;
            }

            public void setPatrolRecordPoints(Object patrolRecordPoints) {
                this.patrolRecordPoints = patrolRecordPoints;
            }
        }
    }
}
