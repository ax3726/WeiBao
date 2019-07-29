package com.wb.weibao.model.record;

/**
 * Created by shiqingqing on 2019/3/1.
 */

public class RecordCount {


    /**
     * code : 200
     * message : SUCCESS
     * data : {"remoteMonitoringTbcNum":1,"remoteMonitoringTbpNum":91,"remoteMonitoringCountNum":92,"nineSmallPlacesTbcNum":57,"nineSmallPlacesTbpNum":81,"nineSmallPlacesCountNum":138,"electricityFaultTbcNum":12,"electricityFaultTbpNum":3,"electricityFaultCountNum":15,"waterFaultTbcNum":0,"waterFaultTbpNum":0,"waterFaultCountNum":0,"alarmNum":19,"tamperNum":41}
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
         * remoteMonitoringTbcNum : 1
         * remoteMonitoringTbpNum : 91
         * remoteMonitoringCountNum : 92
         * nineSmallPlacesTbcNum : 57
         * nineSmallPlacesTbpNum : 81
         * nineSmallPlacesCountNum : 138
         * electricityFaultTbcNum : 12
         * electricityFaultTbpNum : 3
         * electricityFaultCountNum : 15
         * waterFaultTbcNum : 0
         * waterFaultTbpNum : 0
         * waterFaultCountNum : 0
         * alarmNum : 19
         * tamperNum : 41
         */

        private int remoteMonitoringTbcNum;
        private int remoteMonitoringTbpNum;
        private int remoteMonitoringCountNum;
        private int nineSmallPlacesTbcNum;
        private int nineSmallPlacesTbpNum;
        private int nineSmallPlacesCountNum;
        private int electricityFaultTbcNum;
        private int electricityFaultTbpNum;
        private int electricityFaultCountNum;
        private int waterFaultTbcNum;
        private int waterFaultTbpNum;
        private int waterFaultCountNum;
        private int alarmNum;
        private int tamperNum;

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

        public int getRemoteMonitoringCountNum() {
            return remoteMonitoringCountNum;
        }

        public void setRemoteMonitoringCountNum(int remoteMonitoringCountNum) {
            this.remoteMonitoringCountNum = remoteMonitoringCountNum;
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

        public int getNineSmallPlacesCountNum() {
            return nineSmallPlacesCountNum;
        }

        public void setNineSmallPlacesCountNum(int nineSmallPlacesCountNum) {
            this.nineSmallPlacesCountNum = nineSmallPlacesCountNum;
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

        public int getElectricityFaultCountNum() {
            return electricityFaultCountNum;
        }

        public void setElectricityFaultCountNum(int electricityFaultCountNum) {
            this.electricityFaultCountNum = electricityFaultCountNum;
        }

        public int getWaterFaultTbcNum() {
            return waterFaultTbcNum;
        }

        public void setWaterFaultTbcNum(int waterFaultTbcNum) {
            this.waterFaultTbcNum = waterFaultTbcNum;
        }

        public int getWaterFaultTbpNum() {
            return waterFaultTbpNum;
        }

        public void setWaterFaultTbpNum(int waterFaultTbpNum) {
            this.waterFaultTbpNum = waterFaultTbpNum;
        }

        public int getWaterFaultCountNum() {
            return waterFaultCountNum;
        }

        public void setWaterFaultCountNum(int waterFaultCountNum) {
            this.waterFaultCountNum = waterFaultCountNum;
        }

        public int getAlarmNum() {
            return alarmNum;
        }

        public void setAlarmNum(int alarmNum) {
            this.alarmNum = alarmNum;
        }

        public int getTamperNum() {
            return tamperNum;
        }

        public void setTamperNum(int tamperNum) {
            this.tamperNum = tamperNum;
        }
    }
}
