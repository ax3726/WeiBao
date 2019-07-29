package com.wb.weibao.model.home;

/**
 * Created by shiqingqing on 2019/7/8.
 */

public class MaintenanceRecordDetailBean {


    /**
     * code : 200
     * message : SUCCESS
     * data : {"reportLastTime":null,"createTime":1562304629000,"updateTime":1562304629000,"startTime":null,"endTime":null,"id":71,"projectId":"74","projectName":"test0612","contractName":"测试","contractPhone":"46789998","maintenanceDate":1562275803000,"maintenanceNextDate":1562275805000,"picturesOssKeys":"80266615-56C2-41DF-B5E9-4F7B31CDF8B3.png","userId":"3","maintenanceContent":"","coverProjectId":"65","coverProjectName":"hhh"}
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
