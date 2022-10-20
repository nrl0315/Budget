package edu.uco.budget.data.daofactory;

import edu.uco.budget.data.dao.BudgetDAO;
import edu.uco.budget.data.dao.PersonDAO;
import edu.uco.budget.data.dao.YearDAO;
import edu.uco.budget.data.daofactory.DAOFactory;

final class SqlServerDAOFactory extends DAOFactory {

    SqlServerDAOFactory(){
        openConnection();
    }

    @Override
    protected void openConnection() {

    }

    @Override
    public void initTransaction() {

    }

    @Override
    public void confirmTransaction() {

    }

    @Override
    public void cancelTransaction() {

    }

    @Override
    public void closeConnection() {

    }

    @Override
    public BudgetDAO getBudgetDAO() {
        return null;
    }

    @Override
    public PersonDAO getPersonDAO() {
        return null;
    }

    @Override
    public YearDAO getYearDAO() {
        return null;
    }
}
