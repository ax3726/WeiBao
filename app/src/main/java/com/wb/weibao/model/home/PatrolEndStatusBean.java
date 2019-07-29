package com.wb.weibao.model.home;

/**
 * Created by shiqingqing on 2019/7/29.
 */

public class PatrolEndStatusBean {


    /**
     * code : string
     * data : {"isEnd":false,"patrolRecordId":"string"}
     * message : string
     */

    private String code;
    private DataBean data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * isEnd : false
         * patrolRecordId : string
         */

        private boolean isEnd;
        private String patrolRecordId;

        public boolean isIsEnd() {
            return isEnd;
        }

        public void setIsEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }

        public String getPatrolRecordId() {
            return patrolRecordId;
        }

        public void setPatrolRecordId(String patrolRecordId) {
            this.patrolRecordId = patrolRecordId;
        }
    }
}
