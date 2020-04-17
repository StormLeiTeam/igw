package com.igw.igw.widget.storm.pickerTime.listener;

import com.igw.igw.widget.storm.pickerTime.bean.DateBean;
import com.igw.igw.widget.storm.pickerTime.bean.DateType;


/**
 * Created by codbking on 2016/9/22.
 */

public interface WheelPickerListener {
     void onSelect(DateType type, DateBean bean);
}
