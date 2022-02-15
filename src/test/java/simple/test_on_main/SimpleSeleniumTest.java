package simple.test_on_main;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleSeleniumTest {
    public static void main(String[] args){
        System.out.println("Simple Selenium test");

        try {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.applitools.com/");
            Thread.sleep(500);
        driver.manage().window().setSize(new Dimension(800, 600));
        Thread.sleep(1000);
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)");
        Thread.sleep(1000);
        ((JavascriptExecutor)driver).executeScript("window.scrollTo({top:0})");
        Thread.sleep(1000);
        driver.quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
