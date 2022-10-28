package edu.uco.budget.crosscuting.exception.data;

import edu.uco.budget.crosscuting.exception.enumeration.LayerException;

import static edu.uco.budget.crosscuting.helper.StringHelper.EMPTY;

public final class CrosscutingCostumException extends BudgetCustomException {
    private static final long serialVersionUID = 6955662894932198270l;

    public CrosscutingCostumException(final String userMessage, final String technicalMessage, final Exception rootException){
        super(userMessage,technicalMessage,rootException, LayerException.CROSSCUTING);
    }

    public static final BudgetCustomException createUserException(final String userMessage, final String technicalMessage,
                                                                  final Exception rootException){
        return new CrosscutingCostumException(userMessage,userMessage,new Exception());
    }

    public static final BudgetCustomException createTechnicalException(final String technicalMessage, final Exception exception){
        return new CrosscutingCostumException(EMPTY, technicalMessage,new Exception());
    }

    public static final BudgetCustomException create(final String userMessage, final String technicalMessage, final Exception exception){
        return new CrosscutingCostumException(userMessage,technicalMessage,new Exception());
    }
}
