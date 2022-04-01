package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class DataHubPage extends BaseWebPage {

    public SelenideElement rightMenu = $x("//div[@class='right menu open']");
    public SelenideElement menuSignInItem = $x("//div[@class='menu']//a[contains(@class,'sign-in')]");
    public SelenideElement spatialCoverage = $x("//div[@class='Select-control']/div/div[text()='Spatial Coverage']");
    public SelenideElement viewDatasets = $x("//div[@class='variable-form']/a");
    public SelenideElement dataSetsSelectors = $x("//div[@class='datasets-selectors']");
    public ElementsCollection dataSet = $$x("//div[@class='dataset']");
    public SelenideElement variableSelectionDropdown;
    public ElementsCollection selectionList;

    public DataHubPage() {
        if (manager.isMobile()) {
            variableSelectionDropdown = $x("//input[@name='variable']");
            selectionList = $$x("//div[@class='variable-form']/div/ul/li");
        } else {
            variableSelectionDropdown = $x("//div[text()='Variable Names']//following::div[1]//div[@class='Select-control']");
            selectionList = $$x("//div[@class='Select-menu']/div");
        }
    }

    public void setVariable(String variableName) {
        selectionList.shouldHave(sizeGreaterThan(1));
        for (SelenideElement variable : selectionList) {
            if (variable.getText().equals(variableName)) {
                variable.click();
                break;
            }
        }
        if (manager.isMobile()) {
            viewDatasets.click();
        }
    }

    public void setCountry(String countryName) {
        selectionList.shouldHave(sizeGreaterThan(1));
        for (SelenideElement country : selectionList) {
            if (country.getText().equals(countryName)) {
                country.click();
                break;
            }
        }
    }

    public SelenideElement getDatasetElement(String selector) {
        return dataSetsSelectors.$x("./div/div/div[text()='" + selector + "']");
    }
}
