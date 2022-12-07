package mall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import mall.PaymentApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { PaymentApplication.class })
public class CucumberSpingConfiguration {}
