package com.wb.weibao.model;

import java.util.List;

/**
 * Created by shiqingqing on 2019/7/29.
 */

public class PatrolUserListBean {

    /**
     * code : 200
     * message : SUCCESS
     * data : ["cykj520","cykj250","cykj521","test0611"]
     */

    private String code;
    private String message;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
