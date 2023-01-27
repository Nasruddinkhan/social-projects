package com.mypractice.microservice.socialcore.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private Date timestamp;
    private String path;
    private Object status;
    private String error;
    private String requestId;
    private String details;


}