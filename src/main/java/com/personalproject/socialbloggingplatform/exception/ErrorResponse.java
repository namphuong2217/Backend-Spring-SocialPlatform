package com.personalproject.socialbloggingplatform.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    // error message
    private String message;
    // specific errors for API request
    private List<String> details;
}
