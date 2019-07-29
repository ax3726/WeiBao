package com.wb.weibao.model.home;

import java.util.List;

/**
 * Created by shiqingqing on 2019/7/29.
 */

public class SmartElectorDetailBean {

    /**
     * code : string
     * data : {"code":"string","collectorPoints":[{"alarmThreshold":"string","alarmThresholdDate":"2019-07-29T07:27:20.875Z","collectorId":"string","createTime":"2019-07-29T07:27:20.875Z","endTime":"string","equipmentName":"string","equipmentNo":"string","equipmentTypeName":"string","host":"string","id":0,"installationDate":"2019-07-29T07:27:20.875Z","instructStatus":"string","isFireEquipment":"string","isOnline":"string","latitude":"string","longitude":"string","measuredData":"string","memo":"string","ploop":"string","ppoint":"string","produceBrand":"string","reportLastTime":"2019-07-29T07:27:20.875Z","reportNum":0,"sendStatus":"string","startTime":"string","status":"string","type":"string","updateTime":"2019-07-29T07:27:20.875Z"}],"id":"string","lotCard":"string","name":"string","position":"string","statusName":"string","updateTime":"2019-07-29T07:27:20.875Z"}
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
         * code : string
         * collectorPoints : [{"alarmThreshold":"string","alarmThresholdDate":"2019-07-29T07:27:20.875Z","collectorId":"string","createTime":"2019-07-29T07:27:20.875Z","endTime":"string","equipmentName":"string","equipmentNo":"string","equipmentTypeName":"string","host":"string","id":0,"installationDate":"2019-07-29T07:27:20.875Z","instructStatus":"string","isFireEquipment":"string","isOnline":"string","latitude":"string","longitude":"string","measuredData":"string","memo":"string","ploop":"string","ppoint":"string","produceBrand":"string","reportLastTime":"2019-07-29T07:27:20.875Z","reportNum":0,"sendStatus":"string","startTime":"string","status":"string","type":"string","updateTime":"2019-07-29T07:27:20.875Z"}]
         * id : string
         * lotCard : string
         * name : string
         * position : string
         * statusName : string
         * updateTime : 2019-07-29T07:27:20.875Z
         */

        private String code;
        private String id;
        private String lotCard;
        private String name;
        private String position;
        private String statusName;
        private long updateTime;
        private List<CollectorPointsBean> collectorPoints;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLotCard() {
            return lotCard;
        }

        public void setLotCard(String lotCard) {
            this.lotCard = lotCard;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public List<CollectorPointsBean> getCollectorPoints() {
            return collectorPoints;
        }

        public void setCollectorPoints(List<CollectorPointsBean> collectorPoints) {
            this.collectorPoints = collectorPoints;
        }

        public static class CollectorPointsBean {
            /**
             * alarmThreshold : string
             * alarmThresholdDate : 2019-07-29T07:27:20.875Z
             * collectorId : string
             * createTime : 2019-07-29T07:27:20.875Z
             * endTime : string
             * equipmentName : string
             * equipmentNo : string
             * equipmentTypeName : string
             * host : string
             * id : 0
             * installationDate : 2019-07-29T07:27:20.875Z
             * instructStatus : string
             * isFireEquipment : string
             * isOnline : string
             * latitude : string
             * longitude : string
             * measuredData : string
             * memo : string
             * ploop : string
             * ppoint : string
             * produceBrand : string
             * reportLastTime : 2019-07-29T07:27:20.875Z
             * reportNum : 0
             * sendStatus : string
             * startTime : string
             * status : string
             * type : string
             * updateTime : 2019-07-29T07:27:20.875Z
             */

            private String alarmThreshold;
            private String alarmThresholdDate;
            private String collectorId;
            private String createTime;
            private String endTime;
            private String equipmentName;
            private String equipmentNo;
            private String equipmentTypeName;
            private String host;
            private int id;
            private String installationDate;
            private String instructStatus;
            private String isFireEquipment;
            private String isOnline;
            private String latitude;
            private String longitude;
            private String measuredData;
            private String memo;
            private String ploop;
            private String ppoint;
            private String produceBrand;
            private String reportLastTime;
            private int reportNum;
            private String sendStatus;
            private String startTime;
            private String status;
            private String type;
            private String updateTime;

            public String getAlarmThreshold() {
                return alarmThreshold;
            }

            public void setAlarmThreshold(String alarmThreshold) {
                this.alarmThreshold = alarmThreshold;
            }

            public String getAlarmThresholdDate() {
                return alarmThresholdDate;
            }

            public void setAlarmThresholdDate(String alarmThresholdDate) {
                this.alarmThresholdDate = alarmThresholdDate;
            }

            public String getCollectorId() {
                return collectorId;
            }

            public void setCollectorId(String collectorId) {
                this.collectorId = collectorId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getEquipmentName() {
                return equipmentName;
            }

            public void setEquipmentName(String equipmentName) {
                this.equipmentName = equipmentName;
            }

            public String getEquipmentNo() {
                return equipmentNo;
            }

            public void setEquipmentNo(String equipmentNo) {
                this.equipmentNo = equipmentNo;
            }

            public String getEquipmentTypeName() {
                return equipmentTypeName;
            }

            public void setEquipmentTypeName(String equipmentTypeName) {
                this.equipmentTypeName = equipmentTypeName;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getInstallationDate() {
                return installationDate;
            }

            public void setInstallationDate(String installationDate) {
                this.installationDate = installationDate;
            }

            public String getInstructStatus() {
                return instructStatus;
            }

            public void setInstructStatus(String instructStatus) {
                this.instructStatus = instructStatus;
            }

            public String getIsFireEquipment() {
                return isFireEquipment;
            }

            public void setIsFireEquipment(String isFireEquipment) {
                this.isFireEquipment = isFireEquipment;
            }

            public String getIsOnline() {
                return isOnline;
            }

            public void setIsOnline(String isOnline) {
                this.isOnline = isOnline;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getMeasuredData() {
                return measuredData;
            }

            public void setMeasuredData(String measuredData) {
                this.measuredData = measuredData;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getPloop() {
                return ploop;
            }

            public void setPloop(String ploop) {
                this.ploop = ploop;
            }

            public String getPpoint() {
                return ppoint;
            }

            public void setPpoint(String ppoint) {
                this.ppoint = ppoint;
            }

            public String getProduceBrand() {
                return produceBrand;
            }

            public void setProduceBrand(String produceBrand) {
                this.produceBrand = produceBrand;
            }

            public String getReportLastTime() {
                return reportLastTime;
            }

            public void setReportLastTime(String reportLastTime) {
                this.reportLastTime = reportLastTime;
            }

            public int getReportNum() {
                return reportNum;
            }

            public void setReportNum(int reportNum) {
                this.reportNum = reportNum;
            }

            public String getSendStatus() {
                return sendStatus;
            }

            public void setSendStatus(String sendStatus) {
                this.sendStatus = sendStatus;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
