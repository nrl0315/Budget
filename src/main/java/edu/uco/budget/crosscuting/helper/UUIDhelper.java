package edu.uco.budget.crosscuting.helper;

import edu.uco.budget.crosscuting.exception.data.CrosscutingCostumException;
import edu.uco.budget.crosscuting.messages.Messages;

import java.util.UUID;

public final class UUIDhelper {
    private static final  String DEFAULT_UUID_AS_STRING = "00000000-0000-0000-0000-000000000000";
    private static final UUID DEFAULT_UUID = UUID.fromString(DEFAULT_UUID_AS_STRING);

    private UUIDhelper(){
        super();
    }

    public static final UUID getDefaultUUID(final UUID value){
        return ObjectHelper.getDefault(value, DEFAULT_UUID);
    }

    public static final UUID getNewUUID(){
        UUID uuid;

        do{
            uuid = UUID.randomUUID();
        }while(isDefaultUUID(uuid));
        return uuid;
    }

    public static final String getUUIDAsString(final UUID value){
        return getDefaultUUID(value).toString();
    }

    public static final UUID getUUIDFromString(final String value){
        try{
            return UUID.fromString(StringHelper.getDefaultString(value,DEFAULT_UUID_AS_STRING));
        } catch(IllegalArgumentException exception){
            throw CrosscutingCostumException.createTechnicalException(Messages.UUIDHelper.TECHNICAL_INVALID_STRING_TO_CREATE_UUID,exception);
        } catch(Exception exception){
            throw CrosscutingCostumException.createTechnicalException(Messages.UUIDHelper.TECHNICAL_UNEXPECTED_ERROR_CREATING_UUID_FROM_STRING,exception);
        }
    }

    public static final boolean isDefaultUUID(final UUID value){
        return DEFAULT_UUID.equals(value);
    }

}
