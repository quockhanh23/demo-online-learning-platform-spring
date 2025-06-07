package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.dto.TestDTO;
import com.example.demoonlinelearningplatform.entity.Test;

public interface TestService {

    Test createTest(Test request);

    TestDTO getDetailTest(Long idTest);

    Test updateTest(Test request);
}
