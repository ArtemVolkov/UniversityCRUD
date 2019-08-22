package com.volkov.UniversityCRUD.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnMessage {

    private String type;
    private Object message;

    @Builder
    private ReturnMessage(boolean success, Object message) {
        type = "Fail";
        if (success)
            type = "Success";
        this.message = message;
    }
}
