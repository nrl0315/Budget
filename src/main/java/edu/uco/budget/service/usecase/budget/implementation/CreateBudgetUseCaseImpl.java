package edu.uco.budget.service.usecase.budget.implementation;

import edu.uco.budget.crosscuting.exception.usecase.UseCaseCustomException;
import edu.uco.budget.crosscuting.helper.UUIDhelper;
import edu.uco.budget.crosscuting.messages.Messages;
import edu.uco.budget.data.daofactory.DAOFactory;
import edu.uco.budget.data.enumeration.DAOFactoryType;
import edu.uco.budget.domain.BudgetDTO;
import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.domain.YearDTO;
import edu.uco.budget.service.usecase.budget.CreateBudgetUseCase;
import edu.uco.budget.service.usecase.budget.FindBudgetUseCase;
import edu.uco.budget.service.usecase.person.FindPersonById;
import edu.uco.budget.service.usecase.person.implementation.FindPersonByIdImpl;
import edu.uco.budget.service.usecase.year.FindNextYearUseCase;
import edu.uco.budget.service.usecase.year.implementation.FindNextYearUseCaseImpl;

import java.util.List;
import java.util.UUID;

public final class CreateBudgetUseCaseImpl implements CreateBudgetUseCase {

    private final DAOFactory factory;
    private final FindNextYearUseCase findNextYearUseCase;
    private final FindPersonById findPersonById;
    private final FindBudgetUseCase findBudgetUseCase;

    public CreateBudgetUseCaseImpl(DAOFactory factory) {
        this.factory = factory;
        findNextYearUseCase = new FindNextYearUseCaseImpl(factory);
        findPersonById = new FindPersonByIdImpl(factory);
        findBudgetUseCase = new FindBudgetUseCaseImpl(factory);
    }

    @Override
    public final void execute(final BudgetDTO budget) {

        final YearDTO year = findNextYearUseCase.execute();
        final PersonDTO person = findPerson(budget.getPerson().getId());
        budget.setYear(year);
        budget.setPerson(person);
        validateIfBudgetExist(budget);
        budget.setId(UUIDhelper.getNewUUID());

        factory.getBudgetDAO().create(budget);
    }

    private final PersonDTO findPerson(final UUID id){
        final PersonDTO person = findPersonById.execute(id);
        if(person.notExist()){
            throw UseCaseCustomException.createUserException(Messages.CreateBudgetUseCaseImpl.BUSINESS_PERSON_DOES_NOT_EXIST);
        }
        return person;
    }

    private final void validateIfBudgetExist(BudgetDTO budget){
        final List<BudgetDTO> results = findBudgetUseCase.execute(budget);

        if(!results.isEmpty()){
            throw UseCaseCustomException.createUserException(Messages.CreateBudgetUseCaseImpl.BUSINESS_BUDGET_ALREADY_EXIST);
        }
    }
}
