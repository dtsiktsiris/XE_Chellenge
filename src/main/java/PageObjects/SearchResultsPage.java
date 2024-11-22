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
    public By sortingDropdown = By.cssSelector("div[data-testid='sort-filter'] button[data-testid='open-property-sorting-dropdown']");
    public By priceDescOption = By.cssSelector("div[data-testid='sort-filter'] button[data-testid='price_desc']");
    public By carousels = By.cssSelector("div[data-testid='property-ad-images-carousel']");
    public By adImageLink = By.cssSelector("div[data-testid='property-ad-image-container'] a[data-testid='property-ad-url']");
    public By phones = By.cssSelector("div[data-testid='phones']");
    public By phonesButton = By.cssSelector("button[data-testid='call-action-button']");
    public By closePhonesModalButton = By.cssSelector("div.call-action-modal button[data-testid='xe-modal-close']");
    public By closeAdModalButton = By.cssSelector("div.ad-details-modal button[data-testid='xe-modal-close']");
    public By closeMultipleAdModalButton = By.cssSelector("div.up-modal button[data-testid='xe-modal-close']");
    public By multipleAdsPopupImageLinks = By.cssSelector("div.unique-ad-image-container a[data-testid='unique-ad-url']");


    public WebElement getPriceFilterButton() {
        return driver.findElement(priceFilterButton);
    }

    public WebElement getClosePhonesModalButton() {
        return driver.findElement(closePhonesModalButton);
    }

    public WebElement getCloseMultipleAdModalButton() {
        return driver.findElement(closeMultipleAdModalButton);
    }

    public WebElement getCloseAdModalButton() {
        return driver.findElement(closeAdModalButton);
    }

    public WebElement getPhonesButton() {
        return driver.findElement(phonesButton);
    }

    public WebElement getPhones() {
        return driver.findElement(phones);
    }

    public WebElement getCommonResultsTitleContainer() {
        return driver.findElement(commonResultsTitleContainer);
    }

    public List<WebElement> getMultipleAdsPopupImageLinks() {
        return driver.findElements(multipleAdsPopupImageLinks);
    }

    public List<WebElement> getAdImageLinks() {
        return driver.findElements(adImageLink);
    }

    public List<WebElement> getCarousels() {
        return driver.findElements(carousels);
    }

    public WebElement getFirstCarousel() {
        return driver.findElements(carousels).get(0);
    }

    public WebElement getFirstCarouselPrevButton() {
        return driver.findElements(carousels).get(0).findElement(By.cssSelector("button.prev-arrow"));
    }

    public WebElement getFirstCarouselNextButton() {
        return driver.findElements(carousels).get(0).findElement(By.cssSelector("button.next-arrow"));
    }

    public void typeOnMinPriceFilterInput(String text) {
        driver.findElement(minPriceFilterInput).sendKeys(text);
    }

    public void typeOnMaxPriceFilterInput(String text) {
        driver.findElement(maxPriceFilterInput).sendKeys(text);
    }

    public WebElement getSizeFilterButton() {
        return driver.findElement(sizeFilterButton);
    }

    public void typeOnMinSizeFilterInput(String text) {
        driver.findElement(minSizeFilterInput).sendKeys(text);
    }

    public void typeOnMaxSizeFilterInput(String text) {
        driver.findElement(maxSizeFilterInput).sendKeys(text);
    }

    public List<WebElement> getPropertyAdTitles() {
        return driver.findElements(propertyAdTitle);
    }

    public List<WebElement> getPropertyAdPrices() {
        return driver.findElements(propertyAdPrice);
    }

    public WebElement getSortingDropdown() {
        return driver.findElement(sortingDropdown);
    }

    public WebElement getPriceDescOption() {
        return driver.findElement(priceDescOption);
    }
}
