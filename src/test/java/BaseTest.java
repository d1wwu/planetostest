import com.codeborne.selenide.Condition;
import core.TestManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.DataHubPage;
import pages.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static core.TestManager.assertThat;
import static org.hamcrest.Matchers.is;

public class BaseTest {

    protected TestManager manager;

    private DataHubPage dataHubPage;

    @BeforeTest(alwaysRun = true)
    public void init() {
        manager = new TestManager();
        manager.configure();
        dataHubPage = new DataHubPage();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        manager.closeWebDriver();
    }

    protected void login() {
        new LoginPage().login();
    }

    protected void selectVariable(String variable) {
        dataHubPage.variableSelectionDropdown.click();
        dataHubPage.setVariable(variable);
    }

    protected void selectCountry(String country) {
        dataHubPage.spatialCoverage.click();
        dataHubPage.setCountry(country);
    }

    protected void assertFilter(String selector) {
        dataHubPage.dataSetsSelectors.shouldBe(Condition.visible, Duration.ofMillis(10000));
        assertThat("Filter '" + selector + "' is exist", dataHubPage.getDatasetElement(selector).isDisplayed(), is(true));
    }

    protected void assertDataset() {
        dataHubPage.dataSet.shouldHave(size(1), Duration.ofMillis(10000));
        assertThat("Filtered results are shown", dataHubPage.dataSet.get(0).isDisplayed(), is(true));
    }
}
