package Learning.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CheckBoxElementAssignment {

    public static void main(String[] args) throws InterruptedException {
        //LOCATOR
        By firstOptionCheckBox = By.id("checkBoxOption1");
        By secondOptionCheckBox = By.id("checkBoxOption2");
        By thirdOptionCheckBox = By.id("checkBoxOption3");
        By allOptionsCheckBox = By.xpath("//input[@type='checkbox']");


        //SETUP

        String url = "https://rahulshettyacademy.com/AutomationPractice/";
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // SCRIPT
        // 1. Check the first  Checkbox and verify if it is successfully checked and Uncheck it again to verify if it is successfully Unchecked
        WebElement el1 = driver.findElement(firstOptionCheckBox);
        el1.click();
        Assert.assertTrue(el1.isSelected());

        el1.click();
        Assert.assertFalse(el1.isSelected());

        // 2. Get the Count of number of check boxes present in the page
        List<WebElement> els = driver.findElements(allOptionsCheckBox);
        Assert.assertEquals(els.size(), 3);

        //CLEAN UP
        Thread.sleep(1500);
        driver.quit();

    }
}
