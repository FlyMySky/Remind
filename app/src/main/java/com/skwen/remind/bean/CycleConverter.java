package com.skwen.remind.bean;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;

/**
 * 作者：vicent
 * 时间：2019/1/29
 */
public class CycleConverter implements PropertyConverter<List<String>, String> {
    @Override
    public List<String> convertToEntityProperty(String databaseValue) {
        String[] values = databaseValue.split(",");
        return Arrays.asList(values);
    }

    @Override
    public String convertToDatabaseValue(List<String> entityProperty) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : entityProperty) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
