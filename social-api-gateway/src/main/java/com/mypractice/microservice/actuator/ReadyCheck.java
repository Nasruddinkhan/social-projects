/*
 * PEARSON PROPRIETARY AND CONFIDENTIAL INFORMATION SUBJECT TO NDA 
 * Copyright (c) 2018 Pearson Education, Inc.
 * All Rights Reserved. 
 * 
 * NOTICE: All information contained herein is, and remains the property of 
 * Pearson Education, Inc. The intellectual and technical concepts contained 
 * herein are proprietary to Pearson Education, Inc. and may be covered by U.S. 
 * and Foreign Patents, patent applications, and are protected by trade secret 
 * or copyright law. Dissemination of this information, reproduction of this  
 * material, and copying or distribution of this software is strictly forbidden   
 * unless prior written permission is obtained from Pearson Education, Inc.
 */
package com.mypractice.microservice.actuator;

import com.mypractice.microservice.socialcore.actuator.AbstractReadyCheck;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class ReadyCheck extends AbstractReadyCheck {


  public ReadyCheck() {
    super();
  }


  @Override
  public Map<String, String> dependentComponents() {
    return Map.of("user-svc", "http://localhost:8090/actuator/health");
  }
}
