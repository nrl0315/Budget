package edu.uco.budget.service.usecase.person.implementation;

import edu.uco.budget.data.daofactory.DAOFactory;
import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.service.usecase.person.FindPersonById;

import java.util.List;
import java.util.UUID;

public final class FindPersonByIdImpl implements FindPersonById {

    private final DAOFactory factory;

    public FindPersonByIdImpl(DAOFactory factory){
        this.factory = factory;
    }

    @Override
    public PersonDTO execute(UUID id) {
        PersonDTO result = new PersonDTO();
        final PersonDTO person = PersonDTO.create(id);
        final List<PersonDTO> results = factory.getPersonDAO().find(person);

        if(!results.isEmpty()){
            result = results.get(0);
        }
        return null;
    }
}
