package com.arbaaz.SpringSecurity8.responseWrapper;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResponseWrapper {


    private String message;
    private Object data;
}
