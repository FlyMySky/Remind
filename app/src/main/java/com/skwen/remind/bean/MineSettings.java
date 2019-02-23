package com.skwen.remind.bean;

import java.io.Serializable;

/**
 * 作者：vicent
 * 时间：2019/2/23
 */
public class MineSettings implements Serializable {


    private int iconRes;


    private int rightRes;


    private String content;


    private int type;


    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public int getRightRes() {
        return rightRes;
    }

    public void setRightRes(int rightRes) {
        this.rightRes = rightRes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
