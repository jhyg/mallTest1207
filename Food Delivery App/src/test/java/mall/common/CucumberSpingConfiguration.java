package mall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import mall.FoodDeliveryAppApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { FoodDeliveryAppApplication.class })
public class CucumberSpingConfiguration {}
