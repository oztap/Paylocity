package paylocity.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {
    public static List<String> getElementsText(List<WebElement> webElementList){


        List<String> webElementsAsString = new ArrayList<>();

        for (WebElement each : webElementList) {
            webElementsAsString.add(each.getText());
        }


        return webElementsAsString;
    }


    public static void titleVerification(String expectedTitle){

        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertTrue(actualTitle.equals(expectedTitle));


    }


    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            System.out.println("something happened in sleep method");

        }
    }
}
