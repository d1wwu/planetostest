package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.LoginHelper;
import org.testng.Reporter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class LoginPage extends BaseWebPage {

    private final LoginHelper loginHelper;
    private final DataHubPage dataHubPage;

    public final SelenideElement emailField = $x("//input[@name='email']");
    public final SelenideElement passwordField = $x("//input[@name='password']");
    public final SelenideElement submitButton = $x("//button[@type='submit']");
    public final SelenideElement contentForm = $x("//div[@class='content']");

    public LoginPage() {
        loginHelper = new LoginHelper();
        dataHubPage = new DataHubPage();
    }

    public void login() {
        login(manager.getLogin(), manager.getPassword());
    }

    public void login(String login, String passwd) {
        if (!loginHelper.isUserAlreadyLoggedIn()) {
            this.navigateTo();
            this.fillLoginForm(login, passwd);
            submitButton.click();
        }
    }

    public LoginPage navigateTo() {
        if (manager.isMobile()) {
            burgerMenu.click();
            headerMenu.shouldBe(visible);
            itemData.click();
            switchTo().window(1);
            dataHubPage.rightMenu.click();
            dataHubPage.menuSignInItem.click();
        } else {
            signInButton.click();
        }
        contentForm.shouldBe(visible);
        return this;
    }

    public LoginPage fillLoginForm(String login, String passwd) {
        Reporter.log("Login as " + login);
        this.setEmailField(login);
        this.setPasswordField(passwd);
        return this;
    }

    public LoginPage setEmailField(String val) {
        clearAndTypeNewValue(emailField, val);
        return this;
    }

    public LoginPage setPasswordField(String val) {
        clearAndTypeNewValue(passwordField, val);
        return this;
    }
}
