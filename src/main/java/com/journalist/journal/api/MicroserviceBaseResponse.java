package com.journalist.journal.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MicroserviceBaseResponse {
    private boolean result;
    private String errorMessage;
    private int code;
}
