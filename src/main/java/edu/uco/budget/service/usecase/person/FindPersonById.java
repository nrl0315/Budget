package edu.uco.budget.service.usecase.person;

import edu.uco.budget.domain.PersonDTO;

import java.util.UUID;

public interface FindPersonById {

    PersonDTO execute (UUID id);
}
