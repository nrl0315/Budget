package edu.uco.budget.service.usecase.budget;

import edu.uco.budget.domain.BudgetDTO;

import java.util.List;

public interface FindBudgetUseCase {
    List<BudgetDTO> execute(BudgetDTO budget);
}
