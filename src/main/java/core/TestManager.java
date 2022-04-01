package core;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import consts.Defaults;
import consts.UserAgent;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.Reporter;

import java.util.HashMap;
import java.util.Map;

public class TestManager {

    private static ITestContext testContext;
    private static final String MANAGER_NAME_IN_TEST_CONTEXT = "manager";

    private WebDriver webDriver;

    private String platform;
    private String login;
    private String password;
    private String apikey;

    private boolean isMobile = false;

    public TestManager() {
        overrideTestContext(this);
        fetchTestParams();
    }

    private static void overrideTestContext(TestManager testManager) {
        testContext = Reporter.getCurrentTestResult().getTestContext();
        testContext.setAttribute(MANAGER_NAME_IN_TEST_CONTEXT, testManager);
    }

    private void fetchTestParams() {
        platform = System.getProperty("platform");
        login = System.getProperty("login");
        password = System.getProperty("passwd");
        apikey = System.getProperty("apikey");
    }

    public TestManager configure() {
        if (platform != null && platform.equals("emulator")) {
            initMobileBrowser(UserAgent.EMULATOR, Defaults.DEFAULT_EMULATOR_WIDTH, Defaults.DEFAULT_EMULATOR_HEIGHT, Defaults.DEFAULT_EMULATOR_PIXELRATIO);
            isMobile = true;
        }
        webDriver = WebDriverRunner.getAndCheckWebDriver();
        return this;
    }

    public static TestManager getActualInstance() {
        return (TestManager) testContext.getAttribute(MANAGER_NAME_IN_TEST_CONTEXT);
    }

    private void initMobileBrowser(String userAgent, int width, int height, double pixelRatio) {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", width);
        deviceMetrics.put("height", height);
        deviceMetrics.put("pixelRatio", pixelRatio);
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", userAgent);
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriverManager.chromedriver().setup();
        WebDriverRunner.setWebDriver(new ChromeDriver(chromeOptions));
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getApikey() {
        return apikey;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public static <T> void assertThat(String attemptedTestDescription, T actual, Matcher<? super T> matcher) {
        try {
            org.hamcrest.MatcherAssert.assertThat(actual, matcher);
        } catch (AssertionError ex) {
            Reporter.log("Failed: " + attemptedTestDescription);
            throw new AssertionError();
        }
        Reporter.log("Passed: " + attemptedTestDescription);
    }

    public void closeWebDriver() {
        Selenide.closeWebDriver();
    }
}
