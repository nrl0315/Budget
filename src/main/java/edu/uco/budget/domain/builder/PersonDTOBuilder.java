package edu.uco.budget.domain.builder;

import edu.uco.budget.domain.PersonDTO;

import java.util.UUID;

public final class PersonDTOBuilder {
    private UUID id;
    private String idCard;
    private String firstName;
    private String secondName;
    private String firstSurname;
    private String secondSurname;

    private PersonDTOBuilder(){
        super();
    }

    public final PersonDTOBuilder setId(final UUID id) {
        this.id = id;
        return this;
    }

    public final PersonDTOBuilder setIdCard(final String idCard) {
        this.idCard = idCard;
        return this;
    }

    public final PersonDTOBuilder setFirstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public final PersonDTOBuilder setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public final PersonDTOBuilder setFirstSurname(final String firstSurname) {
        this.firstSurname = firstSurname;
        return this;
    }

    public final PersonDTOBuilder setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
        return this;
    }

}
