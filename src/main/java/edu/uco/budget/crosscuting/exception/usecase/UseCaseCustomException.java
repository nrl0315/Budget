package edu.uco.budget.crosscuting.exception.usecase;

import edu.uco.budget.crosscuting.exception.data.BudgetCustomException;
import edu.uco.budget.crosscuting.exception.enumeration.LayerException;

import static edu.uco.budget.crosscuting.helper.StringHelper.EMPTY;

public class UseCaseCustomException extends BudgetCustomException {
    private static final long serialVersionUID = 7955662894932198270l;
    protected UseCaseCustomException(final String userMessage, final String technicalMessage, final Exception rootException) {
        super(userMessage, technicalMessage,rootException , LayerException.DATA);
    }

    protected UseCaseCustomException(String userMessage) {
        super(userMessage);
    }


    public static final BudgetCustomException createUserException(final String userMessage){
        return new UseCaseCustomException(userMessage);
    }

    public static final BudgetCustomException createTechnicalException(final String technicalMessage, final Exception exception){
        return new UseCaseCustomException(EMPTY, technicalMessage,new Exception());
    }

    public static final BudgetCustomException createBusinessException(final String businessMessage, final Exception exception){
        return new UseCaseCustomException(businessMessage, EMPTY,new Exception());
    }

    public static final BudgetCustomException create(final String userMessage, final String technicalMessage, final Exception exception){
        return new UseCaseCustomException(userMessage,technicalMessage,new Exception());
    }

    public static final BudgetCustomException wrapException(final String message, final BudgetCustomException exception){
        if(exception.isTechnicalException()){
            return UseCaseCustomException.createBusinessException(null,exception);
        }
        return exception;
    }
}
