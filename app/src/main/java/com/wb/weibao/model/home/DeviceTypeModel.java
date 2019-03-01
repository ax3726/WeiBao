package com.wb.weibao.model.home;

import java.util.List;

public class DeviceTypeModel {

    /**
     * code : 200
     * message : SUCCESS
     * data : [{"id":311,"name":"采集器","code":"equipmentType","codeValue":"collector","defaults":null,"parentId":null,"parentName":null,"level":null,"status":0,"sort":1,"createUserId":"1","updateUserId":null,"createTime":1550462812000,"updateTime":null},{"id":312,"name":"主机","code":"equipmentType","codeValue":"host","defaults":null,"parentId":null,"parentName":null,"level":null,"status":0,"sort":2,"createUserId":"1","updateUserId":null,"createTime":1550462812000,"updateTime":null},{"id":313,"name":"感温探测器","code":"equipmentType","codeValue":"temperatureSensor","defaults":null,"parentId":null,"parentName":null,"level":null,"status":0,"sort":3,"createUserId":"1","updateUserId":null,"createTime":1550462812000,"updateTime":null}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 311
         * name : 采集器
         * code : equipmentType
         * codeValue : collector
         * defaults : null
         * parentId : null
         * parentName : null
         * level : null
         * status : 0
         * sort : 1
         * createUserId : 1
         * updateUserId : null
         * createTime : 1550462812000
         * updateTime : null
         */

        private int id;
        private String name;
        private String code;
        private String codeValue;
        private Object defaults;
        private Object parentId;
        private Object parentName;
        private Object level;
        private int status;
        private int sort;
        private String createUserId;
        private Object updateUserId;
        private long createTime;
        private Object updateTime;

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCodeValue() {
            return codeValue;
        }

        public void setCodeValue(String codeValue) {
            this.codeValue = codeValue;
        }

        public Object getDefaults() {
            return defaults;
        }

        public void setDefaults(Object defaults) {
            this.defaults = defaults;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public Object getParentName() {
            return parentName;
        }

        public void setParentName(Object parentName) {
            this.parentName = parentName;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
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
