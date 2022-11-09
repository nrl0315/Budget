package edu.uco.budget.service.command.implementation;

import edu.uco.budget.crosscuting.exception.data.BudgetCustomException;
import edu.uco.budget.crosscuting.exception.usecase.UseCaseCustomException;
import edu.uco.budget.crosscuting.messages.Messages;
import edu.uco.budget.data.daofactory.DAOFactory;
import edu.uco.budget.data.enumeration.DAOFactoryType;
import edu.uco.budget.domain.BudgetDTO;
import edu.uco.budget.service.command.CreateBudgetCommand;
import edu.uco.budget.service.usecase.budget.CreateBudgetUseCase;
import edu.uco.budget.service.usecase.budget.implementation.CreateBudgetUseCaseImpl;

public class CreateBudgetCommandImpl implements CreateBudgetCommand {

    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.SQLSERVER);
    private final CreateBudgetUseCase useCase = new CreateBudgetUseCaseImpl(factory);

    @Override
    public final void execute(final BudgetDTO budget) {
        try{
            factory.initTransaction();
            useCase.execute(budget);
            factory.confirmTransaction();
        }catch(UseCaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        }catch(final BudgetCustomException exception){
            factory.cancelTransaction();
            throw UseCaseCustomException.wrapException(null,exception);
        }catch(final Exception exception){
            factory.cancelTransaction();
            throw UseCaseCustomException.createBusinessException(null,exception);
        } finally {
            factory.closeConnection();
        }
    }
}
