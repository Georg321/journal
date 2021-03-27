package com.journalist.journal.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MicroserviceBaseResponse { // this class was created to be used as response to actions of user, giving feedback if operation was successful or not
    private boolean result;
    private String errorMessage;
    private int code;
}
