package mall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import mall.RestaurantApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { RestaurantApplication.class })
public class CucumberSpingConfiguration {}
