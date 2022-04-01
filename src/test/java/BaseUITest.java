import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class BaseUITest extends BaseTest {

    @Test
    public void uiTest() {
        String variableName = "present weather";
        String spatialCoverage = "Estonia";

        open("https://planetos.com/");
        login();
        selectVariable(variableName);
        assertFilter(variableName);
        if (!manager.isMobile()) {
            selectCountry(spatialCoverage);
            assertFilter(spatialCoverage);
        }
        assertDataset();
    }
}
