package com.cloudmart.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response {
    private boolean status;
    private List<String> errorMessages=new ArrayList<>();
    private Object responseData;
    private Integer pages;
}
