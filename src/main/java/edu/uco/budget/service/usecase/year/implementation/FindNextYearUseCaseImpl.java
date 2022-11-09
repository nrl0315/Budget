package edu.uco.budget.service.usecase.year.implementation;

import edu.uco.budget.crosscuting.helper.DateHelper;
import edu.uco.budget.crosscuting.helper.UUIDhelper;
import edu.uco.budget.data.daofactory.DAOFactory;
import edu.uco.budget.domain.YearDTO;
import edu.uco.budget.service.usecase.year.FindNextYearUseCase;

import java.util.List;

public class FindNextYearUseCaseImpl implements FindNextYearUseCase {

    private final DAOFactory factory;

    public FindNextYearUseCaseImpl(DAOFactory factory){
        this.factory = factory;
    }

    @Override
    public YearDTO execute() {
        YearDTO year = YearDTO.create("", DateHelper.getNextYear());
        List<YearDTO> results = factory.getYearDAO().find(year);

        if(!results.isEmpty()){
            year.setId(UUIDhelper.getNewUUID());
            factory.getYearDAO().create(year);

        } else{
            year = results.get(0);
        }
        return year;
    }
}
