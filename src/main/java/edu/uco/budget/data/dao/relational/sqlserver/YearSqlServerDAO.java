package edu.uco.budget.data.dao.relational.sqlserver;

import edu.uco.budget.data.dao.YearDAO;
import edu.uco.budget.data.dao.relational.DAORelational;
import edu.uco.budget.domain.YearDTO;

import java.sql.Connection;
import java.util.List;

public final class YearSqlServerDAO extends DAORelational implements YearDAO {
    public YearSqlServerDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public final void create(YearDTO year) {

    }

    @Override
    public final List<YearDTO> find(YearDTO year) {
        return null;
    }

    @Override
    public final void update(YearDTO year) {

    }

    @Override
    public final void delete(YearDTO year) {

    }
}
