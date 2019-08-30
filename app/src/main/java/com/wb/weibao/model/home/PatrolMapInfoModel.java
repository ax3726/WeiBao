package com.wb.weibao.model.home;

import java.util.List;

/**
 * @author ：LiMing
 * @date ：2019-07-30
 * @desc ：
 */
public class PatrolMapInfoModel {

    /**
     * code : string
     * data : {"createTime":"2019-07-30T07:10:29.600Z","createUserId":"string","id":0,"isPatrolEnd":false,"patrolEndTime":"2019-07-30T07:10:29.600Z","patrolRecordPoints":[{"createTime":"2019-07-30T07:10:29.600Z","createUserId":"string","id":0,"latitude":"string","longitude":"string","patrolAddress":"string","patrolName":"string","patrolRecordId":"string","patrolType":"string","picturesOssKeys":"string","projectId":"string","projectName":"string","remark":"string","updateTime":"2019-07-30T07:10:29.600Z","updateUserId":"string"}],"patrolStartTime":"2019-07-30T07:10:29.600Z","projectId":"string","projectName":"string","trajectoryOssKeys":"string","updateTime":"2019-07-30T07:10:29.600Z","updateUserId":"string","userId":"string","userName":"string","userPhone":"string"}
     * message : string
     */

    private String code;
    private DataBean data;
    private String   message;

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
         * createTime : 2019-07-30T07:10:29.600Z
         * createUserId : string
         * id : 0
         * isPatrolEnd : false
         * patrolEndTime : 2019-07-30T07:10:29.600Z
         * patrolRecordPoints : [{"createTime":"2019-07-30T07:10:29.600Z","createUserId":"string","id":0,"latitude":"string","longitude":"string","patrolAddress":"string","patrolName":"string","patrolRecordId":"string","patrolType":"string","picturesOssKeys":"string","projectId":"string","projectName":"string","remark":"string","updateTime":"2019-07-30T07:10:29.600Z","updateUserId":"string"}]
         * patrolStartTime : 2019-07-30T07:10:29.600Z
         * projectId : string
         * projectName : string
         * trajectoryOssKeys : string
         * updateTime : 2019-07-30T07:10:29.600Z
         * updateUserId : string
         * userId : string
         * userName : string
         * userPhone : string
         */

        private String createTime;
        private String                       createUserId;
        private int                          id;
        private boolean                      isPatrolEnd;
        private long                       patrolEndTime;
        private long                       patrolStartTime;
        private String                       projectId;
        private String                       projectName;
        private String                       trajectoryOssKeys;
        private String                       updateTime;
        private String                       updateUserId;
        private String                       userId;
        private String                       userName;
        private String                       userPhone;
        private List<PatrolRecordPointsBean> patrolRecordPoints;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsPatrolEnd() {
            return isPatrolEnd;
        }

        public void setIsPatrolEnd(boolean isPatrolEnd) {
            this.isPatrolEnd = isPatrolEnd;
        }

        public long getPatrolEndTime() {
            return patrolEndTime;
        }

        public void setPatrolEndTime(long patrolEndTime) {
            this.patrolEndTime = patrolEndTime;
        }

        public long getPatrolStartTime() {
            return patrolStartTime;
        }

        public void setPatrolStartTime(long patrolStartTime) {
            this.patrolStartTime = patrolStartTime;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getTrajectoryOssKeys() {
            return trajectoryOssKeys;
        }

        public void setTrajectoryOssKeys(String trajectoryOssKeys) {
            this.trajectoryOssKeys = trajectoryOssKeys;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public List<PatrolRecordPointsBean> getPatrolRecordPoints() {
            return patrolRecordPoints;
        }

        public void setPatrolRecordPoints(List<PatrolRecordPointsBean> patrolRecordPoints) {
            this.patrolRecordPoints = patrolRecordPoints;
        }

        public static class PatrolRecordPointsBean {
            /**
             * createTime : 2019-07-30T07:10:29.600Z
             * createUserId : string
             * id : 0
             * latitude : string
             * longitude : string
             * patrolAddress : string
             * patrolName : string
             * patrolRecordId : string
             * patrolType : string
             * picturesOssKeys : string
             * projectId : string
             * projectName : string
             * remark : string
             * updateTime : 2019-07-30T07:10:29.600Z
             * updateUserId : string
             */
            private String completeType;

            public String getCompleteType() {
                return completeType;
            }

            public void setCompleteType(String completeType) {
                this.completeType = completeType;
            }

            private String createTime;
            private String createUserId;
            private int    id;
            private String latitude;
            private String longitude;
            private String patrolAddress;
            private String patrolName;
            private String patrolRecordId;
            private String patrolType;
            private String picturesOssKeys;
            private String projectId;
            private String projectName;
            private String remark;
            private long updateTime;
            private String updateUserId;

            private String patrolTypeName;

            public String getPatrolTypeName() {
                return patrolTypeName;
            }

            public void setPatrolTypeName(String patrolTypeName) {
                this.patrolTypeName = patrolTypeName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(String createUserId) {
                this.createUserId = createUserId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getPatrolAddress() {
                return patrolAddress;
            }

            public void setPatrolAddress(String patrolAddress) {
                this.patrolAddress = patrolAddress;
            }

            public String getPatrolName() {
                return patrolName;
            }

            public void setPatrolName(String patrolName) {
                this.patrolName = patrolName;
            }

            public String getPatrolRecordId() {
                return patrolRecordId;
            }

            public void setPatrolRecordId(String patrolRecordId) {
                this.patrolRecordId = patrolRecordId;
            }

            public String getPatrolType() {
                return patrolType;
            }

            public void setPatrolType(String patrolType) {
                this.patrolType = patrolType;
            }

            public String getPicturesOssKeys() {
                return picturesOssKeys;
            }

            public void setPicturesOssKeys(String picturesOssKeys) {
                this.picturesOssKeys = picturesOssKeys;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateUserId() {
                return updateUserId;
            }

            public void setUpdateUserId(String updateUserId) {
                this.updateUserId = updateUserId;
            }
        }
    }
}
