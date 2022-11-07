package edu.uco.budget.domain;

import edu.uco.budget.crosscuting.helper.UUIDhelper;

import java.util.UUID;

import static edu.uco.budget.crosscuting.helper.UUIDhelper.getNewUUID;
import static edu.uco.budget.crosscuting.helper.StringHelper.EMPTY;

public final class PersonDTO {
    private UUID id;
    private String idCard;
    private String firstName;
    private String secondName;
    private String firstSurname;
    private String secondSurname;

    public PersonDTO() {
        setId(getNewUUID());
         setIdCard(EMPTY);
         setFirstName(EMPTY);
         setSecondName(EMPTY);
         setFirstSurname(EMPTY);
         setSecondSurname(EMPTY);
    }

    public PersonDTO(final UUID id, final String idCard, final String firstName, final String secondName, final String firstSurname,
                     final String secondSurname){
        setId(id);
        setIdCard(idCard);
        setFirstName(firstName);
        setSecondName(secondName);
        setFirstSurname(firstSurname);
        setSecondSurname(secondSurname);
    }

    public final UUID getId() {
        return id;
    }

    public final void setId(UUID id) {
        this.id = id;
    }

    public final String getIdCard() {
        return idCard;
    }

    public final void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public final String getSecondName() {
        return secondName;
    }

    public final void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public final String getFirstSurname() {
        return firstSurname;
    }

    public final void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public final String getSecondSurname() {
        return secondSurname;
    }

    public final void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public final String getUUIDAsString(){
        return UUIDhelper.getUUIDAsString(getId());
    }
}
