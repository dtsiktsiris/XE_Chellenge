
import PageObjects.PropertyPage;
import PageObjects.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.SearchResultsPageUtils;
import utils.WebElementUtils;

import java.time.Duration;
import java.util.List;

public class XETest {
    @Test
    public void testProperty(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.xe.gr/property/");
        PropertyPage propertyPage = new PropertyPage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(propertyPage.acceptAllCookiesButton));
        propertyPage.acceptAllCookies();

        Assert.assertEquals( propertyPage.getSelectedTransaction(),"Ενοικίαση");
        Assert.assertEquals( propertyPage.getSelectedType(),"Κατοικία");

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

        Assert.assertEquals(searchResultsPage.getPriceFilterButton().getText(), minPrice+" - "+maxPrice+" €");
        Assert.assertEquals(searchResultsPage.getSizeFilterButton().getText(), minSize+" - "+maxSize+" τ.μ.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.propertyAdTitle));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<Integer> sizes = SearchResultsPageUtils.extractSizesFromTitles(searchResultsPage.getPropertyAdTitles());

        for(Integer size : sizes){
            System.out.println(size);
            Assert.assertTrue(minSize <= size && size <= maxSize, "Assert size is between criteria" );
        }

        int resultsCount = SearchResultsPageUtils.extractResultsCount(searchResultsPage.getCommonResultsTitleContainer().getText());

        WebElementUtils.PageScrollAndWait(driver, 0.1f,2);

        List<WebElement> prices = searchResultsPage.getPropertyAdPrices();
        Assert.assertEquals(prices.size(), resultsCount);
        System.out.println(resultsCount);
//        for(Integer price : prices){
//            Assert.assertTrue(minPrice <= price && price <= maxPrice, "Assert price is between criteria" );
//        }

//        System.out.println(prices.get(0));
    }
}
