package Learning.Common;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class ActionsLearning {

    // LOCATOR
    private static By hoverLink = By.cssSelector("a[href='/hovers']");
    private static By contextMenuLink = By.cssSelector("a[href='/context_menu']");
    private static By keyPressLink = By.cssSelector("a[href='/key_presses']");
    private static By contectClickArea = By.id("hot-spot");
    private static By hoverPageHeader = By.xpath("//h3[text()='Hovers']");
    private static By profiles = By.xpath("(//img[@alt='User Avatar'])");
    private static By userCaption = By.cssSelector("div.figcaption");
    private static By inputTarget = By.id("target");


    // OTHERS
    private static String url = "https://the-internet.herokuapp.com/";
    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        String username = "rahulshettyacademy";
        String password = "learning";
        String userTypeGroup = "Consultant";

        // SETUP
        driver = new ChromeDriver();
        driver.get(url);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions action = new Actions(driver);

        //SCRIPT
        driver.findElement(hoverLink).click();
        String pageTitle = driver.findElement(hoverPageHeader).getText();
        Assert.assertEquals(pageTitle, "Hovers");
        List<WebElement> users = driver.findElements(profiles);
        List<WebElement> captions = driver.findElements(userCaption);

        // Hover the mouse to element

        for(int i=0; i<users.size(); i++){
            //hover to profile image
            action.moveToElement(users.get(i)).build().perform();
            WebElement cap = captions.get(i).findElement(By.tagName("h5"));
            Assert.assertTrue(cap.isDisplayed());
            Thread.sleep(1000);
        }

        driver.navigate().back();

        // Right Click Action

        driver.findElement(contextMenuLink).click();
        WebElement area = driver.findElement(contectClickArea);
        action.moveToElement(area).contextClick().build().perform();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();

        driver.navigate().back();

        // hold key and send keys using action

        driver.findElement(keyPressLink).click();
        WebElement input = driver.findElement(inputTarget);
        action.moveToElement(input).click()
                .keyDown(Keys.SHIFT).sendKeys("bashang")
                .keyUp(Keys.SHIFT).sendKeys("basir")
                .build().perform();


        //CLEAN UP
        sleep(3500);
        driver.quit();
    }
}
