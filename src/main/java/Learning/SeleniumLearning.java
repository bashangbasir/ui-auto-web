package Learning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumLearning {

    public static void main(String[] args) throws InterruptedException {

        //Chrome
        WebDriver driver = new ChromeDriver();
        driver.get("https://qaplayground.dev/");
        Thread.sleep(1000);
        String title = driver.getTitle();
        String currentUrl = driver.getCurrentUrl();
        System.out.println(title);
        System.out.println(currentUrl);
        driver.quit();


        WebDriver driver1 = new FirefoxDriver();
        driver1.get("https://qaplayground.dev/");
        Thread.sleep(1000);
        String title1 = driver1.getTitle();
        String currentUrl1 = driver1.getCurrentUrl();
        System.out.println(title1);
        System.out.println(currentUrl1);
        driver1.quit();
    }
}
