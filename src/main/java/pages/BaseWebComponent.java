package pages;

import com.codeborne.selenide.SelenideElement;

public class BaseWebComponent extends BasePage {

    public void clearAndTypeNewValue(SelenideElement element, String text) {
        if (getInputLength(element) != 0) {
            element.clear();
        }
        element.sendKeys(text);
    }

    public int getInputLength(SelenideElement element) {
        return element.getText().length();
    }
}
