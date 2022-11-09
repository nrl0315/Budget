package edu.uco.budget.data.daofactory;

import edu.uco.budget.crosscuting.exception.crosscuting.CrosscutingCostumException;
import edu.uco.budget.crosscuting.exception.data.DataCustomException;
import edu.uco.budget.crosscuting.helper.SqlConnectionHelper;
import edu.uco.budget.crosscuting.messages.Messages;
import edu.uco.budget.data.dao.BudgetDAO;
import edu.uco.budget.data.dao.PersonDAO;
import edu.uco.budget.data.dao.YearDAO;
import edu.uco.budget.data.dao.relational.sqlserver.BudgetSqlServerDAO;
import edu.uco.budget.data.dao.relational.sqlserver.PersonSqlServerDAO;
import edu.uco.budget.data.dao.relational.sqlserver.YearSqlServerDAO;

import java.sql.Connection;

final class SqlServerDAOFactory extends DAOFactory {

    private Connection connection;

    SqlServerDAOFactory(){
        openConnection();
    }

    @Override
    protected void openConnection() {
        connection = null;
    }

    @Override
    public void initTransaction() {
        try{
            SqlConnectionHelper.innitTransaction(connection);
        } catch (CrosscutingCostumException exception){
            throw DataCustomException.createTechnicalException(Messages.SQLDAOFactory.TECHNICAL_PROBLEM_INIT_TRANSACTION,new Exception());
        }
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
        return new BudgetSqlServerDAO(connection);
    }

    @Override
    public PersonDAO getPersonDAO() {
        return new PersonSqlServerDAO(connection);
    }

    @Override
    public YearDAO getYearDAO() {
        return new YearSqlServerDAO(connection);
    }
}
