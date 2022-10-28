package edu.uco.budget.crosscuting.helper;

import edu.uco.budget.crosscuting.exception.data.CrosscutingCostumException;
import edu.uco.budget.crosscuting.messages.Messages;

import java.sql.Connection;
import java.sql.SQLException;

public final class SqlConnectionHelper {
    private SqlConnectionHelper(){
        super();
    }


    public static final boolean connectionIsNull(final Connection connection){
        return ObjectHelper.isNull(connection);
    }

    public static final boolean connectionIsOpen(final Connection connection){
        try {
            return !connectionIsNull(connection) && !connection.isClosed();
        } catch (final SQLException exception){
            throw new CrosscutingCostumException(Messages.SqlConnectionHelper.CONNECTION_IS_CLOSED,exception);
        }
    }

    public static final void closeConnection(final Connection connection){
        try {
            if (!connectionIsOpen(connection)) {
                throw CrosscutingCostumException.createTechnicalException(Messages.SqlConnectionHelper.CONNECTION_IS_ALREADY_CLOSED);
            }
            connection.close();
        } catch (final SQLException exception){
            throw CrosscutingCostumException.createTechnicalException(Messages.SqlConnectionHelper.PROBLEM_TRYING_TO_CLOSE_THE_CONNECTION);
        }
    }

    public static final void innitTransaction(final Connection connection){
        try {
            if (!connectionIsOpen(connection)) {
                throw CrosscutingCostumException.createTechnicalException(Messages.SqlConnectionHelper.CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION);
            }
            connection.setAutoCommit(false);
        } catch (final SQLException exception){
            throw CrosscutingCostumException.createTechnicalException(Messages.SqlConnectionHelper.PROBLEM_TRYING_TO_INIT_TRANSACTION);
        }
    }

    public static final void commitTransaction(final Connection connection){
        try {
            if (!connectionIsOpen(connection)) {
                throw CrosscutingCostumException.createTechnicalException(Messages.SqlConnectionHelper.CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION);
            }
            connection.setAutoCommit(false);
        } catch (final SQLException exception){
            throw CrosscutingCostumException.createTechnicalException(Messages.SqlConnectionHelper.PROBLEM_TRYING_TO_COMMIT_TRANSACTION);
        }
    }

    public static final void rollbackTransaction(final Connection connection){
        try {
            if (!connectionIsOpen(connection)) {
                throw CrosscutingCostumException.createTechnicalException(Messages.SqlConnectionHelper.CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION);
            }
            connection.rollback();
        } catch (final SQLException exception){
            throw CrosscutingCostumException.createTechnicalException(Messages.SqlConnectionHelper.PROBLEM_TRYING_TO_ROLLBACK_TRANSACTION);
        }
    }
}
