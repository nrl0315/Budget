package edu.uco.budget.crosscuting.helper;

import static edu.uco.budget.crosscuting.helper.ObjectHelper.getDefault;

public final class NumberHelper {
    public static final byte ZERO = 0;
    private NumberHelper(){
        super();
    }

    public static final <T extends Number> T getDefaultNumber(T value, T defaultValue){
        return getDefault(value, defaultValue);
    }

    public static final <T extends Number> Number getDefaultNumber(T value){
        return getDefaultNumber(value, ZERO);
    }

    public static final <T extends Number> boolean isGreaterThan (T numberOne, T numberTwo){
       return getDefaultNumber(numberOne).doubleValue() > getDefaultNumber(numberTwo).doubleValue();
    }

    public static final <T extends Number> boolean isLessThan (T numberOne, T numberTwo){
        return getDefaultNumber(numberOne).doubleValue() < getDefaultNumber(numberTwo).doubleValue();
    }

    public static final <T extends Number> boolean isEquals (T numberOne, T numberTwo){
        return getDefaultNumber(numberOne).doubleValue() == getDefaultNumber(numberTwo).doubleValue();
    }

    public static final <T extends Number> boolean isGreaterOrEquals (T numberOne, T numberTwo){
        return getDefaultNumber(numberOne).doubleValue() >= getDefaultNumber(numberTwo).doubleValue();
    }

    public static final <T extends Number> boolean isLessOrEquals (T numberOne, T numberTwo){
        return getDefaultNumber(numberOne).doubleValue() <= getDefaultNumber(numberTwo).doubleValue();
    }

    public static final <T extends Number> boolean isDifferentThan (T numberOne, T numberTwo){
        return !isEquals(numberOne,numberTwo);
    }
}
