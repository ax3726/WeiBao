package com.wb.weibao.model.home;

public class SecurityInfoModel {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"startTime":null,"endTime":null,"id":47,"orderNo":"201903011401357851303639","instCode":"00010013","projectId":"1","userId":"108","principalName":"李彦","principalPhone":"15656565665","type":"2","amount":0,"endAmount":0,"status":"1","subStatus":null,"memo":"在家里面\n","inventory":null,"returnMsg":null,"processingName":null,"processingRet":null,"processingTime":null,"createUserId":"108","updateUserId":null,"createTime":1551420095000,"updateTime":null,"faultType":"2","equipmentType":"temperatureSensor","picturesOssKeys":"1BE0532D-4CA0-4FD7-8504-F384A0F37CAE.png;9E6FE7BB-5358-40F5-AF21-AFDB76CA528C.png;","processingOssKeys":null,"equipmentTypeName":"感温探测器","faultTypeName":"监管"}
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
         * startTime : null
         * endTime : null
         * id : 47
         * orderNo : 201903011401357851303639
         * instCode : 00010013
         * projectId : 1
         * userId : 108
         * principalName : 李彦
         * principalPhone : 15656565665
         * type : 2
         * amount : 0.0
         * endAmount : 0.0
         * status : 1
         * subStatus : null
         * memo : 在家里面
         * inventory : null
         * returnMsg : null
         * processingName : null
         * processingRet : null
         * processingTime : null
         * createUserId : 108
         * updateUserId : null
         * createTime : 1551420095000
         * updateTime : null
         * faultType : 2
         * equipmentType : temperatureSensor
         * picturesOssKeys : 1BE0532D-4CA0-4FD7-8504-F384A0F37CAE.png;9E6FE7BB-5358-40F5-AF21-AFDB76CA528C.png;
         * processingOssKeys : null
         * equipmentTypeName : 感温探测器
         * faultTypeName : 监管
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
        private Object subStatus;
        private String memo;
        private Object inventory;
        private Object returnMsg;
        private Object processingName;
        private Object processingRet;
        private long processingTime;
        private String createUserId;
        private Object updateUserId;
        private long createTime;
        private Object updateTime;
        private String faultType;
        private String equipmentType;
        private String picturesOssKeys;
        private Object processingOssKeys;
        private String equipmentTypeName;
        private String faultTypeName;

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

        public Object getSubStatus() {
            return subStatus;
        }

        public void setSubStatus(Object subStatus) {
            this.subStatus = subStatus;
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

        public long getProcessingTime() {
            return processingTime;
        }

        public void setProcessingTime(long processingTime) {
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

        public String getFaultType() {
            return faultType;
        }

        public void setFaultType(String faultType) {
            this.faultType = faultType;
        }

        public String getEquipmentType() {
            return equipmentType;
        }

        public void setEquipmentType(String equipmentType) {
            this.equipmentType = equipmentType;
        }

        public String getPicturesOssKeys() {
            return picturesOssKeys;
        }

        public void setPicturesOssKeys(String picturesOssKeys) {
            this.picturesOssKeys = picturesOssKeys;
        }

        public Object getProcessingOssKeys() {
            return processingOssKeys;
        }

        public void setProcessingOssKeys(Object processingOssKeys) {
            this.processingOssKeys = processingOssKeys;
        }

        public String getEquipmentTypeName() {
            return equipmentTypeName;
        }

        public void setEquipmentTypeName(String equipmentTypeName) {
            this.equipmentTypeName = equipmentTypeName;
        }

        public String getFaultTypeName() {
            return faultTypeName;
        }

        public void setFaultTypeName(String faultTypeName) {
            this.faultTypeName = faultTypeName;
        }
    }
}
