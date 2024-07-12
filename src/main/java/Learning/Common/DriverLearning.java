package Learning.Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.Scanner;

public class DriverLearning {

    public static void main(String[] args) throws Exception {
        String url = "https://rahulshettyacademy.com/locatorspractice/";
        String username = "bashang";
        String password = "password";
        String newUserName = "John";
        String email = "bashnag@email.com";
        String phoneNumber = "0139999999";

        Scanner scanner = new Scanner(System.in);
        //LOCATORS
        By userNameTextBox = By.id("inputUsername");
        By passwordTextBox = By.name("inputPassword");
        By signInButton = By.className("signInBtn");
        By loginErrorMessage = By.cssSelector(".error");
        By forgotPasswordAnchor = By.linkText("Forgot your password?");
        By resetName = By.xpath("//input[@placeholder='Name']");
        By resetEmail = By.cssSelector("input[placeholder='Email']");
        By resetPhoneNumber = By.xpath("//input[@type='text'][2]"); //get element by index for xpath
        By resetButton = By.xpath("//button[contains(@class,'reset-pwd-btn')]");
        By infoMessageText = By.cssSelector("form p[class*='infoMsg']");
        By goToLoginButton = By.cssSelector(".go-to-login-btn");
        By rememberCheckBox = By.id("chkboxOne");
        By termAndPrivacyCheckBox = By.id("chkboxTwo");
        By successLoginMessage = By.xpath("//p[normalize-space()='You are successfully logged in.']");
        By logoutButton = By.xpath("//button[contains(text(),'Log Out')]");

        //SET WEBDRIVER
        System.out.println("Enter driver type: ");
        String driverType = scanner.nextLine();

        WebDriver driver;
        if (driverType.equals("chrome")) {
            driver = new ChromeDriver();
            System.out.println("using chrome");
        } else if (driverType.equals("edge")) {
            driver = new EdgeDriver();
            System.out.println("using edge");
        } else if (driverType.equals("mozilla")) {
            driver = new FirefoxDriver();
            System.out.println("using firefox");
        } else {
            driver = new ChromeDriver();
            System.out.println("using chrome - default");
        }
        //set implicit wait to avoid failure
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //GO TO WEBSITE
        driver.get(url);

        //INPUT USERNAME AND PASSWORD
        driver.findElement(userNameTextBox).sendKeys(username);
        driver.findElement(passwordTextBox).sendKeys(password);

        //CLICK SIGN IN BUTTON
        driver.findElement(signInButton).click();

        //GET ERROR MESSAGE TEXT
        String error = driver.findElement(loginErrorMessage).getText();
        System.out.println(error);

        // CLICK FORGOT PASSWORD
        driver.findElement(forgotPasswordAnchor).click();

        // RESET
        driver.findElement(resetName).sendKeys(newUserName);
        driver.findElement(resetEmail).sendKeys(email);
        driver.findElement(resetPhoneNumber).sendKeys(phoneNumber);
        Thread.sleep(1000); // to prevent ElementClickInterceptedException
        driver.findElement(resetButton).click();

        //EXTRACT PASSWORD FROM INFO MESSAGE
        String infoMessage = driver.findElement(infoMessageText).getText();
        String tempPassword = infoMessage.split("'")[1];
        System.out.println(tempPassword);

        // RE-LOGIN
        driver.findElement(goToLoginButton).click();
        driver.findElement(userNameTextBox).sendKeys(username);
        driver.findElement(passwordTextBox).sendKeys(tempPassword);
        Thread.sleep(1000); // to prevent ElementClickInterceptedException
        driver.findElement(rememberCheckBox).click();
        driver.findElement(termAndPrivacyCheckBox).click();
        Thread.sleep(1000);
        driver.findElement(signInButton).click();

        //VERIFY LOGIN SUCCESS
        String successMessage = getPassword(driver, successLoginMessage);
        Assert.assertEquals(successMessage, "You are successfully logged in.");
        System.out.println(successMessage);

        // LOG OUT
        Thread.sleep(2000);
        driver.findElement(logoutButton).click();

        //QUIT BROWSER
        Thread.sleep(1000);
        driver.quit();

    }

    public static String getPassword(WebDriver driver, By element) {
        return driver.findElement(element).getText();
    }
}
