package edu.uco.budget.crosscuting.messages;

import java.util.UUID;

public class Messages {

    private Messages(){
        super();
    }

    public static class DAOFactory{
        private DAOFactory(){
            super();
        }

        public static final String TECHNICAL_MONGODB_NOT_IMPLEMENTED = "DAOFactory for MONGODB is not implemented yet";
        public static final String TECHNICAL_CASSANDRA_NOT_IMPLEMENTED = "DAOFactory for CASSANDRA is not implemented yet";
        public static final String TECHNICAL_MYSQL_NOT_IMPLEMENTED = "DAOFactory for MYSQL is not implemented yet";
        public static final String TECHNICAL_ORACLE_NOT_IMPLEMENTED = "DAOFactory for ORACLE is not implemented yet";
        public static final String TECHNICAL_MARIADB_NOT_IMPLEMENTED = "DAOFactory for MARIADB is not implemented yet";
        public static final String TECHNICAL_POSTGRESQL_NOT_IMPLEMENTED = "DAOFactory for POSTGESQL is not implemented yet";
        public static final String TECHNICAL_UNEXPECTED_DAOFACTORY = " Unexpected DAOFactory";
    }

    public static class SqlConnectionHelper{
        private SqlConnectionHelper(){
            super();
        }
        public static final String CONNECTION_IS_NULL = "Connection is null";
        public static final String CONNECTION_IS_CLOSED = "The connection is already closed";
        public static final String CONNECTION_IS_ALREADY_CLOSED = "The connection is already closed ";
        public static final String PROBLEM_TRYING_TO_CLOSE_THE_CONNECTION = "There was a problem when trying to close the connection";
        public static final String CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION = "The connection is closed and the system is unable to process the transaction";
        public static final String PROBLEM_TRYING_TO_INIT_TRANSACTION = "There was an error when trying to initiate the transaction";
        public static final String PROBLEM_TRYING_TO_COMMIT_TRANSACTION = "There was an error when trying to initiate the transaction COMMIT";
        public static final String PROBLEM_TRYING_TO_ROLLBACK_TRANSACTION = "There was an error when trying to initiate the transaction ROLLBACK";
        public static final String CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION = "The connection is closed and the system is unable to process the transaction COMMIT";
        public static final String CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION = "The connection is closed and the system is unable to process the transaction ROLLBACK";
    }

    public static class SQLDAOFactory{
        private SQLDAOFactory(){
            super();
        }
        public static final String TECHNICAL_PROBLEM_INIT_TRANSACTION = "There was a problem trying to init transaction";
    }

    public static class BudgetSqlServerDAO{
        private BudgetSqlServerDAO(){
            super();
        }
        public static final String TECHNICAL_PROBLEM_CREATING_BUDGET = "There was a problem when trying to create the desired budget " +
                "in SQLServerDAOFactory with id = ";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_WHEN_CREATING_BUDGET = "There was an unknown problem when trying " +
                "to create the budget";
        public static final String TECHNICAL_ERROR_UPDATING_BUDGET = "There was an error when trying to update the budget with id ";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_WHEN_UPDATING_BUDGET = "There was an unknown problem when trying " +
                "to update the budget";
        public static final String TECHNICAL_ERROR_DELETING_BUDGET = "There was an error when trying to delete the budget";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_WHEN_TRYING_TO_DELETE = "There was an error when trying to delete the " +
                "desired budget";
        public static final String TECHNICAL_ERROR_SEARCHING_INTO_BUDGET = "There was an error when trying to search into budget with the id ";
        public static final String TECHNICAL_UNEXPECTED_ERROR_WHEN_TRYING_TO_SEARCH = "There was an error when trying to search using " +
                "the desired budget";
        public static final String TECHNICAL_ERROR_FILLING_RESULTS = "There was an error when trying to fill the results";
        public static final String TECHNICAL_UNEXPECTED_ERROR_FILLING_RESULTS = "There was an unknown issue when trying to fill the results " +
                "on the desired list";
        public static final String TECHNICAL_ERROR_WHEN_FILLING_BUDGETDTO = "There was a problem when trying to fill BudgetDTO from resultsSet";
        public static final String TECHNICAL_UNEXPECTED_ERROR_FILLING_BUDGETDTO = "There was an unknown error when trying to fill the " +
                "budgetDTO from resultsSet";
        public static final String TECNICAL_ERROR_WHEN_FILLING_YEARDTO = "There was an error when trying to fill YearDTO from ResultsSet";
        public static final String TECHNICAL_UNEXPECTED_ERROR_FILLING_YEARDTO = "There was an unexpected error when trying to fill the " +
                "yearDTO from ResultsSet";
        public static final String TECNICAL_ERROR_WHEN_FILLING_PERSONDTO = "There was an error when trying to fill personDTO from ResultsSet";
        public static final String TECHNICAL_UNEXPECTED_ERROR_FILLING_PERSONDTO = "There was an unexpected error when trying to fill the " +
                "personDTO from ResultsSet";
        public static final String TECHNICAL_ERROR_WHEN_EXECUTING_QUERY = "There was an error when trying to execute the query";
        public static final String TECHNICAL_UNEXPECTED_ERROR_EXECUTING_QUERY = "There was an unknown error when trying to execute the " +
                "query";
        public static final String TECHNICAL_ERROR_TRYING_TO_SET_PARAMETER_VALUES = "There was an error when trying to set the parameter " +
                "values using the query";
        public static final String TECHNICAL_UNEXPECTED_ERROR_TRYING_TO_SET_PARAMETER_VALUES = "There was an unknown error when trying " +
                "to set the parameter values using the desired query";
        public static final String TECHNICAL_PROBLEM_TRYING_TO_PREPARE_THE_QUERY_FOR_EXECUTE = "There as an error when trying to prepare " +
                "the query to execute the desired sql information";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_TRYING_TO_PREPARE_THE_QUERY_FOR_EXECUTE = "There was an unknown issue " +
                "when trying to prepare the query to execute the desired sql information";
    }

    public static class UUIDHelper{
        private UUIDHelper(){
            super();
        }

        public static final String TECHNICAL_INVALID_STRING_TO_CREATE_UUID = "There was an error when trying to create the UUID since " +
                "the argument do not match the format";
        public static final String TECHNICAL_UNEXPECTED_ERROR_CREATING_UUID_FROM_STRING = "There is an unexpected problem when trying to " +
                "convert the string to UUID ";
    }

    public static class CreateBudgetUseCaseImpl{
        private CreateBudgetUseCaseImpl(){
            super();
        }

        public static final String BUSINESS_PERSON_DOES_NOT_EXIST = "There was an error creating the budget since the person does not " +
                "exist on the data base";
        public static final String BUSINESS_BUDGET_ALREADY_EXIST = "The budget that you are trying to create already exist on our data " +
                "base for the persona and also for the year";
    }
}
