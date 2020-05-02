package org.ff4j.sample.integration;

import org.ff4j.aop.Flip;
import org.ff4j.sample.dto.ResponseDTO;
import org.ff4j.sample.hystrix.FF4JHelper;

public interface MyIntegration {
    
    @Flip(name=FF4JHelper.FEATURE_UID_F1, alterBean=FF4JHelper.BEANID_MY_INTEGRATION_ALTERNATIVE)
    ResponseDTO doIntegration(String myDTO);
    
}
