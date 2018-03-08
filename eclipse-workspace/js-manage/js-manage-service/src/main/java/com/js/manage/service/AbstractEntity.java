package com.js.manage.service;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = -1102125832013085981L;
    private Date addBeginTime;
    private Date addEndTime;
    private String searchKeyWord;
    private Date addTime;
    private Date updateTime;
    private Long updateUserId;
    private Long addUserId;
    private String uuid;

    public AbstractEntity() {
    }

    public Date getAddTime() {
        return this.addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return this.updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getAddUserId() {
        return this.addUserId;
    }

    public void setAddUserId(Long addUserId) {
        this.addUserId = addUserId;
    }

    public abstract ID getId();

    public abstract void setId(ID var1);

    @JSONField(
        serialize = false
    )
    public boolean isNew() {
        return null == this.getId();
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

