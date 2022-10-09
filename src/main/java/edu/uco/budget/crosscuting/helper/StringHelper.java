package edu.uco.budget.crosscuting.helper;

import static edu.uco.budget.crosscuting.helper.ObjectHelper.getDefault;

public class StringHelper {
    public static final String EMPTY = "";

    private StringHelper(){
        super();
    }
    public static final String getDefaultString(String value, String defaultValue){
        return getDefault(value,defaultValue);
    }

    public static final String getDefaultString(String value){
        return getDefault(value, EMPTY);
    }

    public static final String applyTrim(String value){
        return getDefaultString(value).trim();
    }
}
