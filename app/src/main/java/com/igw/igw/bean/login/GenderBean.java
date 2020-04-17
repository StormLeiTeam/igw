package com.igw.igw.bean.login;

import com.igw.igw.widget.storm.popwindowselect.popselectview.bean.IWheelEntity;

import java.io.Serializable;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe 性别类型
 */
public class GenderBean implements IWheelEntity, Serializable {




    public int id;  // 对应的id

    public String enName;  // 对应的英文说明

    public String chName;  // 对应的中文说明

    public boolean isEnglish; // 是否英文模式


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public boolean isEnglish() {
        return isEnglish;
    }

    public void setEnglish(boolean english) {
        isEnglish = english;
    }


    @Override
    public String toString() {
        return "GenderBean{" +
                "id=" + id +
                ", enName='" + enName + '\'' +
                ", chName='" + chName + '\'' +
                ", isEnglish=" + isEnglish +
                '}';
    }

    @Override
    public String getWheelText() {

        return isEnglish ? enName : chName;
    }
}
