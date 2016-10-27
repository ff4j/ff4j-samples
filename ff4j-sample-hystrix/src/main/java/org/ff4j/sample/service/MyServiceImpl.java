package org.ff4j.sample.service;

import org.ff4j.sample.dto.ResponseDTO;
import org.ff4j.sample.hystrix.FF4JHelper;
import org.ff4j.sample.integration.MyIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

    @Autowired
    @Qualifier(FF4JHelper.BEANID_MY_INTEGRATION)
    private MyIntegration myIntegration;

    /** {@inheritDoc} */
    @Override
    public ResponseDTO doSomething(String myDTO) {
        return myIntegration.doIntegration(myDTO);
    }
}