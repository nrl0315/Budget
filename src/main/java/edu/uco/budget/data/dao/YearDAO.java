package edu.uco.budget.data.dao;

import edu.uco.budget.domain.YearDTO;

import java.util.List;

public interface YearDAO {

    void create(final YearDTO year);
    List<YearDTO> find(final YearDTO year);
    void update(final YearDTO year);
    void delete(YearDTO year);
}
