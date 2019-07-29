package com.wb.weibao.model.home;

/**
 * Created by shiqingqing on 2019/7/8.
 */

public class HomePageStatisticsBean {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"todayAlarmsNumber":0,"historyToBeConfirmed":90,"historyToBeProcessed":170}
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
         * todayAlarmsNumber : 0
         * historyToBeConfirmed : 90
         * historyToBeProcessed : 170
         */

        private int todayAlarmsNumber;
        private int historyToBeConfirmed;
        private int historyToBeProcessed;

        public int getTodayAlarmsNumber() {
            return todayAlarmsNumber;
        }

        public void setTodayAlarmsNumber(int todayAlarmsNumber) {
            this.todayAlarmsNumber = todayAlarmsNumber;
        }

        public int getHistoryToBeConfirmed() {
            return historyToBeConfirmed;
        }

        public void setHistoryToBeConfirmed(int historyToBeConfirmed) {
            this.historyToBeConfirmed = historyToBeConfirmed;
        }

        public int getHistoryToBeProcessed() {
            return historyToBeProcessed;
        }

        public void setHistoryToBeProcessed(int historyToBeProcessed) {
            this.historyToBeProcessed = historyToBeProcessed;
        }
    }
}
