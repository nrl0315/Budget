package edu.uco.budget.data.dao.relational;

import edu.uco.budget.crosscuting.helper.SqlConnectionHelper;
import edu.uco.budget.crosscuting.messages.Messages;
import java.sql.Connection;
import static edu.uco.budget.crosscuting.helper.SqlConnectionHelper.connectionIsOpen;

public class DAORelational {

    private Connection connection;
    protected DAORelational(final Connection connection){
         if(!connectionIsOpen(connection)){
             throw new RuntimeException(Messages.SqlConnectionHelper.CONNECTION_IS_CLOSED);
         }
         this.connection = connection;
    }

    protected final Connection getConnection(){
        return connection;
    }
}
