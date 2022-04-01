package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BaseWebPage extends BaseWebComponent {

    public final SelenideElement signInButton = $x("//div[text()='SIGN IN']");
    public final SelenideElement burgerMenu = $x("//div[@class='header-item burger']");
    public final SelenideElement headerMenu = $x("//div[@class='header-menu-container']");
    public final SelenideElement itemData = $x("//div[text()='DATAHUB']");
}
