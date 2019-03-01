package com.wb.weibao.model.home;

import java.util.List;

public class StatisticsModel {

    /**
     * code : 200
     * message : SUCCESS
     * data : [{"sum":0,"date":1551024000000,"rate":0},{"sum":0,"date":1551110400000,"rate":0},{"sum":0,"date":1551196800000,"rate":0},{"sum":0,"date":1551283200000,"rate":0},{"sum":0,"date":1551369600000,"rate":0},{"sum":0,"date":1551456000000,"rate":0},{"sum":0,"date":1551542400000,"rate":0}]
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
         * sum : 0
         * date : 1551024000000
         * rate : 0.0
         */

        private int sum;
        private long date;
        private double rate;

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }
    }
}
