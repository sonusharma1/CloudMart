package com.cloudmart.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Response {
    private boolean isSuccess=false;
    private List<String> errorMessages=new ArrayList<>();
    private Object responseData=null;
}
