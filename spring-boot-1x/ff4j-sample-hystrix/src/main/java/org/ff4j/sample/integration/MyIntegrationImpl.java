package org.ff4j.sample.integration;

import org.ff4j.sample.dto.ResponseDTO;
import org.ff4j.sample.hystrix.FF4JHelper;
import org.ff4j.sample.hystrix.HystrixHelper;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service(FF4JHelper.BEANID_MY_INTEGRATION)
public class MyIntegrationImpl implements MyIntegration{

    @Override
    @HystrixCommand(groupKey = HystrixHelper.MY_GROUP, commandKey = "myCommandKey")
    public ResponseDTO doIntegration(String myDTO) {
        return new ResponseDTO("doIntegration: ok with " + myDTO);
    }

    public ResponseDTO doIntegrationFallback(String myDTO){
        return new ResponseDTO("doIntegration: fallback with " + myDTO);
    }
}
