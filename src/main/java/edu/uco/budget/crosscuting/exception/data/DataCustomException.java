package edu.uco.budget.crosscuting.exception.data;

import edu.uco.budget.crosscuting.exception.enumeration.LayerException;
import static edu.uco.budget.crosscuting.helper.StringHelper.EMPTY;

public class DataCustomException extends BudgetCustomException{
    private static final long serialVersionUID = 7955662894932198270l;
    protected DataCustomException(final String userMessage, final String technicalMessage,final Exception rootException) {
        super(userMessage, technicalMessage,rootException , LayerException.DATA);
    }


    public static final BudgetCustomException createUserException(final String userMessage, final String technicalMessage,
                                                                  final Exception rootException){
        return new DataCustomException(userMessage,userMessage,new Exception());
    }

    public static final BudgetCustomException createTechnicalException(final String technicalMessage, final Exception exception){
        return new DataCustomException(EMPTY, technicalMessage,new Exception());
    }

    public static final BudgetCustomException create(final String userMessage, final String technicalMessage, final Exception exception){
        return new DataCustomException(userMessage,technicalMessage,new Exception());
    }
}
