package edu.uco.budget.service.usecase.budget.implementation;

import edu.uco.budget.crosscuting.exception.usecase.UseCaseCustomException;
import edu.uco.budget.data.daofactory.DAOFactory;
import edu.uco.budget.data.enumeration.DAOFactoryType;
import edu.uco.budget.domain.BudgetDTO;
import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.domain.YearDTO;
import edu.uco.budget.service.usecase.budget.CreateBudgetUseCase;
import edu.uco.budget.service.usecase.person.FindPersonById;
import edu.uco.budget.service.usecase.person.implementation.FindPersonByIdImpl;
import edu.uco.budget.service.usecase.year.FindNextYearUseCase;
import edu.uco.budget.service.usecase.year.implementation.FindNextYearUseCaseImpl;

public final class CreateBudgetUseCaseImpl implements CreateBudgetUseCase {

    private final DAOFactory factory;
    private final FindNextYearUseCase findNextYearUseCase;
    private final FindPersonById findPersonById;

    public CreateBudgetUseCaseImpl(DAOFactory factory) {
        this.factory = factory;
        findNextYearUseCase = new FindNextYearUseCaseImpl(factory);
        findPersonById = new FindPersonByIdImpl(factory);
    }

    @Override
    public final void execute(final BudgetDTO budget) {

        final YearDTO year = findNextYearUseCase.execute();
        final PersonDTO person = findPersonById.execute(budget.getPerson().getId());
        if(person.notExist()){
            throw UseCaseCustomException.createUserException(null,null,null);
        }
        factory.getBudgetDAO().create(budget);
    }
}
