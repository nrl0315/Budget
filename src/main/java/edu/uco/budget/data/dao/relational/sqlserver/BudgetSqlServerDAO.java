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
import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.domain.YearDTO;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        var parameters = new ArrayList<Object>();
        final var sqlBuilder = new StringBuilder();
        createSelectfrom(sqlBuilder);
        createWhere(sqlBuilder,budget,parameters);
        createOrderBy(sqlBuilder);
        return prepareAndExecuteQuery(sqlBuilder,parameters);

    }

    private final List<BudgetDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {
        try(final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){
            setParameterValues(preparedStatement,parameters);
            return executeQuery(preparedStatement);
        } catch (DataCustomException exception) {
            throw exception;
        }catch(SQLException exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_PROBLEM_TRYING_TO_PREPARE_THE_QUERY_FOR_EXECUTE,exception);
        }catch(Exception exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_TRYING_TO_PREPARE_THE_QUERY_FOR_EXECUTE,exception);
        }
    }

    private final List<BudgetDTO> executeQuery(PreparedStatement preparedStatement){
        try(final var resultSet = preparedStatement.executeQuery()){
            return fillResults(resultSet);
        }catch(DataCustomException exception){
            throw exception;
        } catch(SQLException exception){
            String message = Messages.BudgetSqlServerDAO.TECHNICAL_ERROR_WHEN_EXECUTING_QUERY;
            throw DataCustomException.createTechnicalException(message,exception);
        }catch(Exception exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_ERROR_EXECUTING_QUERY,exception);
        }
    }

    private final void setParameterValues(final PreparedStatement preparedStatement, final List<Object> parameters){
        try{
            for(int index = 0; index < parameters.size(); index ++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        }catch(SQLException exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_ERROR_TRYING_TO_SET_PARAMETER_VALUES,exception);
        }catch (Exception exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_ERROR_TRYING_TO_SET_PARAMETER_VALUES, exception);
        }
    }

    private final void createSelectfrom(final StringBuilder sqlBuilder){
        sqlBuilder.append("SELECT   bu.id AS idBudget, ");
        sqlBuilder.append("         bu.idYear AS idYear, ");
        sqlBuilder.append("         ye.year AS NumberYear, ");
        sqlBuilder.append("         bu.idPerson AS idPerson, ");
        sqlBuilder.append("         pe.idCard AS idCardPerson, ");
        sqlBuilder.append("         pe.firstName AS firstNamePerson, ");
        sqlBuilder.append("         pe.secondName AS secondNamePerson, ");
        sqlBuilder.append("         pe.firstSurname AS PersonFirstSurname, ");
        sqlBuilder.append("         pe.secondSurname AS PersonSecondSurname ");
        sqlBuilder.append("FROM     Budget bu INNER JOIN Year ye on bu.id = ye.id ");
        sqlBuilder.append("         INNER JOIN person pe on bu.id = pe.id ");
    }

    private final void createWhere(final StringBuilder sqlBuilder, final BudgetDTO budget, final List<Object> parameters){
        var setWhere = true;
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
    }

    private void createOrderBy(StringBuilder sqlBuilder) {
        sqlBuilder.append("ORDER BY pe.idCard ASC, ");
        sqlBuilder.append("ye.year ASC ");
    }

    private final List<BudgetDTO> fillResults(final ResultSet resultSet){
        try{
            var results = new ArrayList<BudgetDTO>();
            while (resultSet.next()){
                results.add(fillBudgetDTO(resultSet));
            }
            return results;
         }catch (final DataCustomException exception){
        throw exception;
         }catch (final SQLException exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_ERROR_FILLING_RESULTS, exception);
        }catch (final Exception exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_ERROR_FILLING_RESULTS, exception);
        }
    }

    private final BudgetDTO fillBudgetDTO(final ResultSet resultSet) {
        try {
            return BudgetDTO.create(resultSet.getString("idBudget"), fillPersonDTO(resultSet), fillYearDTO(resultSet));
        }catch (final DataCustomException exception){
            throw exception;
         }catch(final SQLException exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_ERROR_WHEN_FILLING_BUDGETDTO,exception);
        }catch (final Exception exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_ERROR_FILLING_BUDGETDTO,exception);
        }
    }

    private final YearDTO fillYearDTO(final ResultSet resultSet){
        try{
            return YearDTO.create(resultSet.getString("idYear"), resultSet.getShort("numberYear"));
        } catch(final SQLException exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECNICAL_ERROR_WHEN_FILLING_YEARDTO,exception);
        } catch (Exception exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_ERROR_FILLING_YEARDTO,exception);
        }
    }

    private final PersonDTO fillPersonDTO(final ResultSet resultSet){
        try{
            return PersonDTO.create(resultSet.getString("idPerson"),resultSet.getString("idCardPerson"),
                    resultSet.getString( "firstNamePerson"),resultSet.getString("secondNamePerson"), resultSet.getString("PersonFirstSurname"),
                    resultSet.getString("PersonSecondSurname"));
        } catch(final SQLException exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECNICAL_ERROR_WHEN_FILLING_PERSONDTO,exception);
        } catch (Exception exception){
            throw DataCustomException.createTechnicalException(Messages.BudgetSqlServerDAO.TECHNICAL_UNEXPECTED_ERROR_FILLING_PERSONDTO,exception);
        }
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
