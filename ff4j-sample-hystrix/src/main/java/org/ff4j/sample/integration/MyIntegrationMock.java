package org.ff4j.sample.integration;

import org.ff4j.sample.dto.ResponseDTO;
import org.ff4j.sample.hystrix.FF4JHelper;
import org.springframework.stereotype.Service;

@Service(FF4JHelper.BEANID_MY_INTEGRATION_ALTERNATIVE)
public class MyIntegrationMock implements MyIntegration{

    @Override
    public ResponseDTO doIntegration(String myDTO) {
        return new ResponseDTO("doIntegration: mock with " + myDTO);
    }
}