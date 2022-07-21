import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterMethod;

@CucumberOptions(
        features = "classpath:features/",
        glue = {"common"},
        tags = "",
        plugin = {"pretty"},
        publish = true
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @AfterMethod(alwaysRun = true)
    public void tearDownClass(){

    }
}

