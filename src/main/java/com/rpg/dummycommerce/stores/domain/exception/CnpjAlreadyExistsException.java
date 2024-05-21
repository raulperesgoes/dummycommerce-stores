package com.rpg.dummycommerce.stores.domain.exception;

import java.sql.SQLException;

public class CnpjAlreadyExistsException extends SQLException {
    public CnpjAlreadyExistsException(String message){
        super(message);
    }
}
