package edu.uco.budget.domain;

import edu.uco.budget.crosscuting.helper.UUIDhelper;

import java.util.UUID;

import static edu.uco.budget.crosscuting.helper.ObjectHelper.getDefault;
import static edu.uco.budget.crosscuting.helper.UUIDhelper.*;
import static edu.uco.budget.domain.builder.YearDTOBuilder.getYearDTOBuilder;
import static edu.uco.budget.domain.builder.PersonDTOBuilder.getPersonDTOBuilder;

public final class BudgetDTO {

    private UUID id;
    private PersonDTO person;
    private YearDTO year;


    public BudgetDTO() {
        setId(getNewUUID());
        setPerson(getPersonDTOBuilder().build());
        setYear(getYearDTOBuilder().build());
    }

    public BudgetDTO(final UUID id, final PersonDTO person, final YearDTO year){
        setId(id);
        setPerson(person);
        setYear(year);
    }

    public static final BudgetDTO create (final UUID id, final PersonDTO person, final YearDTO year){
        return new BudgetDTO(id, person,year);
    }

    public static final BudgetDTO create (final String id, final PersonDTO person, final YearDTO year){
        return new BudgetDTO(getUUIDFromString(id), person, year);
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

    public final String getUUIDAsString(){
        return UUIDhelper.getUUIDAsString(getId());
    }
}
