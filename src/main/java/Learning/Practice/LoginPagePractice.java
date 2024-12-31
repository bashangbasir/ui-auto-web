package Learning.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LoginPagePractice {

    // LOCATOR
    private static By userNameInput = By.cssSelector("#username");
    private static By passwordInput = By.cssSelector("#password");
    private static By userRadioButton = By.xpath("//input[@value='user']");
    private static By userOkayButton = By.id("okayBtn");
    private static By groupDropdown = By.cssSelector("select.form-control");
    private static By termAndConditionCheckBox = By.id("terms");
    private static By signInButton = By.id("signInBtn");
    private static By productAddButton = By.cssSelector("button.btn.btn-info");
    private static By checkOutButton = By.cssSelector("a.nav-link.btn.btn-primary");

    // OTHERS
    private static String url = "https://rahulshettyacademy.com/loginpagePractise/";
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testLoginPage() throws InterruptedException {

        String username = "rahulshettyacademy";
        String password = "learning";
        String userTypeGroup = "Consultant";

        // SETUP
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        driver = new ChromeDriver(options);

        driver.get(url);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //SCRIPT

        driver.findElement(userNameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(userRadioButton).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(userOkayButton)));
        driver.findElement(userOkayButton).click();

        Select userType = new Select(driver.findElement(groupDropdown));
        userType.selectByVisibleText(userTypeGroup);

        driver.findElement(termAndConditionCheckBox).click();
        driver.findElement(signInButton).click();

        wait.until(ExpectedConditions.urlToBe("https://rahulshettyacademy.com/angularpractice/shop"));
        List<WebElement> addProductToCart = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productAddButton));
        Assert.assertEquals(addProductToCart.size(), 4);

        for (WebElement add : addProductToCart){
            add.click();
        }

        driver.findElement(checkOutButton).click();

        //CLEAN UP
        Thread.sleep(3500);
        driver.quit();
    }
}
