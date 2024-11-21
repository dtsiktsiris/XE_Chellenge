package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebElementUtils {
    public static List<String> getTextFromElements(List<WebElement> elements){
        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return texts;
    }
    public static void PageScrollAndWait(WebDriver driver, float percentage, int waitTimePerStep){
        for(float p=percentage; p<=1; p+=percentage ){
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight*"+p+")");

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTimePerStep));
        }
    }
}