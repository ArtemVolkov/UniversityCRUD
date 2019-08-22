package com.volkov.UniversityCRUD.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnMessage {

    private String type;
    private Object message;

    private ReturnMessage(boolean success, Object message) {
        type = "Fail";
        if (success)
            type = "Success";
        this.message = message;
    }

    public static ReturnMessage getInstanceOfSuccessMessage(Object object) {
        return new ReturnMessage(true, object);
    }

    public static ReturnMessage getInstanceOfFailMessage(Object object) {
        return new ReturnMessage(false, object);
    }

}
