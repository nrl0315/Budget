package edu.uco.budget.crosscuting.messages;

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
}
