package Learning.Common;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

import static java.lang.Thread.sleep;

public class BrowserWindowsLearning {

    // LOCATOR
    private static By multipleWindowsLink = By.cssSelector("a[href='/windows']");
    private static By newWindowLink = By.cssSelector("a[href='/windows/new']");
    private static By newWindowText = By.xpath("//h3[text()='New Window']");

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

        driver.findElement(multipleWindowsLink).click();
        driver.findElement(newWindowLink).click();
        Set<String>  windows = driver.getWindowHandles();
        Assert.assertEquals(windows.size(), 2);
        // get new window (child window)
        String newWindowId = windows.stream().toList().get(1);
        driver.switchTo().window(newWindowId);
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/windows/new");
        Assert.assertEquals(driver.findElement(newWindowText).getText(), "New Window");


        //CLEAN UP
        sleep(3500);
        driver.quit();
    }

}
