package com.wb.weibao.model.home;

import java.util.List;

/**
 * Created by shiqingqing on 2019/7/8.
 */

public class RecordListAppBean {


    /**
     * code : 200
     * message : SUCCESS
     * data : {"pageNum":1,"pageSize":4,"size":4,"orderBy":null,"startRow":0,"endRow":3,"total":4,"pages":1,"list":[{"reportLastTime":null,"createTime":1562304629000,"updateTime":1562304629000,"startTime":null,"endTime":null,"id":71,"projectId":"74","projectName":"test0612","contractName":"测试","contractPhone":"46789998","maintenanceDate":1562275803000,"maintenanceNextDate":1562275805000,"picturesOssKeys":"80266615-56C2-41DF-B5E9-4F7B31CDF8B3.png","userId":"3","maintenanceContent":"","coverProjectId":"65","coverProjectName":"hhh"},{"reportLastTime":null,"createTime":1562295396000,"updateTime":1562295396000,"startTime":null,"endTime":null,"id":70,"projectId":"74","projectName":"test0612","contractName":"测试","contractPhone":"3678877","maintenanceDate":1562266591000,"maintenanceNextDate":1562266593000,"picturesOssKeys":"","userId":"3","maintenanceContent":"","coverProjectId":"65","coverProjectName":"hhh"},{"reportLastTime":1561953659000,"createTime":1561953670000,"updateTime":1560929290000,"startTime":null,"endTime":null,"id":68,"projectId":"17","projectName":"11","contractName":"11","contractPhone":"22","maintenanceDate":1561953670000,"maintenanceNextDate":1560873600000,"picturesOssKeys":"AC97ABFA-61D1-48D2-94D4-43044B566992.jpg;3F279218-8484-4960-850A-24186420C975.jpg;9978D24A-05FB-4B9C-B2CA-4CF3FAFD4129.jpg","userId":"194","maintenanceContent":"We","coverProjectId":"59","coverProjectName":"2222222222"},{"reportLastTime":null,"createTime":1561625604000,"updateTime":1561360321000,"startTime":null,"endTime":null,"id":69,"projectId":"54","projectName":"429test","contractName":"66677","contractPhone":"123456789","maintenanceDate":1561625604000,"maintenanceNextDate":1561824000000,"picturesOssKeys":"692865E1-4B65-4A0E-89F5-591EA4E6C21F.jpg;F92AB940-F483-4496-A5E5-AA51AE8FC21C.png;18DB255C-CEE2-4596-B246-E7730E065B46.jpg","userId":"194","maintenanceContent":"1废物holy女子组形容哦诺呜呜呜哦哦许诺嘻嘻嘻诺舞女定我婆子说压迫刚温柔偶遇了我","coverProjectId":"62","coverProjectName":"liyan0507"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * pageSize : 4
         * size : 4
         * orderBy : null
         * startRow : 0
         * endRow : 3
         * total : 4
         * pages : 1
         * list : [{"reportLastTime":null,"createTime":1562304629000,"updateTime":1562304629000,"startTime":null,"endTime":null,"id":71,"projectId":"74","projectName":"test0612","contractName":"测试","contractPhone":"46789998","maintenanceDate":1562275803000,"maintenanceNextDate":1562275805000,"picturesOssKeys":"80266615-56C2-41DF-B5E9-4F7B31CDF8B3.png","userId":"3","maintenanceContent":"","coverProjectId":"65","coverProjectName":"hhh"},{"reportLastTime":null,"createTime":1562295396000,"updateTime":1562295396000,"startTime":null,"endTime":null,"id":70,"projectId":"74","projectName":"test0612","contractName":"测试","contractPhone":"3678877","maintenanceDate":1562266591000,"maintenanceNextDate":1562266593000,"picturesOssKeys":"","userId":"3","maintenanceContent":"","coverProjectId":"65","coverProjectName":"hhh"},{"reportLastTime":1561953659000,"createTime":1561953670000,"updateTime":1560929290000,"startTime":null,"endTime":null,"id":68,"projectId":"17","projectName":"11","contractName":"11","contractPhone":"22","maintenanceDate":1561953670000,"maintenanceNextDate":1560873600000,"picturesOssKeys":"AC97ABFA-61D1-48D2-94D4-43044B566992.jpg;3F279218-8484-4960-850A-24186420C975.jpg;9978D24A-05FB-4B9C-B2CA-4CF3FAFD4129.jpg","userId":"194","maintenanceContent":"We","coverProjectId":"59","coverProjectName":"2222222222"},{"reportLastTime":null,"createTime":1561625604000,"updateTime":1561360321000,"startTime":null,"endTime":null,"id":69,"projectId":"54","projectName":"429test","contractName":"66677","contractPhone":"123456789","maintenanceDate":1561625604000,"maintenanceNextDate":1561824000000,"picturesOssKeys":"692865E1-4B65-4A0E-89F5-591EA4E6C21F.jpg;F92AB940-F483-4496-A5E5-AA51AE8FC21C.png;18DB255C-CEE2-4596-B246-E7730E065B46.jpg","userId":"194","maintenanceContent":"1废物holy女子组形容哦诺呜呜呜哦哦许诺嘻嘻嘻诺舞女定我婆子说压迫刚温柔偶遇了我","coverProjectId":"62","coverProjectName":"liyan0507"}]
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
             * reportLastTime : null
             * createTime : 1562304629000
             * updateTime : 1562304629000
             * startTime : null
             * endTime : null
             * id : 71
             * projectId : 74
             * projectName : test0612
             * contractName : 测试
             * contractPhone : 46789998
             * maintenanceDate : 1562275803000
             * maintenanceNextDate : 1562275805000
             * picturesOssKeys : 80266615-56C2-41DF-B5E9-4F7B31CDF8B3.png
             * userId : 3
             * maintenanceContent :
             * coverProjectId : 65
             * coverProjectName : hhh
             */

            private Object reportLastTime;
            private long createTime;
            private long updateTime;
            private Object startTime;
            private Object endTime;
            private int id;
            private String projectId;
            private String projectName;
            private String contractName;
            private String contractPhone;
            private long maintenanceDate;
            private long maintenanceNextDate;
            private String picturesOssKeys;
            private String userId;
            private String maintenanceContent;
            private String coverProjectId;
            private String coverProjectName;

            public Object getReportLastTime() {
                return reportLastTime;
            }

            public void setReportLastTime(Object reportLastTime) {
                this.reportLastTime = reportLastTime;
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

            public String getContractName() {
                return contractName;
            }

            public void setContractName(String contractName) {
                this.contractName = contractName;
            }

            public String getContractPhone() {
                return contractPhone;
            }

            public void setContractPhone(String contractPhone) {
                this.contractPhone = contractPhone;
            }

            public long getMaintenanceDate() {
                return maintenanceDate;
            }

            public void setMaintenanceDate(long maintenanceDate) {
                this.maintenanceDate = maintenanceDate;
            }

            public long getMaintenanceNextDate() {
                return maintenanceNextDate;
            }

            public void setMaintenanceNextDate(long maintenanceNextDate) {
                this.maintenanceNextDate = maintenanceNextDate;
            }

            public String getPicturesOssKeys() {
                return picturesOssKeys;
            }

            public void setPicturesOssKeys(String picturesOssKeys) {
                this.picturesOssKeys = picturesOssKeys;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getMaintenanceContent() {
                return maintenanceContent;
            }

            public void setMaintenanceContent(String maintenanceContent) {
                this.maintenanceContent = maintenanceContent;
            }

            public String getCoverProjectId() {
                return coverProjectId;
            }

            public void setCoverProjectId(String coverProjectId) {
                this.coverProjectId = coverProjectId;
            }

            public String getCoverProjectName() {
                return coverProjectName;
            }

            public void setCoverProjectName(String coverProjectName) {
                this.coverProjectName = coverProjectName;
            }
        }
    }
}
