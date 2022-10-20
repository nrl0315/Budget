package edu.uco.budget.data.dao;

import edu.uco.budget.domain.BudgetDTO;

import java.util.List;
import java.util.UUID;

public interface BudgetDAO {

    void create(final BudgetDTO budget);
    List<BudgetDTO> find (BudgetDTO budget);
    void update(final BudgetDTO budget);
    void delete(final UUID id);

}
