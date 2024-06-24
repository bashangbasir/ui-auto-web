package Learning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverWindowLearning {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        //set implicit wait to avoid failure
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // go to google.com
        driver.get("https://www.google.com");

        //maximize browser windows
        driver.manage().window().maximize();

        Thread.sleep(2000);

        //fullscreen
        driver.manage().window().fullscreen();

        Thread.sleep(2000);

        driver.quit();
    }
}
