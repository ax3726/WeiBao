package com.wb.weibao.model.record;

/**
 * Created by shiqingqing on 2019/3/1.
 */

public class RecordCount {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"alarmWaitProccessNum":79,"fireWaitProccessNum":21,"fireWaitConfirmNum":3}
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
         * alarmWaitProccessNum : 79
         * fireWaitProccessNum : 21
         * fireWaitConfirmNum : 3
         */

        private int alarmWaitProccessNum;
        private int fireWaitProccessNum;
        private int fireWaitConfirmNum;

        public int getAlarmWaitProccessNum() {
            return alarmWaitProccessNum;
        }

        public void setAlarmWaitProccessNum(int alarmWaitProccessNum) {
            this.alarmWaitProccessNum = alarmWaitProccessNum;
        }

        public int getFireWaitProccessNum() {
            return fireWaitProccessNum;
        }

        public void setFireWaitProccessNum(int fireWaitProccessNum) {
            this.fireWaitProccessNum = fireWaitProccessNum;
        }

        public int getFireWaitConfirmNum() {
            return fireWaitConfirmNum;
        }

        public void setFireWaitConfirmNum(int fireWaitConfirmNum) {
            this.fireWaitConfirmNum = fireWaitConfirmNum;
        }
    }
}
