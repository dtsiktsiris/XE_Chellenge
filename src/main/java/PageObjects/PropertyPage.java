package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PropertyPage {
    WebDriver driver;

    public PropertyPage(WebDriver driver) {
        this.driver = driver;
    }

    public By acceptAllCookiesButton = By.cssSelector("div.qc-cmp2-summary-buttons > button[mode='primary']");
    public By searchButton = By.cssSelector("input[data-testid='submit-input']");
    public By areaTagButton = By.cssSelector("button.area-tag" );
    public By tagValueButton = By.cssSelector("button[data-testid='tag-value']");
    public By geoAreaInput = By.cssSelector("input[data-testid='area-input']");
    public By geoAreaSuggestions = By.cssSelector("div.dropdown-panel > button");
    public By geoAreaSuggestionByText;
    public By selectedTransaction = By.cssSelector("div[data-testid='property-transaction-class'] > span[data-testid='property-transaction-name']");
    public By selectedType = By.cssSelector("div[data-testid='property-type-class'] > span[data-testid='property-type-name']");

    public void acceptAllCookies(){
        driver.findElement(acceptAllCookiesButton).click();
    }

    public WebElement getAreaTagButton(){
        return driver.findElement(areaTagButton);
    }

    public WebElement getTagValueButton(){
        return driver.findElement(tagValueButton);
    }

    public void clickSearchButton(){
        driver.findElement(searchButton).click();
    }

    public String getSelectedTransaction() {
        return driver.findElement(selectedTransaction).getText();
    }
    public String getSelectedType() {
        return driver.findElement(selectedType).getText();
    }

    public void selectGeoAreaSuggestionByText(String text){
        geoAreaSuggestionByText = By.xpath("//div[@data-testid='geo_place_id_dropdown_panel']/button[text()='"+text+"']");
        driver.findElement(geoAreaSuggestionByText).click();
    }

    public List<WebElement> getGeoAreaSuggestions(){
        return driver.findElements(geoAreaSuggestions);
    }

    public void typeOnGeoAreaInput(String text){
        driver.findElement(geoAreaInput).sendKeys(text);
    }
}
