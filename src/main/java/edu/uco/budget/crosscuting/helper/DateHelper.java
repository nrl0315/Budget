package edu.uco.budget.crosscuting.helper;

import java.time.LocalDate;

public final class DateHelper {
    private DateHelper(){
        super();
    }

    public static final short getNextYear(){
        return (short) LocalDate.now().plusYears(1).getYear();
    }
}
