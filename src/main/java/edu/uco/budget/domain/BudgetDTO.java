package edu.uco.budget.domain;

import java.util.UUID;

import static edu.uco.budget.crosscuting.helper.ObjectHelper.getDefault;
import static edu.uco.budget.crosscuting.helper.UUIDhelper.getDefaultUUID;
import static edu.uco.budget.domain.builder.YearDTOBuilder.getYearDTOBuilder;
import static edu.uco.budget.domain.builder.PersonDTOBuilder.getPersonDTOBuilder;

public final class BudgetDTO {

    private UUID id;
    private YearDTO year;
    private PersonDTO person;

    public BudgetDTO() {
        super();
    }

    public UUID getId() {
        return id;
    }

    public final void setId(final UUID id) {
        this.id = getDefaultUUID(id);
    }

    public YearDTO getYear() {
        return year;
    }

    public void setYear(YearDTO year) {
        this.year = getDefault(year, getYearDTOBuilder().build());
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = getDefault(person, getPersonDTOBuilder().build());
    }
}
