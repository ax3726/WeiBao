package com.wb.weibao.model.record;

/**
 * Created by shiqingqing on 2019/3/1.
 */

public class RecordCount {


    /**
     * code : string
     * data : {"alarmNum":0,"electricityFaultCountNum":0,"electricityFaultTbcNum":0,"electricityFaultTbpNum":0,"nineSmallPlacesCountNum":0,"nineSmallPlacesTbcNum":0,"nineSmallPlacesTbpNum":0,"remoteMonitoringCountNum":0,"remoteMonitoringTbcNum":0,"remoteMonitoringTbpNum":0,"tamperNum":0}
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
         * alarmNum : 0
         * electricityFaultCountNum : 0
         * electricityFaultTbcNum : 0
         * electricityFaultTbpNum : 0
         * nineSmallPlacesCountNum : 0
         * nineSmallPlacesTbcNum : 0
         * nineSmallPlacesTbpNum : 0
         * remoteMonitoringCountNum : 0
         * remoteMonitoringTbcNum : 0
         * remoteMonitoringTbpNum : 0
         * tamperNum : 0
         */

        private int alarmNum;
        private int electricityFaultCountNum;
        private int electricityFaultTbcNum;
        private int electricityFaultTbpNum;
        private int nineSmallPlacesCountNum;
        private int nineSmallPlacesTbcNum;
        private int nineSmallPlacesTbpNum;
        private int remoteMonitoringCountNum;
        private int remoteMonitoringTbcNum;
        private int remoteMonitoringTbpNum;
        private int tamperNum;
        //old
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

        public int getAlarmNum() {
            return alarmNum;
        }

        public void setAlarmNum(int alarmNum) {
            this.alarmNum = alarmNum;
        }

        public int getElectricityFaultCountNum() {
            return electricityFaultCountNum;
        }

        public void setElectricityFaultCountNum(int electricityFaultCountNum) {
            this.electricityFaultCountNum = electricityFaultCountNum;
        }

        public int getElectricityFaultTbcNum() {
            return electricityFaultTbcNum;
        }

        public void setElectricityFaultTbcNum(int electricityFaultTbcNum) {
            this.electricityFaultTbcNum = electricityFaultTbcNum;
        }

        public int getElectricityFaultTbpNum() {
            return electricityFaultTbpNum;
        }

        public void setElectricityFaultTbpNum(int electricityFaultTbpNum) {
            this.electricityFaultTbpNum = electricityFaultTbpNum;
        }

        public int getNineSmallPlacesCountNum() {
            return nineSmallPlacesCountNum;
        }

        public void setNineSmallPlacesCountNum(int nineSmallPlacesCountNum) {
            this.nineSmallPlacesCountNum = nineSmallPlacesCountNum;
        }

        public int getNineSmallPlacesTbcNum() {
            return nineSmallPlacesTbcNum;
        }

        public void setNineSmallPlacesTbcNum(int nineSmallPlacesTbcNum) {
            this.nineSmallPlacesTbcNum = nineSmallPlacesTbcNum;
        }

        public int getNineSmallPlacesTbpNum() {
            return nineSmallPlacesTbpNum;
        }

        public void setNineSmallPlacesTbpNum(int nineSmallPlacesTbpNum) {
            this.nineSmallPlacesTbpNum = nineSmallPlacesTbpNum;
        }

        public int getRemoteMonitoringCountNum() {
            return remoteMonitoringCountNum;
        }

        public void setRemoteMonitoringCountNum(int remoteMonitoringCountNum) {
            this.remoteMonitoringCountNum = remoteMonitoringCountNum;
        }

        public int getRemoteMonitoringTbcNum() {
            return remoteMonitoringTbcNum;
        }

        public void setRemoteMonitoringTbcNum(int remoteMonitoringTbcNum) {
            this.remoteMonitoringTbcNum = remoteMonitoringTbcNum;
        }

        public int getRemoteMonitoringTbpNum() {
            return remoteMonitoringTbpNum;
        }

        public void setRemoteMonitoringTbpNum(int remoteMonitoringTbpNum) {
            this.remoteMonitoringTbpNum = remoteMonitoringTbpNum;
        }

        public int getTamperNum() {
            return tamperNum;
        }

        public void setTamperNum(int tamperNum) {
            this.tamperNum = tamperNum;
        }
    }
}
