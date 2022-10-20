package edu.uco.budget.data.dao;

import edu.uco.budget.domain.PersonDTO;

import java.util.List;

public interface PersonDAO {

    void create(final PersonDTO person);
    List<PersonDTO> find(final PersonDTO person);
    void update(final PersonDTO person);
    void delete(PersonDTO person);
}
