package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage {
    WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public By priceFilterButton = By.cssSelector("button[data-testid='price-filter-button']");
    public By minPriceFilterInput = By.cssSelector("form.range-filter-form input[data-testid='minimum_price_input']");
    public By maxPriceFilterInput = By.cssSelector("form.range-filter-form input[data-testid='maximum_price_input']");
    public By sizeFilterButton = By.cssSelector("button[data-testid='size-filter-button']");
    public By minSizeFilterInput = By.cssSelector("form.range-filter-form input[data-testid='minimum_size_input']");
    public By maxSizeFilterInput = By.cssSelector("form.range-filter-form input[data-testid='maximum_size_input']");
    public By propertyAdTitle = By.cssSelector("div.common-ad-body h3[data-testid='property-ad-title']");
    public By propertyAdPrice = By.cssSelector("div.common-ad-body span[data-testid='property-ad-price']");
    public By commonResultsTitleContainer = By.cssSelector("div[data-testid='common-results-title-container']");


    public WebElement getPriceFilterButton(){
        return driver.findElement(priceFilterButton);
    }
    public WebElement getCommonResultsTitleContainer(){
        return driver.findElement(commonResultsTitleContainer);
    }

    public void typeOnMinPriceFilterInput(String text){
        driver.findElement(minPriceFilterInput).sendKeys(text);
    }

    public void typeOnMaxPriceFilterInput(String text){
        driver.findElement(maxPriceFilterInput).sendKeys(text);
    }

    public WebElement getSizeFilterButton(){
        return driver.findElement(sizeFilterButton);
    }

    public void typeOnMinSizeFilterInput(String text){
        driver.findElement(minSizeFilterInput).sendKeys(text);
    }

    public void typeOnMaxSizeFilterInput(String text){
        driver.findElement(maxSizeFilterInput).sendKeys(text);
    }

    public List<WebElement> getPropertyAdTitles(){
        return driver.findElements(propertyAdTitle);
    }

    public List<WebElement> getPropertyAdPrices(){
        return driver.findElements(propertyAdPrice);
    }
}
