package edu.uco.budget.domain;
import edu.uco.budget.crosscuting.helper.NumberHelper;
import edu.uco.budget.crosscuting.helper.UUIDhelper;
import java.util.*;
import static edu.uco.budget.crosscuting.helper.NumberHelper.ZERO;
import static edu.uco.budget.crosscuting.helper.NumberHelper.isLessThan;
import static edu.uco.budget.crosscuting.helper.UUIDhelper.getNewUUID;

public class yearDTO {
    private UUID id;
    private short yearNumber;

    public yearDTO(UUID id, short yearNumber) {
        this.id = id;
        this.yearNumber = yearNumber;
    }

    public yearDTO(){
        setId(getNewUUID());
        setYearNumber(ZERO);
    }

    public static final yearDTO create (final UUID id, final short yearNumber){
        return new yearDTO(id, yearNumber);
    }

    public final UUID getId() {
        return id;
    }

    public final void setId(UUID id) {
        this.id = UUIDhelper.getDefaultUUID(id);
    }

    public final short getYearNumber() {
        return yearNumber;
    }

    public final void setYearNumber(short yearNumber) {
        this.yearNumber = isLessThan(yearNumber, ZERO) ? ZERO : yearNumber;
    }
}
