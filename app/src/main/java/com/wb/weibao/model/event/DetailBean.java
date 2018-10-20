package com.wb.weibao.model.event;

/**
 * Created by LiMing on 2018/10/19.
 */

public class DetailBean {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"startTime":null,"endTime":null,"id":28,"orderNo":"201810191407417027921942","instCode":"0001","projectId":"1","userId":"1","principalName":"测试","principalPhone":"15858288241","type":"1","amount":0,"endAmount":0,"status":"1","memo":"6.水泵房内设备损坏;8.消防水箱损坏;","inventory":null,"returnMsg":null,"processingName":null,"processingRet":null,"processingTime":null,"createUserId":"1","updateUserId":null,"createTime":1539929261000,"updateTime":null}
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
         * id : 28
         * orderNo : 201810191407417027921942
         * instCode : 0001
         * projectId : 1
         * userId : 1
         * principalName : 测试
         * principalPhone : 15858288241
         * type : 1
         * amount : 0.0
         * endAmount : 0.0
         * status : 1
         * memo : 6.水泵房内设备损坏;8.消防水箱损坏;
         * inventory : null
         * returnMsg : null
         * processingName : null
         * processingRet : null
         * processingTime : null
         * createUserId : 1
         * updateUserId : null
         * createTime : 1539929261000
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
