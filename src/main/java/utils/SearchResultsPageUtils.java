package utils;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPageUtils {

    public static int extractSizeFromTitle(WebElement title){
        return Integer.parseInt(title.getText().split(" ")[1]);
    }

    public static List<Integer> extractSizesFromTitles(List<WebElement> elements){
        List<Integer> sizes = new ArrayList<>();
        for (WebElement element : elements ){
            sizes.add(SearchResultsPageUtils.extractSizeFromTitle(element));
        }
        return sizes;
    }

    public static int extractPrice(WebElement title){
        int i =StringUtils.extractInteger(title.getText());
        System.out.println(i);
        return StringUtils.extractInteger(title.getText());
    }

    public static List<Integer> extractPrices(List<WebElement> elements){
        List<Integer> sizes = new ArrayList<>();
        for (WebElement element : elements ){
            System.out.println(element.getText());
//            sizes.add(SearchResultsPageUtils.extractPrice(element));
        }
        return sizes;
    }
    public static int extractResultsCount(String text){
        String resultsTemp = text.split("αφορούν ")[1];
        resultsTemp = resultsTemp.split(" ")[0];
        return Integer.parseInt(resultsTemp);
    }
}
