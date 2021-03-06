package com.skwen.remind.bean;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;


/**
 * 作者：vicent
 * 时间：2019/1/29
 */
@Entity
public class Record {


    @Id(autoincrement = true)
    private Long id;

    /**
     * 存储类型 语音 文字
     */
    private int type;

    /**
     * 事件
     */
    private String action;

    /**
     * 内容
     */
    private String content;
    /**
     * 年
     */
    private int yearTime;

    /**
     * 月
     */
    private int mouthTime;

    /**
     * 日
     */
    private int dayTime;
    /**
     * 提醒日期 2018 12 30
     */
    private Long remindDate;

    /**
     * 是否是每天
     */
    private boolean isEveryDay;

    /**
     * 提醒时间 00:00
     */
    private String remindTime;

    /**
     * 保存时间
     */
    private Long saveTime;

    @Convert(columnType = String.class, converter = CycleConverter.class)
    private List<String> cycles;

    /**
     * 提醒是否完成
     */
    private boolean isOver;
    /**
     * 提醒开关
     */
    private boolean isOpen;

    /**
     * 0 normal 1 middle 2 important
     */
    private int level;

    @Generated(hash = 102448090)
    public Record(Long id, int type, String action, String content, int yearTime,
            int mouthTime, int dayTime, Long remindDate, boolean isEveryDay,
            String remindTime, Long saveTime, List<String> cycles, boolean isOver,
            boolean isOpen, int level) {
        this.id = id;
        this.type = type;
        this.action = action;
        this.content = content;
        this.yearTime = yearTime;
        this.mouthTime = mouthTime;
        this.dayTime = dayTime;
        this.remindDate = remindDate;
        this.isEveryDay = isEveryDay;
        this.remindTime = remindTime;
        this.saveTime = saveTime;
        this.cycles = cycles;
        this.isOver = isOver;
        this.isOpen = isOpen;
        this.level = level;
    }

    @Generated(hash = 477726293)
    public Record() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getYearTime() {
        return this.yearTime;
    }

    public void setYearTime(int yearTime) {
        this.yearTime = yearTime;
    }

    public int getMouthTime() {
        return this.mouthTime;
    }

    public void setMouthTime(int mouthTime) {
        this.mouthTime = mouthTime;
    }

    public String getRemindTime() {
        return this.remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public Long getSaveTime() {
        return this.saveTime;
    }

    public void setSaveTime(Long saveTime) {
        this.saveTime = saveTime;
    }

    public List<String> getCycles() {
        return this.cycles;
    }

    public void setCycles(List<String> cycles) {
        this.cycles = cycles;
    }

    public boolean getIsOver() {
        return this.isOver;
    }

    public void setIsOver(boolean isOver) {
        this.isOver = isOver;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDayTime() {
        return this.dayTime;
    }

    public void setDayTime(int dayTime) {
        this.dayTime = dayTime;
    }

    public Long getRemindDate() {
        return this.remindDate;
    }

    public void setRemindDate(Long remindDate) {
        this.remindDate = remindDate;
    }

    public boolean getIsOpen() {
        return this.isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean getIsEveryDay() {
        return this.isEveryDay;
    }

    public void setIsEveryDay(boolean isEveryDay) {
        this.isEveryDay = isEveryDay;
    }


}
