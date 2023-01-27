
package com.mypractice.microservice.socialcore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Error {
  private String message;
  private int status;
  private String code;
  private String moreInfo;


}
