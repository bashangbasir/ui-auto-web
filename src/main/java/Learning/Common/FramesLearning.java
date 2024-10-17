package Learning.Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import static java.lang.Thread.sleep;

public class FramesLearning {

    // LOCATOR
    private static By framesLink = By.cssSelector("a[href='/frames']");
    private static By nestedFramesLink = By.cssSelector("a[href='/nested_frames']");
    private static By iFramesLink = By.cssSelector("a[href='/iframe']");

    // Nested element locators
    private static By nestedFrameTopLocator = By.xpath("//frame[@name='frame-top']");
    private static By nestedFrameLeftLocator = By.xpath("//frame[@name='frame-left']");
    private static By nestedFrameMiddleLocator = By.xpath("//frame[@name='frame-middle']");
    private static By nestedFrameRightLocator = By.xpath("//frame[@name='frame-right']");
    private static By nestedFrameBottomLocator = By.xpath("//frame[@name='frame-bottom']");


    // OTHERS
    private static String url = "https://the-internet.herokuapp.com/";
    private static WebDriver driver;

    public static void main(String[] args) throws Exception{

        // SETUP
        driver = new ChromeDriver();
        driver.get(url);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions action = new Actions(driver);

        //SCRIPT
        driver.findElement(framesLink).click();
        driver.findElement(nestedFramesLink).click();

        driver.switchTo().frame(driver.findElement(nestedFrameTopLocator)).switchTo().frame(driver.findElement(nestedFrameLeftLocator));
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText(), "LEFT");

        driver.switchTo().parentFrame().switchTo().frame(driver.findElement(nestedFrameMiddleLocator));
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText(), "MIDDLE");

        driver.switchTo().parentFrame().switchTo().frame(driver.findElement(nestedFrameRightLocator));
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText(), "RIGHT");

        driver.switchTo().parentFrame().switchTo().parentFrame().switchTo().frame(driver.findElement(nestedFrameBottomLocator));
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText(), "BOTTOM");

        driver.switchTo().window()

        //CLEAN UP
        System.out.println("DONE! yeaaaaa boy");
        sleep(1000);
        driver.quit();
    }

}

