package Learning.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;

public class ElementsAssignment {

    public static void main(String[] args) throws InterruptedException {
        //LOCATOR
        By name = By.xpath("//div[@class='form-group']//input[@name='name']");
        By email = By.xpath("//div[@class='form-group']//input[@name='email']");
        By password = By.id("exampleInputPassword1");
        By checkBoxLoveIceCream = By.id("exampleCheck1");
        By genderDropdown = By.id("exampleFormControlSelect1");
        By radioButtonStudent = By.id("inlineRadio1");
        By radioButtonEmployed = By.id("inlineRadio2");
        By radioButtonEntrepreneur = By.id("inlineRadio1");
        By dateOfBirth = By.xpath("//input[@name='bday']");
        By submitBtn = By.cssSelector(".btn-success");
        By successMessage = By.cssSelector(".alert-success");
        By successMessageClose = By.cssSelector(".close");

        //SETUP

        String url = "https://rahulshettyacademy.com/angularpractice/";
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // SCRIPT

        driver.findElement(name).sendKeys("Bashang Basir");
        driver.findElement(email).sendKeys("bashang@test.com");
        driver.findElement(password).sendKeys("Bashang12345");

        WebElement isLikeIceCream = driver.findElement(checkBoxLoveIceCream);
        isLikeIceCream.click();
        Assert.assertTrue(isLikeIceCream.isSelected());

        Select gender = new Select(driver.findElement(genderDropdown));
        gender.selectByVisibleText("Male");

        driver.findElement(radioButtonStudent).click();
        driver.findElement(dateOfBirth).sendKeys("28/06/1993");
        driver.findElement(submitBtn).click();

        String message = driver.findElement(successMessage).getText();
        Assert.assertTrue(message.contains("The Form has been submitted successfully!."));
        Thread.sleep(2000);
        driver.findElement(successMessageClose).click();

        //CLEAN UP
        Thread.sleep(5500);
        driver.quit();
    }
}
