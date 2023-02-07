package com.ssafy.townbook.model.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

@Getter
@Setter
public class ErrorDto {
    
    private final int              status;
    private final String           message;
    private       List<FieldError> fieldErrors = new ArrayList<>();
    
    @Builder
    public ErrorDto(int status, String message) {
        this.status  = status;
        this.message = message;
    }
    
    @Builder
    public void addFieldError(String objectName, String path, String message) {
        FieldError error = new FieldError(objectName, path, message);
        fieldErrors.add(error);
    }
}