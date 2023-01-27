package com.mypractice.microservice.socialcore.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ready {

    private List<Error> errors;
    private int status;
    private Map<String, String> data;
    private Map<String, String> metadata;

}

