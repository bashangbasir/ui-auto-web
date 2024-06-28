package Learning.Elements;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class AlertsLearning {

    public static void main(String[] args) throws InterruptedException {
        //LOCATOR
        By nameForAlert = By.id("name");
        By alertButton = By.cssSelector("#alertbtn");
        By confirmButton = By.cssSelector("#confirmbtn");

        //SETUP

        String url = "https://rahulshettyacademy.com/AutomationPractice/";
        String user = "Bashang";
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // SCRIPT
        // 1. Add name and click Alert button
        driver.findElement(nameForAlert).sendKeys(user);
        driver.findElement(alertButton).click();
        // 2. Switch to alert and press OK (accept)
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        alert.accept();
        System.out.println(alertMessage);
        // 2. Click Confirm button and cancel
        driver.findElement(nameForAlert).sendKeys(user);
        driver.findElement(confirmButton).click();
        // 3. Switch to alert and press Cancel (dismiss)
        Alert confirm = driver.switchTo().alert();
        String confirmMessage = confirm.getText();
        confirm.dismiss();
        System.out.println(confirmMessage);

        //CLEAN UP
        Thread.sleep(1500);
        driver.quit();
    }
}
