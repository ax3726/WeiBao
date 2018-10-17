package com.wb.weibao.model.earlywarning;

/**
 * Created by Administrator on 2018/10/17.
 */

public class QuestItemModel {
    private String content;
    private boolean is_select;

    public QuestItemModel(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIs_select() {
        return is_select;
    }

    public void setIs_select(boolean is_select) {
        this.is_select = is_select;
    }
}
