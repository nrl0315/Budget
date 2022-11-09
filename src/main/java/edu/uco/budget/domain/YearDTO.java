package edu.uco.budget.domain;
import edu.uco.budget.crosscuting.helper.UUIDhelper;
import java.util.*;
import static edu.uco.budget.crosscuting.helper.NumberHelper.ZERO;
import static edu.uco.budget.crosscuting.helper.NumberHelper.isLessThan;
import static edu.uco.budget.crosscuting.helper.UUIDhelper.*;

public final class YearDTO {
    private UUID id;
    private short yearNumber;

    public YearDTO(UUID id, short yearNumber) {
        this.id = id;
        this.yearNumber = yearNumber;
    }

    public YearDTO(){
        setId(getDefaultUUID(id));
        setYearNumber(ZERO);
    }

    public static final YearDTO create (final UUID id, final short yearNumber){
        return new YearDTO(id, yearNumber);
    }

    public static final YearDTO create (final String id, final short yearNumber){
        return new YearDTO(getUUIDFromString(id), yearNumber);
    }

    public final UUID getId() {
        return id;
    }

    public final void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }

    public final short getYearNumber() {
        return yearNumber;
    }

    public final void setYearNumber(short yearNumber) {
        this.yearNumber = isLessThan(yearNumber, ZERO) ? ZERO : yearNumber;
    }

    public final String getUUIDAsString(){
        return UUIDhelper.getUUIDAsString(getId());
    }
}
