package org.ssoexample;

import org.ssoexample.config.Ui1App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Ui1App.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringContextIntegrationTest {

    @Test
    public void whenLoadApplication_thenSuccess() {

    }
}
