package simple.test_on_main;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleSeleniumApplitoolsTest {
    public static void main(String[] args){
        System.out.println("Simple Applitools Selenium test");
        EyesRunner runner = new ClassicRunner();
        Eyes eyes = new Eyes(runner);
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        eyes.setLogHandler(new StdoutLogHandler(true));
        eyes.setBatch(new BatchInfo("Simple Applitools Selenium test"));

        testNoEyes();

        testEyesNoResize(eyes);

        testEyesResize(eyes, 761, 267);

        TestResultsSummary testResults = runner.getAllTestResults();
        for (TestResultContainer resultContainer : testResults){
            System.out.println(resultContainer);
        }
    }

    public static void testEyesResize(Eyes eyes, int width, int height) {
        System.out.println("This will open the eyes and resize the window and take screenshots. They will appear on the Applitools dashboard ");

        WebDriver driver = new ChromeDriver();

        eyes.open(driver, "App", "Test with driver resize", new RectangleSize(width, height));
        driver.get("https://demo.applitools.com/");
        eyes.check(Target.window());
        eyes.check(Target.window().fully());
        eyes.close();
        driver.quit();

    }

    public static void testEyesNoResize(Eyes eyes){
        System.out.println("This will open the eyes without resizing the browser and take screenshots. They will appear on the Applitools dashboard ");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(800, 600));



        eyes.open(driver, "App", "Test without driver resize");
        driver.get("https://demo.applitools.com/");
        eyes.check(Target.window());
        eyes.check(Target.window().fully());
        eyes.close();
        driver.quit();


    }

    public static void testNoEyes(){
        System.out.println("This will resize the browser using driver.manage().window().setSize. " +
                "driver.manage().window().setSize is also used by eyes.open");
        final int width = 800;
        final int height = 600;
        try {
            WebDriver driver = new ChromeDriver();

            driver.get("https://demo.applitools.com/");
            Thread.sleep(500);
            driver.manage().window().setSize(new Dimension(width, height));
            Thread.sleep(1000);
            ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)");
            Thread.sleep(1000);
            ((JavascriptExecutor)driver).executeScript("window.scrollTo({top:0})");
            Thread.sleep(1000);

            Dimension windowSize = driver.manage().window().getSize();
            if (width != windowSize.width) {
                System.out.println("Selenium width changed");
            }

            if (height != windowSize.height) {
                System.out.println("Selenium height changed");
            }

            driver.quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
