
import PageObjects.PropertyPage;
import PageObjects.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.SearchResultsPageUtils;
import utils.WebElementUtils;

import java.time.Duration;
import java.util.List;

public class XETest {

    WebDriver driver;
    WebDriverWait wait;


    @BeforeMethod
    public void beforeTest() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.xe.gr/property/");
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }

    @Test
    public void testResults() {
        PropertyPage propertyPage = new PropertyPage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(propertyPage.acceptAllCookiesButton));
        propertyPage.acceptAllCookies();

        Assert.assertEquals(propertyPage.getSelectedTransaction(), "Ενοικίαση");
        Assert.assertEquals(propertyPage.getSelectedType(), "Κατοικία");

        propertyPage.typeOnGeoAreaInput("Παγκράτι");
        wait.until(ExpectedConditions.visibilityOfElementLocated(propertyPage.geoAreaSuggestions));

        List<String> suggestions = WebElementUtils.getTextFromElements(propertyPage.getGeoAreaSuggestions());
        propertyPage.selectGeoAreaSuggestionByText(suggestions.get(0));

        for (int i = 1; i < suggestions.size(); i++) {
            propertyPage.typeOnGeoAreaInput("Παγκράτι");
            wait.until(ExpectedConditions.visibilityOfElementLocated(propertyPage.geoAreaSuggestions));

            propertyPage.selectGeoAreaSuggestionByText(suggestions.get(i));
        }

        if (suggestions.size() > 1) {
            Assert.assertEquals(propertyPage.getAreaTagButton().getText(), suggestions.size() + " περιοχές");
        } else {
            Assert.assertEquals(propertyPage.getTagValueButton().getText(), suggestions.get(0));
        }

        propertyPage.clickSearchButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.priceFilterButton));

        searchResultsPage.getPriceFilterButton().click();

        int minPrice = 200;
        int maxPrice = 700;
        searchResultsPage.typeOnMinPriceFilterInput(String.valueOf(minPrice));
        searchResultsPage.typeOnMaxPriceFilterInput(String.valueOf(maxPrice));

        searchResultsPage.getSizeFilterButton().click();

        int minSize = 75;
        int maxSize = 150;
        searchResultsPage.typeOnMinSizeFilterInput(String.valueOf(minSize));
        searchResultsPage.typeOnMaxSizeFilterInput(String.valueOf(maxSize));

        searchResultsPage.getSizeFilterButton().click(); // close form

        Assert.assertEquals(searchResultsPage.getPriceFilterButton().getText(), minPrice + " - " + maxPrice + " €", "Price text not match the parameters");
        Assert.assertEquals(searchResultsPage.getSizeFilterButton().getText(), minSize + " - " + maxSize + " τ.μ.", "Size text not match the parameters");

        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.propertyAdTitle));

        int resultsCount = SearchResultsPageUtils.extractResultsCount(searchResultsPage.getCommonResultsTitleContainer().getText());
        System.out.println("Results " + resultsCount);

        WebElementUtils.PageScrollProgressively(driver, 0.025f);

        List<Integer> sizes = SearchResultsPageUtils.extractSizesFromTitles(searchResultsPage.getPropertyAdTitles());

        Assert.assertEquals(sizes.size(), resultsCount, "Check that we get as many elements as the results");

        for (Integer size : sizes) {
            Assert.assertTrue(minSize <= size && size <= maxSize, "Assert size is between criteria");
        }

        List<Integer> prices = SearchResultsPageUtils.extractPrices(searchResultsPage.getPropertyAdPrices());
        Assert.assertEquals(prices.size(), resultsCount, "Check that we get as many elements as the results");

        for (Integer price : prices) {
            Assert.assertTrue(minPrice <= price && price <= maxPrice, "Assert price is between criteria");
        }

        WebElementUtils.PageScrollTop(driver);

        searchResultsPage.getSortingDropdown().click();
        searchResultsPage.getPriceDescOption().click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.propertyAdTitle));

        WebElementUtils.PageScrollProgressively(driver, 0.025f);

        prices = SearchResultsPageUtils.extractPrices(searchResultsPage.getPropertyAdPrices());
        Assert.assertEquals(prices.size(), resultsCount, "Check that we get as many elements as the results");

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i + 1), "Previous price should be higher");
        }
    }

    @Test
    public void testImagesAndPropertyPopup() {
        PropertyPage propertyPage = new PropertyPage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(propertyPage.acceptAllCookiesButton));
        propertyPage.acceptAllCookies();

        propertyPage.typeOnGeoAreaInput("Παγκράτι");
        wait.until(ExpectedConditions.visibilityOfElementLocated(propertyPage.geoAreaSuggestions));

        List<String> suggestions = WebElementUtils.getTextFromElements(propertyPage.getGeoAreaSuggestions());
        propertyPage.selectGeoAreaSuggestionByText(suggestions.get(0));

        for (int i = 1; i < suggestions.size(); i++) {
            propertyPage.typeOnGeoAreaInput("Παγκράτι");
            wait.until(ExpectedConditions.visibilityOfElementLocated(propertyPage.geoAreaSuggestions));

            propertyPage.selectGeoAreaSuggestionByText(suggestions.get(i));
        }
        propertyPage.clickSearchButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.priceFilterButton));

        searchResultsPage.getPriceFilterButton().click();

        int minPrice = 200;
        int maxPrice = 700;
        searchResultsPage.typeOnMinPriceFilterInput(String.valueOf(minPrice));
        searchResultsPage.typeOnMaxPriceFilterInput(String.valueOf(maxPrice));

        searchResultsPage.getSizeFilterButton().click();

        int minSize = 75;
        int maxSize = 150;
        searchResultsPage.typeOnMinSizeFilterInput(String.valueOf(minSize));
        searchResultsPage.typeOnMaxSizeFilterInput(String.valueOf(maxSize));

        searchResultsPage.getSizeFilterButton().click(); // close form
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.propertyAdTitle));

        Actions action = new Actions(driver);
        action.moveToElement(searchResultsPage.getFirstCarousel()).perform();

        String classesFirst = searchResultsPage.getFirstCarousel().findElement(By.cssSelector("div[data-index='0']")).getAttribute("class");
        assert classesFirst != null;
        Assert.assertTrue(classesFirst.contains("slick-current"));

        String classesSecond = searchResultsPage.getFirstCarousel().findElement(By.cssSelector("div[data-index='1']")).getAttribute("class");
        assert classesSecond != null;
        Assert.assertFalse(classesSecond.contains("slick-current"));

        searchResultsPage.getFirstCarouselNextButton().click();

        classesFirst = searchResultsPage.getFirstCarousel().findElement(By.cssSelector("div[data-index='0']")).getAttribute("class");
        assert classesFirst != null;
        Assert.assertFalse(classesFirst.contains("slick-current"));

        classesSecond = searchResultsPage.getFirstCarousel().findElement(By.cssSelector("div[data-index='1']")).getAttribute("class");
        assert classesSecond != null;
        Assert.assertTrue(classesSecond.contains("slick-current"));

        action.moveToElement(searchResultsPage.getFirstCarousel()).perform();

//        Animation can be waited like with line below, but it is flaky sometimes
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.slick-track[transition]")));
        try {
            Thread.sleep(700); // this is for animation to finish
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        searchResultsPage.getFirstCarouselPrevButton().click();

        wait.until(ExpectedConditions.attributeContains(searchResultsPage.getFirstCarousel().findElement(By.cssSelector("div[data-index='0']")), "class", "slick-current"));

        classesFirst = searchResultsPage.getFirstCarousel().findElement(By.cssSelector("div[data-index='0']")).getAttribute("class");
        assert classesFirst != null;
        Assert.assertTrue(classesFirst.contains("slick-current"));

        classesSecond = searchResultsPage.getFirstCarousel().findElement(By.cssSelector("div[data-index='1']")).getAttribute("class");
        assert classesSecond != null;
        Assert.assertFalse(classesSecond.contains("slick-current"));

        WebElementUtils.PageScrollProgressively(driver, 0.025f);

        List<WebElement> carousels = searchResultsPage.getCarousels();
        Assert.assertFalse(carousels.isEmpty());

        int imageContains;
        for (WebElement carousel : carousels) {
            imageContains = carousel.findElements(By.cssSelector("div.slick-slide:not(.slick-cloned)")).size();
            // If it's a multiple ad, we check only first, next 'for' has the logic, how it can be done
            Assert.assertTrue(imageContains < 31, "Check that images are no more than 30");
        }

        WebElementUtils.PageScrollTop(driver);

        List<WebElement> imageLinks =searchResultsPage.getAdImageLinks();
        Assert.assertFalse(imageLinks.isEmpty());

        WebElement imageLink;
        for (int z = 0; z < imageLinks.size(); z++) { // I did it with traditional for, so you can lower the iterations
        imageLink = imageLinks.get(z);
            if (imageLink.findElements(By.cssSelector("span.common-ad-label")).isEmpty()  ||
              !imageLink.findElement(By.cssSelector("span.common-ad-label")).getText().equals("Πολλαπλές αγγελίες")){
                imageLink.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.phonesButton));

                Assert.assertEquals(searchResultsPage.getPhonesButton().getText(), "Προβολή τηλεφώνου");// I observed if screen is small changes to "Κλήση"

                searchResultsPage.getPhonesButton().click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.phones));
                Assert.assertTrue(searchResultsPage.getPhones().isDisplayed());
                searchResultsPage.getClosePhonesModalButton().click();
                searchResultsPage.getCloseAdModalButton().click();
            } else {
                imageLink.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.multipleAdsPopupImageLinks));
                int multipleAdsCount = searchResultsPage.getMultipleAdsPopupImageLinks().size();
                for(int i=0; i<multipleAdsCount; i++){
                    wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.multipleAdsPopupImageLinks));
                    searchResultsPage.getMultipleAdsPopupImageLinks().get(i).click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.phonesButton));

                    Assert.assertEquals(searchResultsPage.getPhonesButton().getText(), "Προβολή τηλεφώνου");// I observe if screen is small changes to "Κλήση"

                    searchResultsPage.getPhonesButton().click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.phones));
                    Assert.assertTrue(searchResultsPage.getPhones().isDisplayed());
                    searchResultsPage.getClosePhonesModalButton().click();
                    searchResultsPage.getCloseAdModalButton().click();
                }
                searchResultsPage.getCloseMultipleAdModalButton().click();
            }
        }
    }

    @Test
    public void testPagination(){
        PropertyPage propertyPage = new PropertyPage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(propertyPage.acceptAllCookiesButton));
        propertyPage.acceptAllCookies();

        // using different criteria so to have pagination
        propertyPage.typeOnGeoAreaInput("Πάτρα");
        wait.until(ExpectedConditions.visibilityOfElementLocated(propertyPage.geoAreaSuggestions));

        List<String> suggestions = WebElementUtils.getTextFromElements(propertyPage.getGeoAreaSuggestions());
        propertyPage.selectGeoAreaSuggestionByText(suggestions.get(0));

        propertyPage.clickSearchButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.priceFilterButton));

        // because first add may be repeated through pages
        // I use this index to get another add to compare
        int titleIndex = 5;
        String titleFirstPage = searchResultsPage.getPropertyAdTitles().get(titleIndex).getText();

        Assert.assertFalse(searchResultsPage.getPaginationLinks().isEmpty(),"Pagination tabs should exist");

        Assert.assertFalse(searchResultsPage.getPaginationLinks().get(0).isDisplayed(), "Previous button should be hidden");

        String classes = searchResultsPage.getPaginationLinks().get(1).getAttribute("class");
        assert classes != null;
        Assert.assertTrue(classes.contains("active"),"Pagination tab '1' should be active");

        classes = searchResultsPage.getPaginationLinks().get(2).getAttribute("class");
        assert classes != null;
        Assert.assertFalse(classes.contains("active"),"Pagination tab '2' should be inactive");

        searchResultsPage.getPaginationLinks().get(2).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.propertyAdTitle));

        String titleSecondPage = searchResultsPage.getPropertyAdTitles().get(titleIndex).getText();

        Assert.assertNotEquals(titleSecondPage, titleFirstPage, "Title should be different when page changed");

        classes = searchResultsPage.getPaginationLinks().get(1).getAttribute("class");
        assert classes != null;
        Assert.assertFalse(classes.contains("active"),"Pagination tab '1' should be inactive");

        classes = searchResultsPage.getPaginationLinks().get(2).getAttribute("class");
        assert classes != null;
        Assert.assertTrue(classes.contains("active"),"Pagination tab '2' should be active");

        Assert.assertTrue(searchResultsPage.getPaginationLinks().get(0).isDisplayed(), "Previous button should be shown after clicking on page 2");

        searchResultsPage.getPaginationNextLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.propertyAdTitle));

        classes = searchResultsPage.getPaginationLinks().get(3).getAttribute("class");
        assert classes != null;
        Assert.assertTrue(classes.contains("active"), "Pagination tab '3' should be active");

        searchResultsPage.getPaginationPrevLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.propertyAdTitle));

        classes = searchResultsPage.getPaginationLinks().get(2).getAttribute("class");
        assert classes != null;
        Assert.assertTrue(classes.contains("active"),"Pagination tab '2' should be active");
    }
}
