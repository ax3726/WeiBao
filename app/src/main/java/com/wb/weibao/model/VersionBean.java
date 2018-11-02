package com.wb.weibao.model;

/**
 * Created by Administrator on 2018/11/2.
 */

public class VersionBean {

    /**
     * code : 200
     * message : SUCCESS
     * data : {"id":1,"name":"当前版本","androidVersion":"1.0.0","androidUrl":"http://baidu.com","iosVersion":"1.0.0","iosUrl":"http://baidu.com"}
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
         * id : 1
         * name : 当前版本
         * androidVersion : 1.0.0
         * androidUrl : http://baidu.com
         * iosVersion : 1.0.0
         * iosUrl : http://baidu.com
         */

        private int id;
        private String name;
        private String androidVersion;
        private String androidUrl;
        private String iosVersion;
        private String iosUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAndroidVersion() {
            return androidVersion;
        }

        public void setAndroidVersion(String androidVersion) {
            this.androidVersion = androidVersion;
        }

        public String getAndroidUrl() {
            return androidUrl;
        }

        public void setAndroidUrl(String androidUrl) {
            this.androidUrl = androidUrl;
        }

        public String getIosVersion() {
            return iosVersion;
        }

        public void setIosVersion(String iosVersion) {
            this.iosVersion = iosVersion;
        }

        public String getIosUrl() {
            return iosUrl;
        }

        public void setIosUrl(String iosUrl) {
            this.iosUrl = iosUrl;
        }
    }
}
