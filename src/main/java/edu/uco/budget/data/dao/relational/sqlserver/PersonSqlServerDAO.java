package edu.uco.budget.data.dao.relational.sqlserver;

import edu.uco.budget.data.dao.PersonDAO;
import edu.uco.budget.data.dao.relational.DAORelational;
import edu.uco.budget.domain.PersonDTO;

import java.sql.Connection;
import java.util.List;

public final class PersonSqlServerDAO extends DAORelational implements PersonDAO {
    public PersonSqlServerDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(PersonDTO person) {

    }

    @Override
    public final List<PersonDTO> find(PersonDTO person) {
        return null;
    }

    @Override
    public final void update(PersonDTO person) {

    }

    @Override
    public final void delete(PersonDTO person) {

    }
}
