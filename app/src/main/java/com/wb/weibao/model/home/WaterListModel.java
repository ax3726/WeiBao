package com.wb.weibao.model.home;

import java.util.List;

public class WaterListModel {

    /**
     * code : string
     * data : [{"code":"string","id":0,"lotCard":"string","name":"string","position":"string","realtimeData":0,"realtimeDataUnit":"string","typeName":"string","updateTime":"2019-08-31T13:34:07.726Z"}]
     * message : string
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
         * code : string
         * id : 0
         * lotCard : string
         * name : string
         * position : string
         * realtimeData : 0
         * realtimeDataUnit : string
         * typeName : string
         * updateTime : 2019-08-31T13:34:07.726Z
         */

        private String code;
        private int id;
        private String lotCard;
        private String name;
        private String position;
        private float realtimeData;
        private String realtimeDataUnit;
        private String typeName;
        private String updateTime;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public float getRealtimeData() {
            return realtimeData;
        }

        public void setRealtimeData(float realtimeData) {
            this.realtimeData = realtimeData;
        }

        public String getRealtimeDataUnit() {
            return realtimeDataUnit;
        }

        public void setRealtimeDataUnit(String realtimeDataUnit) {
            this.realtimeDataUnit = realtimeDataUnit;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
