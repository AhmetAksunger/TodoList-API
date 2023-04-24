package com.ahmetaksunger.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {

    private int status;
    private List<String> messages;
    private long timeStamp;
}
