package helpers;

import core.TestManager;

public class LoginHelper {

    private final TestManager manager;

    public LoginHelper() {
        manager = TestManager.getActualInstance();
    }

    public boolean isUserAlreadyLoggedIn() {
        return manager.getWebDriver().manage().getCookieNamed("sessionid") != null;
    }
}
