package Learning.Common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.function.Function;

public class SeleniumWaitsLearning {

    private static By promoCodeInput = By.cssSelector("input.promoCode");
    private static By applyPromoButton = By.cssSelector("button.promoBtn");
    private static By promoInfoMessage = By.cssSelector("span.promoInfo");
    private static By cartIcon = By.cssSelector("a.cart-icon");
    private static By cartPreviewPanel = By.cssSelector("div.cart-preview.active");
    private static By cartItem = By.xpath("//div[@class='cart-preview active']//li[@class='cart-item']");
    private static By proceedToCheckoutButton = By.xpath("//button[text()='PROCEED TO CHECKOUT']");


    // THE INTERNET WEBSITE
    private static By startButton = By.cssSelector("div[id='start'] button");
    private static By helloWorld = By.cssSelector("div[id='finish'] h4");


    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        //set implicit wait to avoid failure
        // implicit wait will wait before throw exception for every driver command
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //driver.manage().window().maximize();

        driver.findElement(cartIcon).click();
        WebElement cartPreview = driver.findElement(cartPreviewPanel);
        cartPreview.findElement(proceedToCheckoutButton).click();

        //Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(promoCodeInput).sendKeys("rahulshettyacademy");
        driver.findElement(applyPromoButton).click();
        WebElement infoMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(promoInfoMessage));
        Assert.assertEquals(infoMessage.getText(), "Code applied ..!");
        Assert.assertTrue(infoMessage.getAttribute("style").contains("green"));

        //Fluent wait
        // - under category of explicit wait
        // - Have polling time, example -> timeouts 10s , poll every 1s under condition meet or timeout reach
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        Thread.sleep(2000);
        driver.findElement(startButton).click();

        Duration timeout = Duration.ofSeconds(10);
        Duration polling = Duration.ofSeconds(2);
        Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(NoSuchElementException.class);

        WebElement msg = fWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                WebElement el = driver.findElement(helloWorld);
                if(el.isDisplayed()){
                    return el;
                }else{
                    return null;
                }
            }
        });

        Assert.assertTrue(msg.getText().contains("Hello World"));
        driver.quit();
    }
}
