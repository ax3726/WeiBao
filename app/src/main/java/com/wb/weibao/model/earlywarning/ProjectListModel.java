package com.wb.weibao.model.earlywarning;

import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class ProjectListModel {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"pageNum":0,"pageSize":0,"size":4,"orderBy":null,"startRow":1,"endRow":4,"total":4,"pages":0,"list":[{"startTime":null,"endTime":null,"id":8,"name":"admin","type":"1","code":"11011","memo":"22","status":"1","num":2,"latitude":null,"longitude":null,"instCode":"0001","instId":"1","instName":"创源消防","principalName":"LILI","principalPhone":"13256547845","areaCityCode":"110100","area":"23","createUserId":"1","updateUserId":"1","createTime":1538038196000,"updateTime":null},{"startTime":null,"endTime":null,"id":7,"name":"项目9","type":"3","code":"11011","memo":"项目9地下积水","status":"1","num":1,"latitude":null,"longitude":null,"instCode":"0001","instId":"","instName":"机构1","principalName":"LILI","principalPhone":"13256547845","areaCityCode":"110101002","area":"武汉","createUserId":"1","updateUserId":"1","createTime":1536232205000,"updateTime":null},{"startTime":null,"endTime":null,"id":6,"name":"项目5","type":"3","code":"11","memo":"项目5备注","status":"1","num":15,"latitude":null,"longitude":null,"instCode":"0001","instId":"","instName":"机构1","principalName":"LILI","principalPhone":"13256547845","areaCityCode":"110101","area":"23","createUserId":"1","updateUserId":"","createTime":1536225079000,"updateTime":null},{"startTime":null,"endTime":null,"id":5,"name":"项目2","type":"2","code":"11011","memo":"项目2建筑面积100","status":"1","num":1,"latitude":null,"longitude":null,"instCode":"0001","instId":"14","instName":"技术","principalName":"吴丽","principalPhone":"13429105254","areaCityCode":"","area":"110100","createUserId":"1","updateUserId":"1","createTime":1536221809000,"updateTime":1538052144000}],"prePage":0,"nextPage":0,"isFirstPage":false,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[],"navigateFirstPage":0,"navigateLastPage":0,"firstPage":0,"lastPage":0}
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
         * size : 4
         * orderBy : null
         * startRow : 1
         * endRow : 4
         * total : 4
         * pages : 0
         * list : [{"startTime":null,"endTime":null,"id":8,"name":"admin","type":"1","code":"11011","memo":"22","status":"1","num":2,"latitude":null,"longitude":null,"instCode":"0001","instId":"1","instName":"创源消防","principalName":"LILI","principalPhone":"13256547845","areaCityCode":"110100","area":"23","createUserId":"1","updateUserId":"1","createTime":1538038196000,"updateTime":null},{"startTime":null,"endTime":null,"id":7,"name":"项目9","type":"3","code":"11011","memo":"项目9地下积水","status":"1","num":1,"latitude":null,"longitude":null,"instCode":"0001","instId":"","instName":"机构1","principalName":"LILI","principalPhone":"13256547845","areaCityCode":"110101002","area":"武汉","createUserId":"1","updateUserId":"1","createTime":1536232205000,"updateTime":null},{"startTime":null,"endTime":null,"id":6,"name":"项目5","type":"3","code":"11","memo":"项目5备注","status":"1","num":15,"latitude":null,"longitude":null,"instCode":"0001","instId":"","instName":"机构1","principalName":"LILI","principalPhone":"13256547845","areaCityCode":"110101","area":"23","createUserId":"1","updateUserId":"","createTime":1536225079000,"updateTime":null},{"startTime":null,"endTime":null,"id":5,"name":"项目2","type":"2","code":"11011","memo":"项目2建筑面积100","status":"1","num":1,"latitude":null,"longitude":null,"instCode":"0001","instId":"14","instName":"技术","principalName":"吴丽","principalPhone":"13429105254","areaCityCode":"","area":"110100","createUserId":"1","updateUserId":"1","createTime":1536221809000,"updateTime":1538052144000}]
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
             * startTime : null
             * endTime : null
             * id : 8
             * name : admin
             * type : 1
             * code : 11011
             * memo : 22
             * status : 1
             * num : 2
             * latitude : null
             * longitude : null
             * instCode : 0001
             * instId : 1
             * instName : 创源消防
             * principalName : LILI
             * principalPhone : 13256547845
             * areaCityCode : 110100
             * area : 23
             * createUserId : 1
             * updateUserId : 1
             * createTime : 1538038196000
             * updateTime : null
             */

            private Object startTime;
            private Object endTime;
            private int id;
            private String name;
            private String type;
            private String code;
            private String memo;
            private String status;
            private int num;
            private Object latitude;
            private Object longitude;
            private String instCode;
            private String instId;
            private String instName;
            private String principalName;
            private String principalPhone;
            private String areaCityCode;
            private String area;
            private String createUserId;
            private String updateUserId;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
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

            public String getInstCode() {
                return instCode;
            }

            public void setInstCode(String instCode) {
                this.instCode = instCode;
            }

            public String getInstId() {
                return instId;
            }

            public void setInstId(String instId) {
                this.instId = instId;
            }

            public String getInstName() {
                return instName;
            }

            public void setInstName(String instName) {
                this.instName = instName;
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

            public String getAreaCityCode() {
                return areaCityCode;
            }

            public void setAreaCityCode(String areaCityCode) {
                this.areaCityCode = areaCityCode;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
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

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
