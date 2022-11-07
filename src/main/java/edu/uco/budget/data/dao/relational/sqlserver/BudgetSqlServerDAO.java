package edu.uco.budget.data.dao.relational.sqlserver;

import edu.uco.budget.crosscuting.exception.data.DataCustomException;
import edu.uco.budget.crosscuting.helper.ObjectHelper;
import edu.uco.budget.crosscuting.helper.UUIDhelper;
import edu.uco.budget.crosscuting.messages.Messages;
import edu.uco.budget.data.dao.BudgetDAO;
import edu.uco.budget.data.dao.PersonDAO;
import edu.uco.budget.data.dao.YearDAO;
import edu.uco.budget.data.dao.relational.DAORelational;
import edu.uco.budget.domain.BudgetDTO;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static edu.uco.budget.crosscuting.helper.UUIDhelper.getUUIDAsString;

public final class BudgetSqlServerDAO extends DAORelational implements BudgetDAO {

    public BudgetSqlServerDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public final void create(BudgetDTO budget) {
        final var sql = "INSERT INTO Budget (id,,idYear,idPerson) VALUES (?,?,?)";

        try(final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,budget.getUUIDAsString());
            preparedStatement.setString(2,budget.getYear().getUUIDAsString());
            preparedStatement.setString(3,budget.getPerson().getUUIDAsString());
            preparedStatement.executeUpdate();
        } catch(final SQLException exception){
            String message = Messages.BudgetSqlServerDAO.TECHNICAL_PROBLEM_CREATING_BUDGET.concat(budget.getUUIDAsString());
            throw DataCustomException.createTechnicalException(message, exception);
        } catch(final Exception exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_WHEN_CREATING_BUDGET, exception);
        }
    }

    @Override
    public final List<BudgetDTO> find(BudgetDTO budget) {
        var results = new ArrayList<BudgetDTO>();
        var setWhere = true;
        var parameters = new ArrayList<Object>();
        final var sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT   bu.id AS idBudget, ");
        sqlBuilder.append("         bu.idYear AS idYear, ");
        sqlBuilder.append("         ye.year AS NumberYear, ");
        sqlBuilder.append("         bu.idPerson AS idPerson, ");
        sqlBuilder.append("         pe.idCard AS idCardPerson, ");
        sqlBuilder.append("         pe.firstName AS FirstNamePerson, ");
        sqlBuilder.append("         pe.secondName AS secondNamePerson, ");
        sqlBuilder.append("         pe.firstSurname AS PersonFirstSurname, ");
        sqlBuilder.append("         pe.secondSurname AS PersonSecondSurname ");
        sqlBuilder.append("FROM     Budget bu INNER JOIN Year ye on bu.id = ye.id ");
        sqlBuilder.append("         INNER JOIN person pe on bu.id = pe.id ");

        if(!ObjectHelper.isNull(budget)){
            if(UUIDhelper.isDefaultUUID(budget.getId())){
                sqlBuilder.append("WHERE bu.id = ? ");
                setWhere = false;
                parameters.add(budget.getUUIDAsString());
            }
            if(UUIDhelper.isDefaultUUID(budget.getYear().getId())){
                sqlBuilder.append(setWhere ? "WHERE":"AND ").append("bu.idYear = ? ");
                setWhere = false;
                parameters.add(budget.getYear().getUUIDAsString());
            }
            if(UUIDhelper.isDefaultUUID(budget.getPerson().getId())){
                sqlBuilder.append(setWhere ? "WHERE":"AND ").append("bu.idPerson = ? ");
                parameters.add(budget.getPerson().getUUIDAsString());
            }
        }
        sqlBuilder.append("ORDER BY pe.idCard ASC, ");
        sqlBuilder.append("ye.year ASC ");

        try(final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){
            for(int index = 0; index < parameters.size(); index ++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
            try(final var resultSet = preparedStatement.executeQuery()){

            }

        }catch(SQLException exception){
            String message = Messages.BudgetSqlServerDAO.TECHNICAL_ERROR_SEARCHING_INTO_BUDGET.concat(budget.getUUIDAsString());
            throw DataCustomException.createTechnicalException(message,exception);
        }catch(Exception exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_ERROR_WHEN_TRYING_TO_SEARCH,exception);
        }
        return results;
    }

    @Override
    public final void update(BudgetDTO budget) {
        final var sql = "UPDATE Budget SET idYear = ? , idPerson = ? WHERE id = ?";

        try(final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,budget.getYear().getUUIDAsString());
            preparedStatement.setString(2,budget.getPerson().getUUIDAsString());
            preparedStatement.setString(3,budget.getUUIDAsString());
            preparedStatement.executeUpdate();
        } catch(final SQLException exception){
            String message = Messages.BudgetSqlServerDAO.TECHNICAL_ERROR_UPDATING_BUDGET.concat(budget.getUUIDAsString());
            throw DataCustomException.createTechnicalException(message,exception);
        } catch(final Exception exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_WHEN_UPDATING_BUDGET,exception);
        }

    }

    @Override
    public final void delete(UUID id) {
        final var sql = "DELETE FROM Budget where id = ?";
        final var UUIDAsString = getUUIDAsString(id);

        try(final var preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,UUIDAsString);
            preparedStatement.executeUpdate();
        } catch(final SQLException exception){
            String message = Messages.BudgetSqlServerDAO.TECHNICAL_ERROR_DELETING_BUDGET.concat(getUUIDAsString(id));
            throw DataCustomException.createTechnicalException(message,exception);
        } catch(final Exception exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_WHEN_TRYING_TO_DELETE,exception);
        }
    }
}
